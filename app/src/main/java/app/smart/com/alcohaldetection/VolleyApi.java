package app.smart.com.alcohaldetection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class VolleyApi {

    private ProgressDialog mProgressDialog;
    private static VolleyApi volleyApi = null;
    private ResponseListener mlistener_response;
    public static final String BASE_URL = "https://shopzone247.com/btech/smart_city_alcohal_detection/index.php/";
    public static final String BASE_URL_GOOGLE_API = "https://shopzone247.com/btech/smart_city_alcohal_detection";

    public static final String BASE_Image_URL = "https://shopzone247.com/btech/smart_city_alcohal_detection/application/photo/";


    public static VolleyApi getInstance() {

        if (volleyApi != null)
            return volleyApi;
        else
            return new VolleyApi();
    }


    public interface ResponseListener {

        void _onResponseError(Throwable e);

        void _onNext(String obj);

        void _onNext1(String obj);

        void _onVollyError(Exception e);

    }


    //----------------------------------------------- Volly Api-------------------------------------------------------


    //----------------------------------------------- Login Api-------------------------------------------------------


    public void userLogin(final Activity activity, final ResponseListener _mlistener_response, String mobile, String password ,String active) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Login/get/mobile/"+mobile+"/password/"+password+"/active/"+active,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    /*params.put("username", user);
                    params.put("password", pass);*/
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }



    //----------------------------------------------- Active User Api-------------------------------------------------------


    public void activeUser(final Activity activity, final ResponseListener _mlistener_response, final String mobile) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Login/activeuser",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("mobile", mobile);
                    params.put("active", "1");
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }



    //----------------------------------------------- Check user existing Api-------------------------------------------------------


    public void checkUserExisting(final Activity activity, final ResponseListener _mlistener_response, String mobile) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Login/get/mobile/"+mobile+"/active/1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    /*params.put("username", user);
                    params.put("password", pass);*/
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            ////Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }




    //----------------------------------------------- Update profile Api-------------------------------------------------------


    public void updateProfile(final Activity activity, final ResponseListener _mlistener_response,
                              final String first_name,
                              final String last_name ,
                              final String email,
                              final String mobile,
                              final String gender,
                              final String password,
                              final String id,
                              final String photo ) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Login/post",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("first_name", first_name);
                    params.put("last_name", last_name);
                    params.put("password", password);
                    params.put("email", email);
                    params.put("mobile", mobile);
                    params.put("gender", gender);
                    params.put("photo", photo);
                    params.put("id", id);
                    params.put("active", "1");
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }


    //----------------------------------------------- Registration Api-------------------------------------------------------


    public void Registration(final Activity activity, final ResponseListener _mlistener_response,
                             final String first_name,
                             final String last_name ,
                             final String email,
                             final String mobile,
                             final String gender,
                             final String password,
                             final String photo) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Login/post",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("first_name", first_name);
                    params.put("last_name", last_name);
                    params.put("password", password);
                    params.put("email", email);
                    params.put("mobile", mobile);
                    params.put("gender", gender);
                    params.put("photo", photo);
                    params.put("active", "0");
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }








    //----------------------------------------------- Get State Api-------------------------------------------------------


    public void getState(final Activity activity, final ResponseListener _mlistener_response) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Location/get/isApproved/1",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }


    //----------------------------------------------- Get City Api-------------------------------------------------------


    public void getCity(final Activity activity, final ResponseListener _mlistener_response ,final String stateID) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Location/getCity/state_id/"+stateID,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }


    //----------------------------------------------- Get Cat Api-------------------------------------------------------


    public void getCat(final Activity activity, final ResponseListener _mlistener_response ) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Location/getCat",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }


    //----------------------------------------------- Get Places Api-------------------------------------------------------


    public void getPlaces(final Activity activity, final ResponseListener _mlistener_response ,final String longi ,final String lati ,final String time ) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL_GOOGLE_API+"googleApi.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("longitude", longi);
                    params.put("latitude", lati);
                    params.put("time", time);
                    return params;
                }


            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    //----------------------------------------------- Get Places with cat Api-------------------------------------------------------


    public void getPlacesWithCat(final Activity activity, final ResponseListener _mlistener_response ,final String longi ,final String lati ,final String time ,final String cat) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL_GOOGLE_API+"googleApiWithCat.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("longitude", longi);
                    params.put("latitude", lati);
                    params.put("time", time);
                    params.put("cat", cat);
                    return params;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }



    //----------------------------------------------- Get Portfolio Api-------------------------------------------------------


    public void getPortfolio(final Activity activity, final ResponseListener _mlistener_response ,String id ) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL+"Location/getPortfolio/location_id/"+id,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }


    //----------------------------------------------- Get Search Api-------------------------------------------------------


    public void getSearchPlace(final Activity activity, final ResponseListener _mlistener_response ) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL+"Location/getPlace",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }


    //save location
    public void Save_Location(final Activity activity, final ResponseListener _mlistener_response,
                              final String location_name,
                              final String description,
                              final String category,
                              final String location,
                              final String img) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait...");
        mProgressDialog.show();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL + "Location/post",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("response", response);
                            mProgressDialog.dismiss();
                            mlistener_response._onNext1(response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                            mProgressDialog.dismiss();
                            mlistener_response._onResponseError(error);
                            //username.setError(getString(R.string.error_incorrect_username));

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("location_name",location_name);
                    params.put("description", description);
                    params.put("category", category);
                    params.put("location", location);
                    params.put("img", img);
                    params.put("active", "0");
                    return params;
                }


            };

            VolleySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();

        }
    }

}


