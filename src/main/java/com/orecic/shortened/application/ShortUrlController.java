package com.orecic.shortened.application;

import com.orecic.shortened.application.data.ShortUrlRequest;
import com.orecic.shortened.domain.service.ShortUrlService;
import com.orecic.shortened.infrastructure.ShortUrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ShortUrlController {

    Logger logger = LoggerFactory.getLogger(ShortUrlController.class);


    @Autowired
    private ShortUrlService shortUrlService;

    @RequestMapping(value = "/url", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    ResponseEntity<ShortUrlResponse> generateShortUrl(@RequestBody @Valid ShortUrlRequest shortUrlRequest) {
        logger.info("m=getShortUrl msg=handle-request request={}", shortUrlRequest.originalUrl());

        try {
            var response = shortUrlService.buildShortUrl(shortUrlRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(value = "/url/{alias}", method = RequestMethod.GET)
    ModelAndView getShortUrl(@PathVariable("alias") String alias) {
        var urlToRedirect = shortUrlService.getShortUrl(alias);
        return new ModelAndView("redirect:" + urlToRedirect);
    }

}
