package com.srzhio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

import static com.srzhio.service.Patterns.*;

@Service
public class LineEmitter {

    @Autowired
    HtmlGenerator generator;

    public void emit(StringBuilder out, String input) {
        generator.openHtmlBody(out);
        for (String line : NEW_LINE_SEPARATOR.split(input)) {
            emitLine(out, new Line(line.trim()));
        }
        generator.closeHtmlBody(out);
    }

    private void emitLine(StringBuilder out, Line line) {
        switch (line.getLineType()) {
            case EMPTY:
                generator.breakLine(out);
                break;
            case HEADING:
                generator.openHeading(out, line.getHeadingDepth());
                break;
            case PARAGRAPH:
                generator.openParagraph(out);
                break;
        }

        recursiveEmitLine(out, line.getContent());

        switch (line.getLineType()) {
            case HEADING:
                generator.closeHeading(out, line.getHeadingDepth());
                break;
            case PARAGRAPH:
                generator.closeParagraph(out);
                break;
        }
    }

    private void recursiveEmitLine(final StringBuilder out, String line) {

        final StringBuilder temp = new StringBuilder();
        while (!line.isEmpty()) {
            String subLine = findSubstringFromFirstMatchToken(line);
            if (subLine.isEmpty()) {
                out.append(line);
                break;
            }
            MarkdownUnit markdownUnit = getMarkdownUnit(subLine);
            int tokenPosition = line.length() - subLine.length();

            if (markdownUnit != null) {

                String inlineContent = markdownUnit.getMatchResult().group(1);
                int inlineLength = markdownUnit.getMatchResult().group(0).length();
                out.append(line.substring(0, tokenPosition)); // write unmarked text

                switch (markdownUnit.getMarkdownToken()) {
                    case LINK:
                        generator.openLink(out);
                        emitLinkTag(out, markdownUnit);
                        generator.closeLink(out);
                        break;
                    case EMPH:
                        recursiveEmitLine(temp, inlineContent);
                        generator.openEmphasis(out);
                        out.append(temp);
                        generator.closeEmphasis(out);
                        break;
                    case STRONG:
                        recursiveEmitLine(temp, inlineContent);
                        generator.openStrong(out);
                        out.append(temp);
                        generator.closeStrong(out);
                        break;
                    default:
                        System.out.println("Unknown Token");
                        break;
                }
                line = line.substring(tokenPosition + inlineLength); // step over emitted text
                continue;
            }
            out.append(line.substring(0, tokenPosition + 1));
            line = line.substring(tokenPosition + 1);
        }
    }

    private void emitLinkTag(StringBuilder out, MarkdownUnit markdownUnit) {
        String linkText = markdownUnit.getMatchResult().group(1);
        String ahref = markdownUnit.getMatchResult().group(2).trim();
        out.append(ahref).append("'>").append(linkText);
    }

    private String findSubstringFromFirstMatchToken(final String line) {

        Matcher firstMatch = TOKEN_START_SIGNS.matcher(line);
        if (firstMatch.find()) {
            return line.substring(firstMatch.start());
        }
        return "";
    }

    private MarkdownUnit getMarkdownUnit(String subLine) {
        Matcher matcher = choosePatternBasedOnToken(subLine);
        if (matcher.find()) {
            return new MarkdownUnit(matcher.toMatchResult());
        }
        return null;
    }

    private Matcher choosePatternBasedOnToken(String line) {
        if (line.startsWith(STRONG_START_SIGN)) return STRONG_PATTERN.matcher(line);
        if (line.startsWith(EMPH_START_SIGN)) return EMPH_PATTERN.matcher(line);
        return LINK_PATTERN.matcher(line);
    }
}
