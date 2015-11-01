package com.srzhio.service.emitters.lineemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class HeadingEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private TokenProcessor tokenProcessor;

    @Override
    public void emitLine(Line line, StringBuilder out) {
        generator.openHeading(out, line.getHeadingDepth());
        tokenProcessor.recursiveProcessLine(out, line.getContent());
        generator.closeHeading(out, line.getHeadingDepth());
    }
}
