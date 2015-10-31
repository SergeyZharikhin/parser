package com.srzhio.service;

import com.srzhio.service.builders.lineemitters.LineEmitter;
import com.srzhio.service.builders.lineemitters.LineEmitterFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

import static com.srzhio.service.Patterns.*;

@Service
public class LineProcessor {

    public void recursiveProcessLine(final StringBuilder out, String content) {

        final StringBuilder temp = new StringBuilder();
        while (!content.isEmpty()) {
            String subLine = findSubstringFromFirstMatchToken(content);
            if (subLine.isEmpty()) {
                out.append(content);
                break;
            }
            Line line = new Line(subLine);
            int tokenPosition = content.length() - subLine.length();

            if (line.getLineType() != LineType.NONE) {
                out.append(content.substring(0, tokenPosition)); // write unmarked text

                String matchedText = line.getMatchResult().group(1);
                int matchedTextLength = line.getMatchResult().group(0).length();

                Line subPortion = new Line(matchedText);
                LineEmitter lineEmitter = LineEmitterFactory.getLineEmitter(subPortion);
                lineEmitter.buildline(subPortion, temp);

                content = content.substring(tokenPosition + matchedTextLength); // step over emitted text
                continue;
            }
            out.append(content.substring(0, tokenPosition + 1));
            content = content.substring(tokenPosition + 1);
        }
    }


    /**
     * Returns substring starting from first markdown token(if present) of the string or empty string otherwise
     * @param line
     * @return
     */
    private String findSubstringFromFirstMatchToken(final String line) {

        Matcher firstMatch = TOKEN_START_SIGNS.matcher(line);
        if (firstMatch.find()) {
            return line.substring(firstMatch.start());
        }
        return "";
    }


}
