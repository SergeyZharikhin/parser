package com.srzhio.service;

import com.srzhio.service.builders.blockemitters.BlockEmitter;
import com.srzhio.service.builders.blockemitters.BlockEmitterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.srzhio.service.Patterns.NEW_LINE_SEPARATOR;

@Service
public class BlockProcessor {

    @Autowired
    HtmlGenerator generator;

    public String process(String input) {
        StringBuilder out = new StringBuilder();
        generator.openHtmlBody(out);
        for (String string : NEW_LINE_SEPARATOR.split(input)) {
            Block block = new Block(string);
            BlockEmitter blockEmitter = BlockEmitterFactory.getBlockEmitter(block);
            blockEmitter.emitBlock(block, out);
        }
        generator.closeHtmlBody(out);
        return out.toString();
    }
}
