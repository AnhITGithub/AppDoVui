package com.huawei.android.appdovui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText taikhoan, matkhau;
    TextView dangnhap, dangky;
    String taikhoandk, matkhaudk;
    CheckBox check; //chua lam SharePreferences


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getView();
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=taikhoan.getText().toString().trim();
                String pass=matkhau.getText().toString();

                if (taikhoan.getText().length() != 0 && matkhau.getText().length() != 0) {
                    if (taikhoan.getText().toString().equals(taikhoandk) && matkhau.getText().toString().equals(matkhaudk)) {
                        Toast.makeText(Login.this, "Đăng nhập thành công !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, ListTopic.class);
                        startActivity(intent);
                    } else if (taikhoan.getText().toString().equals("android") && matkhau.getText().toString().equals("123")) {
                        Toast.makeText(Login.this, "Đăng nhập thành công !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Login.this, ListTopic.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Tài khoản không tồn tại !", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Hãy nhập thông tin !", Toast.LENGTH_LONG).show();
                }
            }
        });


        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(Login.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_dangky);
                EditText dktk=(EditText) dialog.findViewById(R.id.dktk);
                EditText dkmk=(EditText) dialog.findViewById(R.id.dkmk);
                Button btn_tao=(Button) dialog.findViewById(R.id.btn_tao);
                Button btn_huy=(Button) dialog.findViewById(R.id.btn_huy);
                btn_tao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taikhoandk=dktk.getText().toString().trim();
                        matkhaudk=dkmk.getText().toString().trim();
                        taikhoan.setText(taikhoandk);
                        matkhau.setText(matkhaudk);
                        dialog.cancel();
                        Toast.makeText(Login.this, "Tạo thành công !", Toast.LENGTH_LONG).show();
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    private void getView(){
        taikhoan=(EditText) findViewById(R.id.tk);
        matkhau=(EditText) findViewById(R.id.mk);
        dangnhap=(TextView) findViewById(R.id.login);
        dangky=(TextView) findViewById(R.id.register);
        check=(CheckBox) findViewById(R.id.checkbox);
    }


}