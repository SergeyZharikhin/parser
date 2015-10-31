package com.srzhio.service.builders.lineemitters;

import com.srzhio.service.Line;
import com.srzhio.service.LineType;

import java.util.HashMap;
import java.util.Map;

public class LineEmitterFactory {

    private static Map<LineType, LineEmitter> lineEmitters = new HashMap<>();

    static {
        lineEmitters.put(LineType.EMPH, new EmphasizeEmitter());
        lineEmitters.put(LineType.LINK, new LinkEmitter());
        lineEmitters.put(LineType.STRONG, new StrongEmitter());
    }

    public static LineEmitter getLineEmitter(Line line){
        return lineEmitters.get(line.getLineType());
    }

}
