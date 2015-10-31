package com.srzhio.controller;

import com.srzhio.service.BlockProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParserController {

    @Autowired
    BlockProcessor blockProcessor;

    @RequestMapping(value="/parse", method=RequestMethod.GET)
    public String parserForm(Model model) {
        model.addAttribute("content", "");
        return "parser";
    }

    @RequestMapping(value = "/parse", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String parserInput(@RequestParam String input){
        return blockProcessor.process(input);
    }

}
