package app.smart.com.alcohaldetection;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class OtpActivity extends AppCompatActivity implements VolleyApi.ResponseListener{
    EditText et1,et2,et3,et4,et5,et6;
    Bundle bundle;
    String mob,pass;
    String strOtp,etOtp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        bundle = getIntent().getExtras();
        mob = bundle.getString("no");
        pass = bundle.getString("pass");
        //Toast.makeText(this, mob+"   "+pass, Toast.LENGTH_SHORT).show();
        findViewById();
        VolleyApi.getInstance().userLogin(this,this,""+mob,""+pass,"0");
    }

    private void findViewById() {
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);
        et5 = (EditText)findViewById(R.id.editText5);
        et6 = (EditText)findViewById(R.id.editText6);
        et1.addTextChangedListener(new GenericTextWatcher(et1));
        et2.addTextChangedListener(new GenericTextWatcher(et2));
        et3.addTextChangedListener(new GenericTextWatcher(et3));
        et4.addTextChangedListener(new GenericTextWatcher(et4));
        et5.addTextChangedListener(new GenericTextWatcher(et5));
        et6.addTextChangedListener(new GenericTextWatcher(et6));
        Button button = (Button)findViewById(R.id.btOtp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    etOtp = et1.getText().toString()+et2.getText().toString()+et3.getText().toString()+et4.getText().toString()+et5.getText().toString()+et6.getText().toString();
                    if (strOtp.equalsIgnoreCase(etOtp)){
                        activeUser();
                    }

                }
            }
        });
    }

    private void activeUser() {
        //Toast.makeText(this, ""+etOtp, Toast.LENGTH_SHORT).show();
        VolleyApi.getInstance().activeUser(this,this,mob);
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    boolean checkValidation(){
        boolean ret = true;
        if(!Validation.hasText(et1))ret=false;
        if(!Validation.hasText(et2))ret=false;
        if(!Validation.hasText(et3))ret=false;
        if(!Validation.hasText(et4))ret=false;
        if(!Validation.hasText(et5))ret=false;
        if(!Validation.hasText(et6))ret=false;


        return ret;
    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("login");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                strOtp = json_data.getString("otp");
                Toast.makeText(this, ""+strOtp, Toast.LENGTH_SHORT).show();

            }



        } catch (JSONException e) {
            e.printStackTrace();


        }
    }

    @Override
    public void _onNext1(String obj) {

        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("msg");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                if (json_data.getString("status").equalsIgnoreCase("200")){
                    Toast.makeText(this, " Success", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(OtpActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }else{
                    Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
                }

            }



        } catch (JSONException e) {
            e.printStackTrace();


        }
    }

    @Override
    public void _onVollyError(Exception e) {

    }

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {

                case R.id.editText1:
                    if(text.length()==1)
                        et2.requestFocus();
                    break;
                case R.id.editText2:
                    if(text.length()==1)
                        et3.requestFocus();
                    break;
                case R.id.editText3:
                    if(text.length()==1)
                        et4.requestFocus();
                    break;
                case R.id.editText4:
                    if(text.length()==1)
                        et5.requestFocus();
                    break;
                case R.id.editText5:
                    if(text.length()==1)
                        et6.requestFocus();
                    break;
                case R.id.editText6:
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

}
