package com.example.access_pc.e_food;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.access_pc.Retro;
import com.example.access_pc.dao.Dish;
import com.example.access_pc.fragments.DishFragment;
import com.example.access_pc.fragments.dummy.DummyContent;
import com.example.access_pc.fragments.dummy.DummyDish;
import com.example.access_pc.interfaces.MenuApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RelativeLayout ll_buttonmenu;
    private ImageView imageView2;
    Retrofit retrofit;
    public static ArrayList<Dish> dishes;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    //private BottomNavigationBehavior.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        ll_buttonmenu = (RelativeLayout) findViewById(R.id.ll_buttonmenu);
        ll_buttonmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://e-food-system.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        populateUsers();
    }

    private void populateUsers() {
        Call<ArrayList<Dish>> randomUsersCall = Retro.getRandomUserService().getMenu();
        randomUsersCall.enqueue(new Callback<ArrayList<Dish>>() {
            @Override
            public void onResponse(Call<ArrayList<Dish>> call, @NonNull Response<ArrayList<Dish>> response) {
                final String TAG = "MainActivity";
                if(response.isSuccessful()) {
                    dishes = response.body();
                    Timber.i(response.body().toString());
                }else{
                    Log.e(TAG,response.errorBody().toString());
                    dishes = (ArrayList<Dish>) DummyDish.ITEMS;
                    /*try {
                        Converter<ResponseBody, QuoteOfTheDayErrorResponse> errorConverter =
                                retrofit.responseBodyConverter(QuoteOfTheDayErrorResponse.class, new Annotation[0]);
                        QuoteOfTheDayErrorResponse error = errorConverter.convert(response.errorBody());
                        showRetry(error.getError().getMessage());

                    } catch (IOException e) {
                        Log.e(TAG, "IOException parsing error:", e);
                    }*/
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Dish>> call, Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }

    /*public MenuApi getRandomUserService(){
        return retrofit.create(MenuApi.class);
    }*/


    private void showEditDialog() {
        startActivity(new Intent(this, DishAcitivity.class));
        /*System.out.println("CLICK CLICKED...");
        FragmentManager fm = getSupportFragmentManager();
        DishFragment editNameDialogFragment = DishFragment.newInstance(1);
        //editNameDialogFragment.;*/
    }

}
