package com.srzhio.service.emitters.tokenemitters;

import com.srzhio.service.Patterns;

import java.util.regex.Matcher;

public class Token {

    private String content;
    private TokenType tokenType;
    private Matcher matcher;

    public Token(String content, Matcher matcher) {
        init(content, matcher);
    }

    private void init(String content, Matcher matcher) {
        this.content = content;
        this.matcher = matcher;
        this.tokenType = determineLineTypeBasedOnMatchedPattern(matcher);
    }

    public TokenType determineLineTypeBasedOnMatchedPattern(Matcher matcher) {
        if (matcher.pattern().equals(Patterns.EMPH_PATTERN)) {
            return TokenType.EMPH;
        } else if (matcher.pattern().equals(Patterns.STRONG_PATTERN)) {
            return TokenType.STRONG;
        }
        return TokenType.LINK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }
}