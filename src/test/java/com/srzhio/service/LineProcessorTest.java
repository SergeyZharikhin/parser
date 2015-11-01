package com.srzhio.service;

import com.srzhio.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, AppConfig.class})
public class LineProcessorTest {

    @Autowired
    private LineProcessor lineProcessor;

    public static final String HEADING1_MARKDOWN = "#Heading";
    public static final String HEADING1_HTML = "<h1>Heading</h1>";

    public static final String HEADING6_MARKDOWN = "#######Heading";
    public static final String HEADING6_HTML = "<h6>#Heading</h6>";

    public static final String LINK_MARKDOWN = "[link](url)";
    public static final String LINK_HTML = "<a href='url'>link</a>";

    public static final String STRONG_MARKDOWN = "**boldtext**";
    public static final String STRONG_HTML = "<strong>boldtext</strong>";

    public static final String EMPH_MARKDOWN = "*italics*";
    public static final String EMPH_HTML = "<em>italics</em>";

    public static final String STRONG_IN_EMPH_MARKDOWN = "*italics**strong**italics*";
    public static final String STRONG_IN_EMPH_HTML = "<em>italics<strong>strong</strong>italics</em>";

    public static final String EMPH_IN_STRONG_MARKDOWN = "**strong*italics*strong**";
    public static final String EMPH_IN_STRONG_HTML = "<strong>strong<em>italics</em>strong</strong>";

    @Test
    public void shouldParseHeadingDepth1() throws Exception {
        //given

        //when
        String result = lineProcessor.process(HEADING1_MARKDOWN);

        //then
        assertThat(result, is(wrapInHtmlBody(HEADING1_HTML)));
    }

    @Test
    public void shouldParseHeadingDepthOver6() throws Exception {
        //given

        //when
        String result = lineProcessor.process(HEADING6_MARKDOWN);

        //then
        assertThat(result, is(wrapInHtmlBody(HEADING6_HTML)));
    }

    @Test
    public void shouldParseSimpleLink() throws Exception {
        //given

        //when
        String result = lineProcessor.process(LINK_MARKDOWN);

        //then
        assertThat(result, is(wrapInParagraph(LINK_HTML)));
    }

    @Test
    public void shouldParseStrong() throws Exception {
        //given

        //when
        String result = lineProcessor.process(STRONG_MARKDOWN);

        //then
        assertThat(result, is(wrapInParagraph(STRONG_HTML)));
    }

    @Test
    public void shouldParseEmphasized() throws Exception {
        //given

        //when
        String result = lineProcessor.process(EMPH_MARKDOWN);

        //then
        assertThat(result, is(wrapInParagraph(EMPH_HTML)));
    }

    @Test
    public void shouldParseStrongInEmphasized() throws Exception {
        //given

        //when
        String result = lineProcessor.process(STRONG_IN_EMPH_MARKDOWN);

        //then
        assertThat(result, is(wrapInParagraph(STRONG_IN_EMPH_HTML)));
    }

    @Test
    public void shouldParseEmphasizedInStrong() throws Exception {
        //given

        //when
        String result = lineProcessor.process(EMPH_IN_STRONG_MARKDOWN);

        //then
        assertThat(result, is(wrapInParagraph(EMPH_IN_STRONG_HTML)));
    }

    public String wrapInParagraph(String input) {
        return "<html><body><p>" + input + "</p></body></html>";
    }

    public String wrapInHtmlBody(String input) {
        return "<html><body>" + input + "</body></html>";
    }

}