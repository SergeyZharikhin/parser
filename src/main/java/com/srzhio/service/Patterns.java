package com.srzhio.service;

import java.util.regex.Pattern;

public final class Patterns {

    public static final Pattern NEW_LINE_SEPARATOR = Pattern.compile("\r\n?|\n");

    public static final Pattern EMPH_PATTERN = Pattern.compile("^[\\*](([^\\*]|[\\*][\\*][^\\*]+?[\\*][\\*])+?)[\\*](?!\\*)");

    public static final Pattern STRONG_PATTERN = Pattern.compile("^[\\*]{2}(([^\\*]|[\\*][^\\*]*[\\*])+?)[\\*]{2}(?!\\*)");

    public static final Pattern LINK_PATTERN = Pattern.compile("^\\[([^\\]]+)\\]\\(([^\\)]+)\\)(?!\\))");

    public static final Pattern HEADING_PATTERN = Pattern.compile("^(#{1,6}+).*");

    public static final String EMPH_START_SIGN = "*";
    public static final String STRONG_START_SIGN = "**";
    public static final String LINK_START_SIGN = "[";

    public static final Pattern TOKEN_START_SIGNS = Pattern.compile("\\" + EMPH_START_SIGN + "\\" + EMPH_START_SIGN +
                                                                    "|" + "\\" + EMPH_START_SIGN +
                                                                    "|" + "\\" + LINK_START_SIGN);
}
