package com.example.retrofitjsonarraytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    TextView tvJsonObject1, tvJsonObject2, tvJsonObject3, tvJsonArr;
    Button btnLoad;
    String token = "token 83dcdadebe362bd32678a0c7ab36202feb998e84";
    String textJsonArr;
    ArrayList<ProfileItem> arrayJsonArr;
    String textJsonOb1;
    String textJsonOb2;
    String textJsonOb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJsonArr = findViewById(R.id.tv_jsonA);
        tvJsonObject1 = findViewById(R.id.tv_jsonOb1);
        tvJsonObject2 = findViewById(R.id.tv_jsonOb2);
        tvJsonObject3 = findViewById(R.id.tv_jsonOb3);

        btnLoad = findViewById(R.id.btn_load);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "데이터 로드");
                userProfileList();
                profileParse();
            }
        });
    }

    private void userProfileList() {
        Log.d(TAG, "프로필 조회");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Hairview.hairviewApiUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Hairview api = retrofit.create(Hairview.class);
        Call<String> call = api.userProfile(token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.e(TAG, "onResponse, 프로필 조회 요청 후, 응답은 성공");
                Log.d(TAG, "response 전체 : "+ response);
                Log.d(TAG, "response code : "+ response.code());
                Log.d(TAG, "response isSuccessful : "+ response.isSuccessful());
                Log.d(TAG, "response message : "+ response.message());
                /*response 에서 code 값이 200 (isSuccessfull = true)이면
                 * 프로필 조회 성공*/
                /*response에서 code 값이 400 (isSuccessfull = false)이면
                 * 프로필 조회 실패*/
                /*response에서 code 값이 500 이면
                 * 프로필 조회 실패
                 * 서버 문제로 프로필 조회에 실패 하였다는 메시지를 보여준다*/
                Log.d(TAG, "response 헤더: "+ response.headers());
                Log.d(TAG, "response 바디: "+ response.body());
                textJsonArr = response.body();
                tvJsonArr.setText(textJsonArr);
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body();


                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e(TAG, "에러 = " + t.getMessage());
                Toast.makeText(getApplicationContext(),"서버에서 오류가 발생하여 프로필 조회에 실패하였습니다. 다시 시도해 주세요",Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void profileParse() {
        Log.d(TAG, "프로필 조회");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Hairview.hairviewApiUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Hairview api = retrofit.create(Hairview.class);
        Call<ArrayList<ProfileItem>> call = api.userProfileParse(token);
        call.enqueue(new Callback<ArrayList<ProfileItem>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ProfileItem>> call, @NonNull Response<ArrayList<ProfileItem>> response) {
                Log.e(TAG, "(parser)onResponse, 프로필 조회 요청 후, 응답은 성공");
                Log.d(TAG, "(parser)response 전체 : "+ response);
                Log.d(TAG, "(parser)response code : "+ response.code());
                Log.d(TAG, "(parser)response isSuccessful : "+ response.isSuccessful());
                Log.d(TAG, "(parser)response message : "+ response.message());
                /*response 에서 code 값이 200 (isSuccessfull = true)이면
                 * 프로필 조회 성공*/
                /*response에서 code 값이 400 (isSuccessfull = false)이면
                 * 프로필 조회 실패*/
                /*response에서 code 값이 500 이면
                 * 프로필 조회 실패
                 * 서버 문제로 프로필 조회에 실패 하였다는 메시지를 보여준다*/
                Log.d(TAG, "(parser)response 헤더: "+ response.headers());
                Log.d(TAG, "(parser)response 바디: "+ response.body());
                arrayJsonArr = response.body();
                JSONArray jsonArray = new JSONArray(arrayJsonArr);
                tvJsonObject1.setText(arrayJsonArr.toString());
                Log.d(TAG, "json length :" + arrayJsonArr.size());
                // below line we are running a loop to add data to our adapter class.
                for (int i = 0; i < arrayJsonArr.size(); i++) {
                        Log.d(TAG, "id :"+ arrayJsonArr.get(i).getId());
                        Log.d(TAG, "account :"+ arrayJsonArr.get(i).getAccount());
                        Log.d(TAG, "nickname :"+ arrayJsonArr.get(i).getNickname());
                        Log.d(TAG, "image :"+ arrayJsonArr.get(i).getImage());
                        Log.d(TAG, "thumb :"+ arrayJsonArr.get(i).getThumb());

                }
                // 배열의 모든 아이템을 출력합니다.
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Log.d(TAG, "id :"+ jsonObject.getString("id"));
                        Log.d(TAG, "account :"+ jsonObject.getString("account"));
                        Log.d(TAG, "nickname :"+ jsonObject.getString("nickname"));
                        Log.d(TAG, "image :"+ jsonObject.getString("image"));
                        Log.d(TAG, "thumb :"+ jsonObject.getString("thumb"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ProfileItem>> call, @NonNull Throwable t) {
                Log.e(TAG, "에러 = " + t.getMessage());
                Toast.makeText(getApplicationContext(),"서버에서 오류가 발생하여 프로필 조회에 실패하였습니다. 다시 시도해 주세요",Toast.LENGTH_SHORT).show();


            }
        });
    }

}