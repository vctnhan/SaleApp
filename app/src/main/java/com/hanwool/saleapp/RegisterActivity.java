package com.hanwool.saleapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.hanwool.saleapp.LoginActivity;
import com.hanwool.saleapp.R;
import com.hanwool.saleapp.modal.Account;
import com.hanwool.saleapp.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText edtUserName;
    private EditText edtPassWord, edtRepassword;
    private EditText edtEmail;
    private Button btnRegister;
    private Button btnLogin;
    private ProgressDialog pDialog;
    Account account;
    public static final String REGISTER_URL = "http://dev.androidcoban.com/blog/register.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get data input
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                String repassword= edtRepassword.getText().toString().trim();
//                password = repassword;
                String email = edtEmail.getText().toString().trim();

                //Call method register
                if (password.equals(repassword) == false){
                    Toast.makeText(RegisterActivity.this,"Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
                else {
                    registerUser(username, password, email, repassword);
                }





            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                startActivity(intent);

                finish();
            }
        });
    }

    private void addControls() {

        edtUserName = (EditText) findViewById(R.id.editUsername);
        edtPassWord = (EditText) findViewById(R.id.editPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtRepassword = findViewById(R.id.editRe_Password);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng ký...");
        pDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * Method register
     *
     * @param username
     * @param password
     * @param email    result json
     */
    private void registerUser(final String username, final String password, final String email, final String repassword) {


        if ( password.equals(repassword) && checkEditText(edtUserName) && checkEditText(edtPassWord) && checkEditText(edtEmail) && isValidEmail(email)  ) {
            pDialog.show();
            StringRequest registerRequest = new StringRequest(Request.Method.POST,Server.DuongdanRegister,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if ( jsonObject.getInt("success") == 1 ) {

                                    account.setUserName(jsonObject.getString("user_name"));
                                    account.setEmail(jsonObject.getString("email"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                    //Start LoginActivity
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    intent.putExtra("register", account.getUserName().toString());
                                    startActivity(intent);
                                } else {
                                    message = jsonObject.getString("message");

                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException error) {
                                VolleyLog.d(TAG, "Error: " + error.getMessage());

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
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    params.put(KEY_EMAIL, email);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(registerRequest);
        }
    }

    /**
     * Check Input
     */
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    /**
     * Check Email
     */
    private boolean isValidEmail(String target) {
        if (target.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
            return true;
        else {
            edtEmail.setError("Email sai định dạng!");
        }
        return false;
    }
}