package com.srzhio.service.emitters.tokenemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class StrongEmitter implements TokenEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Override
    public void emitToken(Token token, StringBuilder out) {
        generator.openStrong(out);
        tokenProcessor.recursiveProcessLine(out, token.getContent());
        generator.closeStrong(out);
    }
}
