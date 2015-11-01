package com.srzhio.service.emitters;

import com.srzhio.service.Patterns;

import java.util.regex.Matcher;

import static com.srzhio.service.Patterns.*;

public class MatchingUtils {

    /**
     * Determines matcher based on starting marker of a content
     *
     * @param content input string
     * @return corresponding matcher
     */
    public static Matcher determineMatcherForContent(String content) {
        if (content.startsWith(Patterns.STRONG_START_SIGN)) {
            return STRONG_PATTERN.matcher(content);
        } else if (content.startsWith(Patterns.EMPH_START_SIGN)) {
            return EMPH_PATTERN.matcher(content);
        }
        return LINK_PATTERN.matcher(content);
    }

    /**
     * Returns a substring starting from first markdown token of the specified string(if present) or the specified
     * string itself otherwise
     *
     * @param line input string
     * @return resulting string
     */
    public static String findSubstringFromFirstMatchedToken(String line) {
        Matcher firstMatch = TOKEN_START_SIGNS.matcher(line);
        if (firstMatch.find()) {
            return line.substring(firstMatch.start());
        }
        return "";
    }
}
