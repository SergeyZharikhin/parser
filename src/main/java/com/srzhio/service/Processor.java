package com.srzhio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Processor {

    @Autowired
    LineEmitter lineEmitter;

    public String process(String input) {
        StringBuilder out = new StringBuilder();
        lineEmitter.emit(out, input.trim());
        return out.toString();
    }
}
