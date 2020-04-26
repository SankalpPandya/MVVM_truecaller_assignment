package com.example.truecallerassigment.viewmodel.controller;


public class TrueCaller10thCharacterHelper implements ProblemUseCaseHelperInterface {

    private String strResponse;
    private static TrueCaller10thCharacterHelper trueCaller10thCharacterHelper;

    private TrueCaller10thCharacterHelper(){
    }

    public void setValueTrueCaller10thCharacterHelper(String response){
        this.strResponse = response;
    }

    public static TrueCaller10thCharacterHelper getInstance(){
        if(trueCaller10thCharacterHelper == null){
            trueCaller10thCharacterHelper = new TrueCaller10thCharacterHelper();
        }
        return trueCaller10thCharacterHelper;
    }

    @Override
    public StringBuilder getFormattedData() {
        StringBuilder tengthChar = new StringBuilder("Character on 10th position on response is: ");
        if (strResponse != null) {
            tengthChar.append(strResponse.charAt(9));
        }
        return tengthChar;
    }
}
