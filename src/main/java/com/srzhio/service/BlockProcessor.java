package com.srzhio.service;

import com.srzhio.service.builders.blockemitters.BlockEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.srzhio.service.Patterns.NEW_LINE_SEPARATOR;

@Service
public class BlockProcessor {

    @Resource(name = "blockEmitters")
    private Map<BlockType, BlockEmitter> blockEmitters;

    @Autowired
    HtmlGenerator generator;

    public String process(String input) {
        StringBuilder out = new StringBuilder();
        generator.openHtmlBody(out);
        for (String string : NEW_LINE_SEPARATOR.split(input)) {
            Block block = new Block(string);
            BlockEmitter blockEmitter = blockEmitters.get(block.getBlockType());
            blockEmitter.emitBlock(block, out);
        }
        generator.closeHtmlBody(out);
        return out.toString();
    }
}
