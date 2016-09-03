package com.example.Kekeo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Kekeo.Listener.Listener;
import com.example.Kekeo.MyActivity;
import com.example.Kekeo.R;
import com.example.Kekeo.Service.UserService;
import com.loopj.android.http.RequestParams;

/**
 * Created by dy on 2016/9/2.
 */
public class LoginActivity extends Activity{


    private EditText userPhone;
    private EditText password;
    private Button button,sub;
    private RequestParams params;
    private UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params=new RequestParams();
                service=new UserService();
                params.put("UserPhone",userPhone.getText().toString()+"");
                params.put("PassWord",password.getText().toString()+"");

                service.post(getApplicationContext(), "login", params, new Listener() {
                    @Override
                    public void onSuccess(String s) {
                        if(s.equals("1")){
                            /**
                             * 登录成功
                             */
                            SharedPreferences sharedPreferences=getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("UserPhone",userPhone.getText().toString()+"");
                            editor.commit();
                            startActivity(new Intent(LoginActivity.this,MyActivity.class));
                        }else{
                            /**
                             * 登录失败
                             */
                            Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String s) {
                        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void init(){
        userPhone= (EditText) findViewById(R.id.l_userPhone);
        password= (EditText) findViewById(R.id.l_password);
        button= (Button) findViewById(R.id.l_register_btn);
        sub= (Button) findViewById(R.id.l_login);
    }

}
