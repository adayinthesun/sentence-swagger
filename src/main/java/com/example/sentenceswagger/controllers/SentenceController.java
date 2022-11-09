package com.example.sentenceswagger.controllers;

import com.example.sentenceswagger.services.SentenceEvaluationService;
import com.example.sentenceswagger.exceptions.SentenceEvaluationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SentenceController {

    @Autowired
    SentenceEvaluationService sentenceEvaluationService;

    @RequestMapping(value = "/sentenceevaluator", method = RequestMethod.GET, produces = "application/json")
    public String validateSentence(@RequestParam("inputSentence") String sentence) {
        try {
            log.info("Entering the validateSentence method in the controller.");
            boolean isSentenceValid = sentenceEvaluationService.checkSentence(sentence);
            if (isSentenceValid) {
                return "\"This sentence is valid as per the rules.\"";
            } else {
                return "\"This sentence is invalid as per the rules.\"";
            }
        } catch (Exception ex) {
            log.error("The sentence evaluation service threw an exception: "+ ex);
            throw new SentenceEvaluationException("The sentence evaluation service threw an exception.");
        }
    }
}
