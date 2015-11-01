package com.srzhio.service.builders.linemitters;

import com.srzhio.service.Patterns;

import java.util.regex.Matcher;

public class Line {

    private String content;
    private LineType lineType;
    private int headingDepth;

    public Line(String content) {
        init(content);
    }

    public void init(String content) {
        if (content.isEmpty()) {
            this.content = content;
            this.lineType = LineType.EMPTY;
            return;
        }
        Matcher matcher = Patterns.HEADING_PATTERN.matcher(content);
        if (matcher.find()) {
            int hashesNumber = matcher.group(1).length();
            this.headingDepth = Math.min(hashesNumber, 6);
            this.content = content.substring(hashesNumber);
            this.lineType = LineType.HEADING;
            return;
        }
        this.content = content;
        this.lineType = LineType.PARAGRAPH;
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
