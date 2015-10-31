package com.srzhio.service.builders.lineemitters;

import com.srzhio.service.Line;

public interface LineEmitter {

    void emitLine(Line line, StringBuilder out);
}
