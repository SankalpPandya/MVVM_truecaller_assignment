package com.example.truecallerassigment.utils;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public final class ApiResponse {

    public final Status status;

    @Nullable
    public final String data;

    @Nullable
    public final Throwable error;

    public final RequestId requestId;

    public enum  RequestId{
        tenthCharacterRequest,
        everyTenthCharacterRequest,
        wordCountRequest
    };

    private ApiResponse(Status status, @Nullable String data, @Nullable Throwable error,RequestId requestId) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.requestId = requestId;
    }

    public static ApiResponse loading(RequestId requestId) {
        return new ApiResponse(Status.LOADING, null, null,requestId);
    }

    public static ApiResponse success(@NonNull String data,RequestId requestId) {
        return new ApiResponse(Status.SUCCESS, data, null,requestId);
    }

    public static ApiResponse error(@NonNull Throwable error,RequestId requestId) {
        String errorMessage="Unknown error has occurred";
        if(error !=null ) {
            errorMessage = error.toString();
        }
        return new ApiResponse(Status.ERROR,  errorMessage, error,requestId);
    }
}
