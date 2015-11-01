package com.srzhio.service;

import com.srzhio.service.emitters.lineemitters.Line;
import com.srzhio.service.emitters.lineemitters.LineEmitter;
import com.srzhio.service.emitters.lineemitters.LineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.srzhio.service.Patterns.NEW_LINE_SEPARATOR;

@Service
public class LineProcessor {

    @Resource(name = "lineEmitters")
    private Map<LineType, LineEmitter> lineEmitters;

    @Autowired
    HtmlGenerator generator;

    public String process(String input) {
        StringBuilder out = new StringBuilder();
        generator.openHtmlBody(out);
        for (String string : NEW_LINE_SEPARATOR.split(input)) {
            Line line = new Line(string);
            LineEmitter blockEmitter = lineEmitters.get(line.getLineType());
            blockEmitter.emitLine(line, out);
        }
        generator.closeHtmlBody(out);
        return out.toString();
    }
}
