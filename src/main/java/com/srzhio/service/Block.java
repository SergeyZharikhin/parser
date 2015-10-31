package com.srzhio.service;

import java.util.regex.Matcher;

public class Block {

    private String content;
    private BlockType blockType;
    private int headingDepth;

    public Block(String content) {
        init(content);
    }

    public void init(String content) {
        if (content.isEmpty()) {
            this.content = content;
            this.blockType = BlockType.EMPTY;
            return;
        }
        Matcher matcher = Patterns.HEADING_PATTERN.matcher(content);
        if (matcher.find()) {
            int hashesNumber = matcher.group(1).length();
            this.headingDepth = Math.min(hashesNumber, 6);
            this.content = content.substring(hashesNumber);
            this.blockType = BlockType.HEADING;
            return;
        }
        this.content = content;
        this.blockType = BlockType.PARAGRAPH;
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

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }
}
