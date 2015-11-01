package com.srzhio.service;

import com.srzhio.service.emitters.MatchingUtils;
import com.srzhio.service.emitters.tokenemitters.Token;
import com.srzhio.service.emitters.tokenemitters.TokenEmitter;
import com.srzhio.service.emitters.tokenemitters.TokenType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.regex.Matcher;

import static com.srzhio.service.Patterns.TOKEN_START_SIGNS;

@Service
public class TokenProcessor {

    @Resource(name = "tokenEmitters")
    private Map<TokenType, TokenEmitter> tokenEmitters;

    public void process(StringBuilder out, String content) {
        StringBuilder temp = new StringBuilder();
        while (!content.isEmpty()) {
            String subLine = MatchingUtils.findSubstringFromFirstMatchedToken(content);
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
                Token subPortion = new Token(matchedText, matcher);
                TokenEmitter tokenEmitter = tokenEmitters.get(subPortion.getTokenType());
                tokenEmitter.emitToken(subPortion, temp);
                out.append(temp);
                content = content.substring(tokenPosition + matchedTextLength); // step over emitted text
                continue;
            }
            out.append(content.substring(0, tokenPosition + 1)); // write unmarked text after token
            content = content.substring(tokenPosition + 1);
        }
    }
}
