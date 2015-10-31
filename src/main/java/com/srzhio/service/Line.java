package com.srzhio.service;

import java.util.regex.Matcher;

public class Line {

    private String content;
    private LineType lineType;
    private Matcher matcher;

    public Line(String content, Matcher matcher) {
        init(content, matcher);
    }

    private void init(String content, Matcher matcher) {
        this.content = content;
        this.matcher = matcher;
        this.lineType = determineLineTypeBasedOnMatchedPattern(matcher);
    }

    public LineType determineLineTypeBasedOnMatchedPattern(Matcher matcher) {
        if (matcher.pattern().equals(Patterns.EMPH_PATTERN)) {
            return LineType.EMPH;
        } else if (matcher.pattern().equals(Patterns.STRONG_PATTERN)) {
            return LineType.STRONG;
        }
        return LineType.LINK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }
}