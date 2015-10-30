package com.srzhio.service;

import java.util.regex.MatchResult;

public class MarkdownUnit {

    private MarkdownToken markdownToken;
    private MatchResult matchResult;

    public MarkdownUnit(MatchResult matchResult) {
        this.matchResult = matchResult;
        this.markdownToken = determineUnitType();
    }

    private MarkdownToken determineUnitType() {
        String content = matchResult.group();
        if (content.startsWith(Patterns.STRONG_START_SIGN)) {
            return MarkdownToken.STRONG;
        } else if (content.startsWith(Patterns.EMPH_START_SIGN)) {
            return MarkdownToken.EMPH;
        } else if (content.startsWith(Patterns.LINK_START_SIGN)) {
            return MarkdownToken.LINK;
        }
        return MarkdownToken.NONE;
    }

    public MarkdownToken getMarkdownToken() {
        return markdownToken;
    }

    public void setMarkdownToken(MarkdownToken markdownToken) {
        this.markdownToken = markdownToken;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }
}
