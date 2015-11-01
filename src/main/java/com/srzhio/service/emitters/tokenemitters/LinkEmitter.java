package com.srzhio.service.emitters.tokenemitters;

import com.srzhio.service.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.MatchResult;

public class LinkEmitter implements TokenEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Override
    public void emitToken(Token token, StringBuilder out) {
        generator.openLink(out);
        emitLinkTag(out, token.getMatcher());
        generator.closeLink(out);
    }

    public void emitLinkTag(StringBuilder out, MatchResult matchResult) {
        String linkText = matchResult.group(1);
        String ahref = matchResult.group(2).trim();
        out.append(ahref).append("'>").append(linkText);
    }
}
