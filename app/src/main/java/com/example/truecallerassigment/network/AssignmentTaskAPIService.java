package com.example.truecallerassigment.network;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class  AssignmentTaskAPIService{

    private static  String BASE_URL = "https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static <s> s createService(Class<s> serviceClass) {

        return retrofit.create(serviceClass);
    }
}
