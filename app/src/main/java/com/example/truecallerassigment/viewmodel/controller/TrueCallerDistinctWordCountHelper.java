package com.example.truecallerassigment.viewmodel.controller;

import android.util.Log;

import java.util.LinkedHashMap;
import java.util.Map;


public class TrueCallerDistinctWordCountHelper implements ProblemUseCaseHelperInterface {

    private String strResponse;
    private static TrueCallerDistinctWordCountHelper trueCallerDistinctWordCountHelper;

    private TrueCallerDistinctWordCountHelper(){

    }
    public static TrueCallerDistinctWordCountHelper getInstance(){
        if(trueCallerDistinctWordCountHelper == null){
            trueCallerDistinctWordCountHelper = new TrueCallerDistinctWordCountHelper();
        }
        return trueCallerDistinctWordCountHelper;
    }

    @Override
    public Map<String, Integer> getFormattedData() {

        Map<String, Integer> wordCountMapping = new LinkedHashMap<String, Integer>();
        String[] splits = strResponse.split("\\s+");

        for (String word : splits) {
            Integer wordCount = wordCountMapping.get(word);
            if (wordCount == null) {
                wordCount = 0;
                Log.d(this.getClass().getName(), " word --" + word + " is unique --");
            } else {
                Log.d(this.getClass().getName(), " word --" + word + " already exists in map--");
            }
            wordCountMapping.put(word, wordCount + 1);
        }

        Log.d(this.getClass().getName(), " total word count is  --" + wordCountMapping.keySet().size());

        return wordCountMapping;
    }

    public void setValueTrueCallerDistinctWordCountHelper(String response) {
        this.strResponse = response;
    }
}