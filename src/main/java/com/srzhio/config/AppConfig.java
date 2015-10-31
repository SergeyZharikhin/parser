package com.srzhio.config;

import com.srzhio.service.BlockType;
import com.srzhio.service.LineType;
import com.srzhio.service.builders.blockemitters.BlockEmitter;
import com.srzhio.service.builders.blockemitters.EmptyEmitter;
import com.srzhio.service.builders.blockemitters.HeadingEmitter;
import com.srzhio.service.builders.blockemitters.ParagraphEmitter;
import com.srzhio.service.builders.lineemitters.EmphasizeEmitter;
import com.srzhio.service.builders.lineemitters.LineEmitter;
import com.srzhio.service.builders.lineemitters.LinkEmitter;
import com.srzhio.service.builders.lineemitters.StrongEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean()
    public Map<BlockType, BlockEmitter> blockEmitters() {
        return new EnumMap<BlockType, BlockEmitter>(BlockType.class) {{
            put(BlockType.HEADING, headingEmitter());
            put(BlockType.PARAGRAPH, paragraphEmitter());
            put(BlockType.EMPTY, emptyEmitter());
        }};
    }

    @Bean()
    public Map<LineType, LineEmitter> lineEmitters() {
        Map<LineType, LineEmitter> linemitters = new EnumMap<>(LineType.class);
        linemitters.put(LineType.EMPH, emphasizeEmitter());
        linemitters.put(LineType.STRONG, strongEmitter());
        linemitters.put(LineType.LINK, linkEmitter());
        return linemitters;
    }

    @Bean
    public BlockEmitter headingEmitter() {
        return new HeadingEmitter();
    }

    @Bean
    public BlockEmitter paragraphEmitter() {
        return new ParagraphEmitter();
    }

    @Bean
    public BlockEmitter emptyEmitter() {
        return new EmptyEmitter();
    }

    @Bean
    public LineEmitter emphasizeEmitter() {
        return new EmphasizeEmitter();
    }

    @Bean
    public LineEmitter strongEmitter() {
        return new StrongEmitter();
    }

    @Bean
    public LineEmitter linkEmitter() {
        return new LinkEmitter();
    }


}
