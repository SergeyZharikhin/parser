package com.srzhio.service.emitters.lineemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class ParagraphEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Override
    public void emitLine(Line line, StringBuilder out) {
        generator.openParagraph(out);
        tokenProcessor.recursiveProcessLine(out, line.getContent());
        generator.closeParagraph(out);
    }
}
