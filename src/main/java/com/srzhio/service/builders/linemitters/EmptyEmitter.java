package com.srzhio.service.builders.linemitters;

import com.srzhio.service.HtmlGenerator;
import org.springframework.beans.factory.annotation.Autowired;

public class EmptyEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Override
    public void emitLine(Line line, StringBuilder out) {
        generator.breakLine(out);
    }
}
