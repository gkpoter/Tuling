package com.example.Kekeo.Service;

import android.content.Context;
import com.example.Kekeo.Listener.Listener;
import com.example.Kekeo.Model.ContextModel;
import com.example.Kekeo.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * Created by dy on 2016/8/31.
 */
public class TulingService {


    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                ContextModel models=new Gson().fromJson(new String(bytes),ContextModel.class);
                listener.onSuccess(models.getMsg());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                listener.onError("网络请求失败");
            }
        });
    }

}
