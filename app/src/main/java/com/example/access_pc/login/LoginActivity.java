package com.example.access_pc.login;

import android.Manifest;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.access_pc.Retro;
import com.example.access_pc.dao.Dish;
import com.example.access_pc.dao.NetworkCall;
import com.example.access_pc.dao.NukeSSLCerts;
import com.example.access_pc.dao.RegisterCustomer;
import com.example.access_pc.e_food.R;
import com.example.access_pc.interfaces.MenuApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Log in Activity.
 * @author Yu Qiu
 */
public class LoginActivity extends AppCompatActivity implements NetworkCall.NetworkInterface,
        NetworkCall.NetworkErrorInterface {

    EditText emailTextView;
    EditText passwordTextView;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        NukeSSLCerts.nuke();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button loginCustomerButton = (Button) findViewById(R.id.email_sign_in_customer_button);
        Button loginRestaurantButton = (Button) findViewById(R.id.email_sign_in_restaurant_button);
        Button customerReg = (Button) findViewById(R.id.email_register_customer_button);
        Button restaurantReg = (Button) findViewById(R.id.email_register_restaurant_button);

        loginRestaurantButton.setVisibility(View.GONE);
        restaurantReg.setVisibility(View.GONE);

        emailTextView = (EditText)findViewById(R.id.email);
        passwordTextView = (EditText)findViewById(R.id.password);

        loginCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterCustomer user = new RegisterCustomer();
                user.setLogusername(emailTextView.getText().toString());
                user.setLogpassword(passwordTextView.getText().toString());
                loginUser(user);
            }
        });

        customerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CustomerRegisterActivity.class));
            }
        });

    }

    private void loginUser(RegisterCustomer user) {
        /*final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                "Logging in. Please wait...", true);*/
        String z = "https://e-food-system.herokuapp.com/api/userlogin";
        Map<String, String> param = new HashMap<String, String>();
        param.put("logusername", user.getLogusername());
        param.put("logpassword", user.getLogpassword());
        param.put("logpasswordConf", user.getLogpassword());
        param.put("first_name", user.getLogpassword());
        param.put("last_name", user.getLogpassword());
        param.put("address", user.getLogpassword());
        JSONObject json = new JSONObject(param);
        System.out.println(json.toString());
        new NetworkCall(this, this, LoginActivity.this, z, json).makeCall("POST");

        /*Call<ResponseBody> randomUsersCall = getUser().users(user)
        randomUsersCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Timber.i(response.body().toString());
                }
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.cancel();
                Timber.i(t.getMessage());
            }
        });*/
        /*System.out.println(user.getLogUsername());
        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "",
                "Logging in. Please wait...", true);
        Call<ResponseBody> randomUsersCall = Retro.getUser().users(user);
        randomUsersCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                   // dishes = response.body();
                    Timber.i(response.body().toString());
                    dialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Timber.i(t.getMessage());
                dialog.cancel();
            }
        });*/
    }

    public MenuApi getUser(){
        return retrofit.create(MenuApi.class);
    }

    @Override
    public void processNetworkPostExecute(JSONObject response) {
        System.out.println(response.toString());
    }

    @Override
    public void processNetworkErrorInterface(VolleyError error) {
        System.out.println(error.getMessage());
        NetworkResponse response = error.networkResponse;
        if (error instanceof ServerError && response != null) {
            try {
                String res = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                // Now you can use any deserializer to make sense of data
                //JSONObject obj = new JSONObject(res);
                System.out.println(res);
            } catch (UnsupportedEncodingException e1) {
                // Couldn't properly decode data to string
                e1.printStackTrace();
            }
        }
    }
}