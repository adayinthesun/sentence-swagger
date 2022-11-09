package com.example.sentenceswagger.controllers

import com.example.sentenceswagger.exceptions.SentenceEvaluationException
import com.example.sentenceswagger.services.SentenceEvaluationService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.util.regex.PatternSyntaxException

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class SentenceControllerSpec extends Specification {

    SentenceController sentenceController
    MockMvc mockMvc
    def validSentenceResponse = "\"This sentence is valid as per the rules.\""
    def invalidSentenceResponse = "\"This sentence is invalid as per the rules.\""

    void setup() {
        sentenceController = new SentenceController(sentenceEvaluationService: Mock(SentenceEvaluationService))
        mockMvc = MockMvcBuilders.standaloneSetup(sentenceController).build()
    }

    def "validateSentence returns the correct response for a valid sentence"() {

        when:
        def response = mockMvc.perform(get("/sentenceevaluator?inputSentence=Valid Sentence.").contentType(APPLICATION_JSON))

        then:
        1 * sentenceController.sentenceEvaluationService.checkSentence("Valid Sentence.") >> true
        response.andReturn().getResponse().getStatus() == 200
        response.andReturn().getResponse().getContentAsString() == validSentenceResponse
    }

    def "validateSentence returns the correct response for an invalid sentence"() {

        when:
        def response = mockMvc.perform(get("/sentenceevaluator?inputSentence=invalid 3 sentence").contentType(APPLICATION_JSON))

        then:
        1 * sentenceController.sentenceEvaluationService.checkSentence("invalid 3 sentence") >> false
        response.andReturn().getResponse().getStatus() == 200
        response.andReturn().getResponse().getContentAsString() == invalidSentenceResponse
    }

    def "validateSentence returns an exception"() {
        when:
        mockMvc.perform(get("/sentenceevaluator?inputSentence=invalid 3 sentence").contentType(APPLICATION_JSON))

        then:
        1 * sentenceController.sentenceEvaluationService.checkSentence("invalid 3 sentence") >> { throw new PatternSyntaxException("","",-1)}
        thrown Exception
    }
}
