package com.srzhio.service.emitters.tokenemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class EmphasizeEmitter implements TokenEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Override
    public void emitToken(Token token, StringBuilder out) {
        generator.openEmphasis(out);
        tokenProcessor.process(out, token.getContent());
        generator.closeEmphasis(out);
    }
}
