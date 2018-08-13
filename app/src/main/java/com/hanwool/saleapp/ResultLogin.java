package com.hanwool.saleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hanwool.saleapp.modal.Account;

public class ResultLogin extends AppCompatActivity {
    private TextView txtUserName;
//    private TextView txtEmail;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");
        addControl();
    }

    private void addControl() {
//        txtEmail = (TextView) findViewById(R.id.txtEmail);

        /**Set value*/
        txtUserName.setText(account.getUserName());
//        txtEmail.setText(account.getEmail());
    }
}
