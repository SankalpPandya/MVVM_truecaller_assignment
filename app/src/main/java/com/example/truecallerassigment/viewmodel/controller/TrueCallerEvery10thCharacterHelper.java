package com.example.truecallerassigment.viewmodel.controller;
import java.util.LinkedHashMap;
import java.util.Map;


public class TrueCallerEvery10thCharacterHelper implements ProblemUseCaseHelperInterface {

    private String strResponse;

    private static TrueCallerEvery10thCharacterHelper trueCaller10thCharacterHelper;

    private TrueCallerEvery10thCharacterHelper(){
    }

    public void setValueTrueCallerEvery10thCharacterHelper(String response) {
        this.strResponse = response;
    }

    public static TrueCallerEvery10thCharacterHelper getInstance(){
        if(trueCaller10thCharacterHelper == null){
            trueCaller10thCharacterHelper = new TrueCallerEvery10thCharacterHelper();
        }
        return trueCaller10thCharacterHelper;
    }

    @Override
    public Map<Integer, Character> getFormattedData() {
        Map<Integer, Character> trueCallerEvery10thCharMap = new LinkedHashMap<Integer, Character>();
        for (int i = 9; i < strResponse.length(); i = i + 10) {
            trueCallerEvery10thCharMap.put(i, strResponse.charAt(i));
        }
        return trueCallerEvery10thCharMap;
    }
}
