package com.srzhio.service.builders.blockemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.Block;
import com.srzhio.service.LineProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ParagraphEmitter implements BlockEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Autowired
    private LineProcessor lineProcessor;

    @Override
    public void emitBlock(Block block, StringBuilder out) {
        generator.openParagraph(out);
        lineProcessor.recursiveProcessLine(out, block.getContent());
        generator.closeParagraph(out);
    }
}
