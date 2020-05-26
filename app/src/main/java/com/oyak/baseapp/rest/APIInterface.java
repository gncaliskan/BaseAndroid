package com.oyak.baseapp.rest;

import com.oyak.baseapp.model.BaseResponse;
import com.oyak.baseapp.rest.Response.*;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    Call<BaseResponse<UserResponse>> login(
        @Field("token") String token,
        @Field("type") String type
    );

}
