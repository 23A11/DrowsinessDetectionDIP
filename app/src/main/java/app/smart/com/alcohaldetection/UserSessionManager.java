package app.smart.com.alcohaldetection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class UserSessionManager {


	// Shared Preferences reference
	SharedPreferences pref;
	private String key_2 = "key2";

	private String key_4 = "senstivity";
	// Editor reference for Shared preferences
	Editor editor;
	
	// Context
	Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREFER_NAME = "AndroidExamplePref";
	
	// All Shared Preferences Keys
	private static final String IS_USER_LOGIN = "IsUserLoggedIn";

	public static final String KEY_ID = "id";
	public static final String KEY_FNAME = "fname";
	public static final String KEY_LNAME = "lname";
	public static final String KEY_MOB = "mob";
	public static final String KEY_EMAIL = "email";
	public static final String KEY_PASS = "password";
	public static final String KEY_GENDER = "gender";
	public static final String KEY_PIC = "pic";

	// Constructor
	public UserSessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	//Create login session
	public void createUserLoginSession(String id ,String fname, String lname, String mob, String email, String pass, String pic, String gender
									   ){
		// Storing login value as TRUE
		editor.putBoolean(IS_USER_LOGIN, true);
		
		// Storing email in pref
		editor.putString(KEY_ID, id);
		editor.putString(KEY_FNAME, fname);
		editor.putString(KEY_LNAME,lname);
		editor.putString(KEY_MOB, mob);
		editor.putString(KEY_EMAIL, email);
		editor.putString(KEY_PASS, pass);
		editor.putString(KEY_PIC, pic);
		editor.putString(KEY_GENDER, gender);
		// commit changes
		editor.commit();
		Intent i=new Intent(_context,FaceTrackerActivity.class);
		i.putExtra(key_4,"4");
		i.putExtra(key_2, DateFormat.getDateTimeInstance().format(new Date()));
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        _context.startActivity(i);
	}	
	
	/**
	 * Check login method will check user login status
	 * If false it will redirect user to login page
	 * Else do anything
	 * */
	public boolean checkLogin(){
		// Check login status
		if(!this.isUserLoggedIn()){
			
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, LoginActivity.class);
			
			// Closing all the Activities from stack
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			// Staring Login Activity
			_context.startActivity(i);
			
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		
		//Use hashmap to store user credentials
		HashMap<String, String> user = new HashMap<String, String>();
		
		// user email id
		user.put(KEY_FNAME, pref.getString(KEY_FNAME, null));
		user.put(KEY_LNAME, pref.getString(KEY_LNAME, null));
		user.put(KEY_MOB, pref.getString(KEY_MOB, null));
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
		user.put(KEY_PASS, pref.getString(KEY_PASS, null));
		user.put(KEY_PIC, pref.getString(KEY_PIC, null));
		user.put(KEY_ID, pref.getString(KEY_ID, null));
		user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));


		// return user
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		
		// Clearing all user data from Shared Preferences
		editor.clear();
		editor.commit();
		
		// After logout redirect user to Login Activity
		Intent i = new Intent(_context, LoginActivity.class);
		
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		// Staring Login Activity
		_context.startActivity(i);
	}

	//check permission
	public static boolean checkPermissions(Context context, String... permissions) {
		for (String permission : permissions) {
			int res = context.checkCallingOrSelfPermission(permissions[0]);
			if (res != PackageManager.PERMISSION_GRANTED) {
				return false;
			}
		}

		return true;
	}


	// Check for login
	public boolean isUserLoggedIn(){
		return pref.getBoolean(IS_USER_LOGIN, false);
	}


}
