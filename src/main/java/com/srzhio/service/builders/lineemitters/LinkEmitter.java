package com.srzhio.service.builders.lineemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.MatchResult;

@Service
public class LinkEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Override
    public void emitLine(Line line, StringBuilder out) {
        generator.openLink(out);
        emitLinkTag(out, line.getMatcher());
        generator.closeLink(out);
    }

    public void emitLinkTag(StringBuilder out, MatchResult matchResult) {
        String linkText = matchResult.group(1);
        String ahref = matchResult.group(2).trim();
        out.append(ahref).append("'>").append(linkText);
    }
}
