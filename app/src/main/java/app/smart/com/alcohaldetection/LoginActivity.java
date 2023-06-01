package app.smart.com.alcohaldetection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,VolleyApi.ResponseListener{

    TextView bt_login,tvNewUser;
    EditText tvMob,tvPass;
    UserSessionManager userSessionManager;
    public  static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userSessionManager = new UserSessionManager(this);
        checkPermision();
        findViewById();
    }

    private void checkPermision() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case ASK_MULTIPLE_PERMISSION_REQUEST_CODE:

                break;
        }
    }

    private void findViewById() {
        tvMob =(EditText) findViewById(R.id.login_etEmail);
        tvPass =(EditText) findViewById(R.id.login_etPassword);
        tvNewUser = (TextView)findViewById(R.id.tv_new_account);
        tvNewUser.setOnClickListener(this);
        bt_login = (TextView)findViewById(R.id.login_tvLogin);
        bt_login.setOnClickListener(this);

    }



    public static String MD5_Hash(String s) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(),0,s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_tvLogin:
               if (checkValidation()){
                   VolleyApi.getInstance().userLogin(this,this,tvMob.getText().toString().trim(),""+MD5_Hash(tvPass.getText().toString()),"1");

               }

                break;

            case R.id.tv_new_account:
                startActivity(new Intent(LoginActivity.this,Registration.class));
                finish();
                break;
        }
    }
    boolean checkValidation(){
        boolean ret = true;
        if(!Validation.hasText(tvPass))ret=false;
        if(!Validation.isPhoneNumber(tvMob,true))ret=false;

        return ret;
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("login");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);
                userSessionManager.createUserLoginSession(
                        ""+json_data.getString("id"),
                        ""+json_data.getString("first_name"),
                        ""+json_data.getString("last_name"),
                        ""+json_data.getString("mobile"),
                        ""+json_data.getString("email"),
                        ""+json_data.getString("password"),
                        ""+json_data.getString("photo"),
                        ""+json_data.getString("gender")
                );


            }
            finish();


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void _onNext1(String obj) {

    }

    @Override
    public void _onVollyError(Exception e) {

    }
}
