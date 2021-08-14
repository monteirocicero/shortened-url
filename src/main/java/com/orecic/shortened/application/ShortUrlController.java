package com.orecic.shortened.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShortUrlController {

    @RequestMapping("/url")
    public @ResponseBody String getShortUrl() {
        return "Hello World";
    }

}
