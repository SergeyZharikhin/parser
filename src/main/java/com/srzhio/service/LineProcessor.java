package com.srzhio.service;

import com.srzhio.service.builders.MatchingUtils;
import com.srzhio.service.builders.lineemitters.LineEmitter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.regex.Matcher;

import static com.srzhio.service.Patterns.TOKEN_START_SIGNS;

@Service
public class LineProcessor {

    @Resource(name = "lineEmitters")
    private Map<LineType, LineEmitter> lineEmitters;

    public void recursiveProcessLine(final StringBuilder out, String content) {
        StringBuilder temp = new StringBuilder();
        while (!content.isEmpty()) {
            String subLine = findSubstringFromFirstMatchToken(content);
            if (subLine.isEmpty()) {
                out.append(content);
                break;
            }
            int tokenPosition = content.length() - subLine.length();
            out.append(content.substring(0, tokenPosition)); // write unmarked text before token
            Matcher matcher = MatchingUtils.determineMatcherForContent(subLine);
            if (matcher.find()) {
                String matchedText = matcher.group(1);
                int matchedTextLength = matcher.group(0).length();
                Line subPortion = new Line(matchedText, matcher);
                LineEmitter lineEmitter = lineEmitters.get(subPortion.getLineType());
                lineEmitter.emitLine(subPortion, temp);
                out.append(temp);
                content = content.substring(tokenPosition + matchedTextLength); // step over emitted text
                continue;
            }
            out.append(content.substring(0, tokenPosition + 1)); // write unmarked text after token
            content = content.substring(tokenPosition + 1);
        }
    }

    /**
     * Returns substring starting from first markdown token(if present) of the string or empty string otherwise
     *
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
