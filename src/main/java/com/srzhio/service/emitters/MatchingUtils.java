package com.srzhio.service.emitters;

import com.srzhio.service.Patterns;

import java.util.regex.Matcher;

import static com.srzhio.service.Patterns.*;

public class MatchingUtils {

    public static Matcher determineMatcherForContent(String content) {
        if (content.startsWith(Patterns.STRONG_START_SIGN)) {
            return STRONG_PATTERN.matcher(content);
        } else if (content.startsWith(Patterns.EMPH_START_SIGN)) {
            return EMPH_PATTERN.matcher(content);
        }
        return LINK_PATTERN.matcher(content);
    }
}
