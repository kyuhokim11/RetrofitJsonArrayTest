package com.example.retrofitjsonarraytest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Hairview {
    String hairviewApiUrl = "http://222.106.61.71:8000/";

    //회원가입을 처리하는 API
    /*전송방법 : POST, Field타입
     * request시 보내는 값 : username, password, email, first_name, last_name
     * api Call 이름 : userRegist
     * response 값 : id, username, email, first_name, last_name */
    @FormUrlEncoded
    @POST("api/v1/accounts/")
    Call<String> userRegist(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name
    );

    //로그인 처리하는 API
    /* 전송방법 : POST, Field타입
     * request시 보내는 값 : username, password
     * api Call 이름 : userLogin
     * response 값 : id(회원 고유번호), token 응답 */
    @FormUrlEncoded
    @POST("api/v1/accounts/login")
    Call<String> userLogin(

            @Field("username") String username,
            @Field("password") String password
    );

    //회원정보 조회하는 API
    /* 전송방법 : GET
     * request시 보내는 값 :
     * Header에 Autorization(token 값)
     * account {id}값 : id(회원 고유번호)
     * api Call 이름 : userinfo
     * response 값 : id, username, email, first_name, last_name 응답 */
    @GET("api/v1/accounts/{id}")
    Call<String> userinfo(
            @Header("Authorization") String autorization,
            @Path("id") String id
    );

    //회원정보 수정(update)하는 API
    /* 전송방법 : PATCH(지정한 변수값만 수정)
     * request시 보내는 값 :
     * Header에 Autorization(token 값)
     * account {id}값 : id(회원 고유번호)
     * 수정할 변수와 값(ex, first_name, 아무개)
     * api Call 이름 : updateInfo
     * response 값 : id, username, email, first_name, last_name 응답 */
    @FormUrlEncoded
    @PATCH("api/v1/accounts/{id}")
    Call<String> updateInfo(
            @Header("Authorization") String autorization,
            @Field("first_name") String first_name,
            @Path("id") String id
    );


    //회원정보 삭제(계정탈퇴)하는 API
    /* 전송방법 : PATCH(지정한 변수값만 수정)
     * request시 보내는 값 :
     * Header에 Autorization(token 값)
     * account {id}값 : id(회원 고유번호)
     * 수정할 변수와 값(ex, first_name, 아무개)
     * api Call 이름 : updateInfo
     * response 값 : id, username, email, first_name, last_name 응답 */
    @DELETE("api/v1/accounts/{id}")
    Call<String> deleteUser(
            @Header("Authorization") String autorization,
            @Path("id") String id
    );


    //회원의 프로필정보 조회하는 API
    /* 전송방법 : GET
     * request시 보내는 값 :
     * Header에 Autorization(token 값)
     * account {id}값 : id(회원 고유번호)
     * api Call 이름 : userProfile
     * response 값 :  응답 */
    @GET("api/v1/profiles/")
    Call<String> userProfile(
            @Header("Authorization") String autorization
    );

    //회원의 프로필정보 조회하는 API
    /* 전송방법 : GET
     * request시 보내는 값 :
     * Header에 Autorization(token 값)
     * account {id}값 : id(회원 고유번호)
     * api Call 이름 : userProfile
     * response 값 :  응답 */
    @GET("api/v1/profiles/")
    Call<ArrayList<ProfileItem>> userProfileParse(
            @Header("Authorization") String autorization
    );


    //프로필 등록을 처리하는 API
    /*전송방법 : POST, Field타입
     * request시 보내는 값 : account(id), nickname
     * * Header에 Autorization(token 값)
     * api Call 이름 : profileRegist
     * response 값 :  */
    @FormUrlEncoded
    @POST("api/v1/profiles/")
    Call<String> profileRegist(
            @Header("Authorization") String autorization,
            @Field("account") String account,
            @Field("nickname") String nickname
    );
    //프로필 삭제하는 API
    /* 전송방법 : PATCH(지정한 변수값만 수정)
     * request시 보내는 값 :
     * Header에 Autorization(token 값)
     * account {id}값 : id(회원 고유번호)
     * 수정할 변수와 값(ex, first_name, 아무개)
     * api Call 이름 : updateInfo
     * response 값 : id, username, email, first_name, last_name 응답 */
    @DELETE("api/v1/profiles/{id}")
    Call<String> deleteProfile(
            @Header("Authorization") String autorization,
            @Path("id") String id
    );



}
