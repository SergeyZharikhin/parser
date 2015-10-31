package com.srzhio.service.builders.lineemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.Line;
import com.srzhio.service.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class StrongEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private LineProcessor lineProcessor;

    @Override
    public void buildline(Line line, StringBuilder out) {
        generator.openStrong(out);
        lineProcessor.recursiveProcessLine(out, line.getContent());
        generator.closeStrong(out);
    }
}
