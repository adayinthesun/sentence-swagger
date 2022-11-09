package com.example.sentenceswagger.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SentenceEvaluationService {

    public boolean checkSentence(String input) {

        long quotationCount = input.chars().filter(c -> c == '\"' || c == '\'').count();
        if (quotationCount % 2 != 0) return false;

        Pattern numberPattern = Pattern.compile("([\\s,][\\d]{1}[\\s,])|([\\s,][1][0-2][\\s,])");
        Matcher numberMatcher = numberPattern.matcher(input);
        if(numberMatcher.find()) return false;

        Pattern textPattern = Pattern.compile("^[A-Z][^.]*[.?!]$");
        Matcher textMatcher = textPattern.matcher(input);
        return textMatcher.find();

    }
}
