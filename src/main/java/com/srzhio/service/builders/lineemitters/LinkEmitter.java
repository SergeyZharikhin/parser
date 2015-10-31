package com.srzhio.service.builders.lineemitters;

import com.srzhio.service.HtmlGenerator;
import com.srzhio.service.Line;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.MatchResult;

public class LinkEmitter implements LineEmitter {

    @Autowired
    private HtmlGenerator generator;

    @Override
    public void buildline(Line line, StringBuilder out) {
        generator.openLink(out);
        emitLinkTag(out, line.getMatchResult());
        generator.closeLink(out);
    }

    public void emitLinkTag(StringBuilder out, MatchResult matchResult) {
        String linkText = matchResult.group(1);
        String ahref = matchResult.group(2).trim();
        out.append(ahref).append("'>").append(linkText);
    }
}
