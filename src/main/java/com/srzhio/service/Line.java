package com.srzhio.service;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.srzhio.service.Patterns.*;

public class Line {

    private String content;
    private LineType lineType;
    private MatchResult matchResult;

    public Line(String content) {
        init(content);
    }

    private void  init (String content) {
        Matcher matcher = determineMatcherForContent(content);
        if (matcher.find()) {
            this.matchResult = matcher.toMatchResult();
            this.lineType = determineLineTypeBasedOnMatchedPattern(matcher.pattern());
        }
        else {
            this.content = matchResult.group();
            this.lineType = LineType.NONE;
        }
    }

    public LineType determineLineTypeBasedOnMatchedPattern(Pattern pattern){
            return null;
    }

    public Matcher determineMatcherForContent(String content) {
        if (content.startsWith(Patterns.STRONG_START_SIGN)) {
            return STRONG_PATTERN.matcher(content);
        } else if (content.startsWith(Patterns.EMPH_START_SIGN)) {
            return EMPH_PATTERN.matcher(content);
        }
        return LINK_PATTERN.matcher(content);
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

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }
}
