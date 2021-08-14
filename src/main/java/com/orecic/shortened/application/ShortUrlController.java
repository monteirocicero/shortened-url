package com.orecic.shortened.application;

import com.orecic.shortened.application.data.ShortUrlRequest;
import com.orecic.shortened.domain.ShortUrlService;
import com.orecic.shortened.infrastructure.ShortUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    @RequestMapping(value = "/url", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<ShortUrlResponse> getShortUrl(@RequestBody @Valid ShortUrlRequest shortUrlRequest) {
        var response = shortUrlService.getShortUrl(shortUrlRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
