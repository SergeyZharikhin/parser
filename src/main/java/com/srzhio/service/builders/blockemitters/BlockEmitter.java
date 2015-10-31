package com.srzhio.service.builders.blockemitters;

import com.srzhio.service.Block;

public interface BlockEmitter {

    void emitBlock(Block block, StringBuilder out);
}
