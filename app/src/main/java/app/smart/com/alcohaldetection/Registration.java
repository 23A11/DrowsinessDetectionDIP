package app.smart.com.alcohaldetection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity implements VolleyApi.ResponseListener{
    View actionBarView;
    EditText fname,lname,email,mobile,password;
    TextView tv_title,save,login;
    ImageView pic;
    RadioButton male,female;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String img="";
    String gender;
    String mob,strPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        findViewById();
    }
    private void clear(){
        fname.setText("");
        lname.setText("");
        email.setText("");
        mobile.setText("");
        password.setText("");

    }

    boolean checkValidation(){
        boolean ret = true;
        if(!Validation.hasText(fname))ret=false;
        if(!Validation.hasText(lname))ret=false;
        if(!Validation.hasText(password))ret=false;
        if(!Validation.isPhoneNumber(mobile,true))ret=false;
        if(!Validation.isEmailAddress(email,true))ret=false;

        return ret;
    }
    private void findViewById() {
        fname = (EditText) findViewById(R.id.et_firstName);
        lname = (EditText) findViewById(R.id.et_lastName);
        email = (EditText) findViewById(R.id.et_email);
        mobile = (EditText) findViewById(R.id.et_mobile);
        password = (EditText) findViewById(R.id.et_pass);
        save = (TextView)findViewById(R.id.tv_save);
        male = (RadioButton)findViewById(R.id.rb_male) ;
        female = (RadioButton)findViewById(R.id.rb_female);
        pic = (ImageView)findViewById(R.id.iv_user);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProfilePic();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkValidation()) {
                      GetUserFromServer();

                }

            }


        });


    }

    private void GetUserFromServer() {
        VolleyApi.getInstance().checkUserExisting(Registration.this,this,mobile.getText().toString() );
    }


    public void chooseProfilePic() // replcae method name with chooseProfilePic

    {
        final CharSequence[] items = { "Camera", "Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Profile Picture");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                boolean result= UserSessionManager.checkPermissions(getApplicationContext());

                if (items[item].equals("Camera")) {
                    userChoosenTask="Camera";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Gallery")) {
                    userChoosenTask="Gallery";
                    if(result)
                        galleryIntent();

                }
            }
        });
        builder.show();
    }


    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }


    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        img = getStringImage(bm);
        pic.setImageBitmap(bm);

    }


    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pic.setImageBitmap(thumbnail);
        img = getStringImage(thumbnail);
        Log.v("heloo","base64 >>>"+img+" <<<<<");
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);

        }

    }


    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("login");
            int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                Toast.makeText(this, "Mobile No. Already Exist", Toast.LENGTH_SHORT).show();

            }

        } catch (JSONException e) {
            e.printStackTrace();

            if (male.isChecked()){
                gender = "male";
            }else {
                gender = "female";
            }
            mob = mobile.getText().toString().trim();
            strPass = MD5_Hash(password.getText().toString().trim());
            VolleyApi.getInstance().Registration(this,this,fname.getText().toString().trim(),lname.getText().toString().trim(),
                    email.getText().toString().trim()
                    ,mob,gender,strPass,img);


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
                    Toast.makeText(this, "Registraion Success", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Registration.this,OtpActivity.class);
                    intent.putExtra("no",mob);
                    intent.putExtra("pass",strPass);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this, "Registration Fail", Toast.LENGTH_SHORT).show();
                }

            }



        } catch (JSONException e) {
            e.printStackTrace();


        }

    }

    @Override
    public void _onVollyError(Exception e) {

    }
}
