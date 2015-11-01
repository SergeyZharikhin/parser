package com.srzhio.config;

import com.srzhio.service.emitters.lineemitters.LineType;
import com.srzhio.service.emitters.tokenemitters.TokenType;
import com.srzhio.service.emitters.lineemitters.LineEmitter;
import com.srzhio.service.emitters.lineemitters.EmptyEmitter;
import com.srzhio.service.emitters.lineemitters.HeadingEmitter;
import com.srzhio.service.emitters.lineemitters.ParagraphEmitter;
import com.srzhio.service.emitters.tokenemitters.EmphasizeEmitter;
import com.srzhio.service.emitters.tokenemitters.TokenEmitter;
import com.srzhio.service.emitters.tokenemitters.LinkEmitter;
import com.srzhio.service.emitters.tokenemitters.StrongEmitter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean()
    public Map<LineType, LineEmitter> lineEmitters() {
        EnumMap<LineType, LineEmitter> lineEmittes = new EnumMap<>(LineType.class);
        lineEmittes.put(LineType.HEADING, headingEmitter());
        lineEmittes.put(LineType.PARAGRAPH, paragraphEmitter());
        lineEmittes.put(LineType.EMPTY, emptyEmitter());
        return lineEmittes;
    }

    @Bean()
    public Map<TokenType, TokenEmitter> tokenEmitters() {
        Map<TokenType, TokenEmitter> tokenEmitters = new EnumMap<>(TokenType.class);
        tokenEmitters.put(TokenType.EMPH, emphasizeEmitter());
        tokenEmitters.put(TokenType.STRONG, strongEmitter());
        tokenEmitters.put(TokenType.LINK, linkEmitter());
        return tokenEmitters;
    }

    @Bean
    public LineEmitter headingEmitter() {
        return new HeadingEmitter();
    }

    @Bean
    public LineEmitter paragraphEmitter() {
        return new ParagraphEmitter();
    }

    @Bean
    public LineEmitter emptyEmitter() {
        return new EmptyEmitter();
    }

    @Bean
    public TokenEmitter emphasizeEmitter() {
        return new EmphasizeEmitter();
    }

    @Bean
    public TokenEmitter strongEmitter() {
        return new StrongEmitter();
    }

    @Bean
    public TokenEmitter linkEmitter() {
        return new LinkEmitter();
    }
}
