package app.smart.com.alcohaldetection;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


public class UpdateProfile extends AppCompatActivity implements VolleyApi.ResponseListener{
    View actionBarView;
    EditText fname,lname,email,mobile,password;
    TextView tv_title,save,login;
    ImageView pic;
    RadioButton male,female;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String img="";
    String gender;
    UserSessionManager userSessionManager;
    String mob,strPass , userId, strEmail,strfname,strlname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void logoutDialog() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    private void intData() {


    }




    private void clear(){
        fname.setText("");
        lname.setText("");
        email.setText("");
        mobile.setText("");
        password.setText("");

    }

    public void  setImage(ImageView iv, String bm) {

    }

    boolean checkValidation(){
        return true;
    }
    private void findViewById() {

    }

    private void GetUserFromServer() {

    }


    public void chooseProfilePic() // replcae method name with chooseProfilePic
    {

    }


    private void galleryIntent()
    {

    }


    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {


    }


    private void onCaptureImageResult(Intent data) {

    }

    public String getStringImage(Bitmap bmp){
        return "";
    }

    public static String MD5_Hash(String s) {
       return "";
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {



    }

    @Override
    public void _onNext1(String obj) {

    }

    @Override
    public void _onVollyError(Exception e) {

    }
}
