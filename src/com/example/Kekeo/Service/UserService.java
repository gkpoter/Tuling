package com.example.Kekeo.Service;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.Kekeo.Listener.Listener;
import com.example.Kekeo.Model.UserModel;
import com.example.Kekeo.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * Created by dy on 2016/9/2.
 */
public class UserService {

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                SharedPreferences sharedPreferences=context.getSharedPreferences("user",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("SecretKey",model.getSecretKey()+"");
                editor.commit();
                listener.onSuccess(model.getState()+"");
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                listener.onError("网络错误");
            }
        });
    }
}
