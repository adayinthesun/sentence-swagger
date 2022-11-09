package com.example.sentenceswagger.services

import spock.lang.Specification

class SentenceEvaluationServiceSpec extends Specification {
    SentenceEvaluationService sentenceEvaluationService = new SentenceEvaluationService()

    def "Sentence example #description should evaluate to #isValid "() {

        when:
        def result = sentenceEvaluationService.checkSentence(example)

        then:
        result == isValid

        where:
        description                   | example                                            | isValid
        "2 quotation marks"           | "The quick brown fox said \"hello Mr lazy dog\"."  | true
        "without numbers"             | "The quick brown fox said hello Mr lazy dog."      | true
        "with 13"                     | "One lazy dog is too few, 13 is too many."         | true
        "with thirteen"               | "One lazy dog is too few, thirteen is too many."   | true
        "ending with question mark"   | "How many \"lazy dogs\" are there?"                | true

        "full stop after Mr"          | "The quick brown fox said \"hello Mr. lazy dog\"." | false
        "first word not capitalized " | "the quick brown fox said \"hello Mr lazy dog\"."  | false
        "odd no of quotation marks"   | "The quick brown fox said \"hello Mr lazy dog."    | false
        "the number 12"               | "One lazy dog is too few, 12 is too many."         | false
        "the numbers 11 and 12"       | "Are there 11, 12, or 13 lazy dogs?"               | false
        "the numbers 1, 2 and 9"      | "Are there 1, 2, or 9 lazy dogs?"                  | false
        "no ending punctuation mark"  | "There is no punctuation in this sentence"         | false

    }
}
