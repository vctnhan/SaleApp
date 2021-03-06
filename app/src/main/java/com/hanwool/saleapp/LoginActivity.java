package com.hanwool.saleapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hanwool.saleapp.Key.Constants;
import com.hanwool.saleapp.modal.Account;
import com.hanwool.saleapp.modal.AccountLogin;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    private Button btnRegister;
    private ProgressDialog pDialog;
    ArrayList<Account> arrayAccount;
    /**
     * URL : URL_LOGIN
     * param : KEY_USERNAME KEY_PASSWORD
     */
    public static final String URL_LOGIN = "http://dev.androidcoban.com/blog/login.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        arrayAccount = new ArrayList<>();
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value input
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                // Call method

                if (edtUserName.getText().toString().equals("admin") && edtPassWord.getText().toString().equals("admin")) {
                    Intent i2 = new Intent(getApplicationContext(), Quanlytaikhoan_AdminActivity.class);
                    startActivity(i2);
                }
                else {
                   loginAccount(username,password);

                }


            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControl() {
        edtUserName = (EditText) findViewById(R.id.editUsername);
        edtPassWord = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập");
        pDialog.setCanceledOnTouchOutside(false);

        Intent i = getIntent();
//        String user = (String) i.getSerializableExtra("register");
//        if (account != null) {
//            edtUserName.setText(user.toString());
//
//        }{
//
//
//        }
    }

    /**
     * Method login
     *
     * @param username
     * @param password result json
     */
    public void loginAccount(final String username, final String password) {

        if (checkEditText(edtUserName) && checkEditText(edtPassWord)) {


                pDialog.show();
                StringRequest requestLogin = new StringRequest(Request.Method.POST, Server.DuongdanLogin,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d(TAG, response);
                                String message = "";
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getInt("success") == 1) {

                                        AccountLogin account = new AccountLogin();
                                        account.setUserName(jsonObject.getString("user_name"));
                                        account.setEmail(jsonObject.getString("email"));
                                        account.setId(jsonObject.getInt("user_id"));
                                        message = "Đăng nhập thành công";

                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putInt("login", account);
//                                // đóng gói bundle vào intent
//                                    intent.putExtras(bundle);
//
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("ten", account.getUserName().toString());
//                                        Intent intent2 = new Intent(LoginActivity.this, ThongtinkhachhangActivity.class);
                                        intent.putExtra("user_id", String.valueOf(account.getId()));
                                        intent.putExtra("email", account.getEmail());
                                        startActivity(intent);
                                        finish();


                                    } else {
                                        message = jsonObject.getString("message");
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                pDialog.dismiss();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());
                                pDialog.dismiss();
                            }
                        }) {
                    /**
                     * set paramater
                     */
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put(KEY_USERNAME, username);
                        params.put(KEY_PASSWORD, password);
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(requestLogin);
            }

    }

    /**
     * Check input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng đăng nhập lại!");
        }
        return false;
    }
}

