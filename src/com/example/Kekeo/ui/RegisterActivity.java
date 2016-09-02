package com.example.Kekeo.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Kekeo.Listener.Listener;
import com.example.Kekeo.R;
import com.example.Kekeo.Service.UserService;
import com.loopj.android.http.RequestParams;

/**
 * Created by dy on 2016/9/2.
 */
public class RegisterActivity extends Activity{

    private EditText userPhone;
    private EditText password;
    private Button button;
    private RequestParams params;
    private UserService service;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params=new RequestParams();
                service=new UserService();
                params.put("UserPhone",userPhone.getText().toString()+"");
                pass=password.getText().toString();
                service.post(getApplicationContext(), "", params, new Listener() {
                    @Override
                    public void onSuccess(String s) {
                        if(s.equals("1")){
                            showcheckcode();
                        }else{
                            Toast.makeText(RegisterActivity.this, "手机号错误！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String s) {

                    }
                });
            }
        });
    }

    private void showcheckcode() {
        EditText text = new EditText(RegisterActivity.this);

    }

    public void init(){
        userPhone= (EditText) findViewById(R.id.userPhone);
        password= (EditText) findViewById(R.id.password);
        button= (Button) findViewById(R.id.register_btn);
    }
}
