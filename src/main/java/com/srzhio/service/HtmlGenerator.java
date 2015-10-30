package com.srzhio.service;

import org.springframework.stereotype.Service;

@Service
public class HtmlGenerator {

    public void openHtmlBody(final StringBuilder out) {
        out.append("<html><body>");
    }

    public void closeHtmlBody(final StringBuilder out) {
        out.append("</body></html>");
    }

    public void breakLine(final StringBuilder out) {
        out.append("</br>");
    }

    public void openParagraph(final StringBuilder out) {
        out.append("<p>");
    }

    public void closeParagraph(final StringBuilder out) {
        out.append("</p>");
    }

    public void openHeading(final StringBuilder out, final int headingDepth) {
        out.append("<h").append(headingDepth).append(">");
    }

    public void closeHeading(final StringBuilder out, final int headingDepth) {
        out.append("</h").append(headingDepth).append(">");
    }

    public void openStrong(final StringBuilder out) {
        out.append("<strong>");
    }

    public void closeStrong(final StringBuilder out) {
        out.append("</strong>");
    }

    public void openEmphasis(final StringBuilder out) {
        out.append("<em>");
    }

    public void closeEmphasis(final StringBuilder out) {
        out.append("</em>");
    }

    public void openLink(final StringBuilder out) {
        out.append("<a href='");
    }

    public void closeLink(final StringBuilder out) {
        out.append("</a>");
    }
}
