package com.srzhio.service.builders.blockemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.Block;
import com.srzhio.service.builders.blockemitters.BlockEmitter;
import org.springframework.beans.factory.annotation.Autowired;

public class EmptyEmitter implements BlockEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Override
    public void emitBlock(Block block, StringBuilder out) {
        generator.breakLine(out);
    }
}
