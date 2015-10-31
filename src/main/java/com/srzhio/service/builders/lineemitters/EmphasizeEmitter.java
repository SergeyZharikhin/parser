package com.srzhio.service.builders.lineemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.Line;
import com.srzhio.service.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmphasizeEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private LineProcessor lineProcessor;

    @Override
    public void emitLine(Line line, StringBuilder out) {
        generator.openEmphasis(out);
        lineProcessor.recursiveProcessLine(out, line.getContent());
        generator.closeEmphasis(out);
    }
}
