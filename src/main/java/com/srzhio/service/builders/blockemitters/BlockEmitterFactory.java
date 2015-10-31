package com.srzhio.service.builders.blockemitters;

import com.srzhio.service.Block;
import com.srzhio.service.BlockType;

import java.util.HashMap;
import java.util.Map;

public class BlockEmitterFactory {

    private static Map<BlockType, BlockEmitter> blockEmitters = new HashMap<>();

    static {
        blockEmitters.put(BlockType.HEADING, new HeadingEmitter());
        blockEmitters.put(BlockType.PARAGRAPH, new ParagraphEmitter());
        blockEmitters.put(BlockType.EMPTY, new EmptyEmitter());
    }

    public static BlockEmitter getBlockEmitter(Block block) {
        return blockEmitters.get(block.getBlockType());
    }

}
