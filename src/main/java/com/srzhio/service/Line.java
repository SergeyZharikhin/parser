package com.srzhio.service;

import java.util.regex.Matcher;

public class Line {

    private String content;
    private LineType lineType;
    private int headingDepth;

    public Line(String content) {
        this.lineType = determineLineType(content);
    }

    public LineType determineLineType(String content) {
        if (content.isEmpty()) {
            this.content = content;
            return LineType.EMPTY;
        }
        Matcher matcher = Patterns.HEADING_PATTERN.matcher(content);
        if (matcher.find()) {
            int hashesNumber = matcher.group(1).length();
            this.headingDepth = Math.min(hashesNumber, 6);
            this.content = content.substring(hashesNumber);
            return LineType.HEADING;
        }
        this.content = content;
        return LineType.PARAGRAPH;
    }

    public int getHeadingDepth() {
        return headingDepth;
    }

    public void setHeadingDepth(int headingDepth) {
        this.headingDepth = headingDepth;
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
}
