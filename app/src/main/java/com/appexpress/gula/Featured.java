package com.appexpress.gula;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.appexpress.gula.util.RecyclerViewAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Featured extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    RecyclerView.Adapter recyclerViewadapter;
    JsonArrayRequest jsonArrayRequest ;
    RequestQueue requestQueue ;
    List<Deals> Deals;
    ProgressBar progressBar;
    String GET_JSON_DATA_HTTP_URL = "http://ksmr.000webhostapp.com/gula/getdeals.php";
    private AdView mAdView;

    public static final String IMAGE = "image";
    public static final String TOWN = "town";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String DESCRIPTION = "description";
    public static final String POSTID = "postId";
    public static final String PRICE = "price";
    public static final String TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseMessaging.getInstance().subscribeToTopic("NEWS");
        // initialize the AdMob app
        MobileAds.initialize(this, getString(R.string.admob_app_id));


        progressBar =  findViewById(R.id.progress);

        Deals = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        JSON_DATA_WEB_CALL();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                PostFragment fragment = new PostFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.commit();
            }
        });

        mAdView = (AdView) findViewById(R.id.adView);
       // mAdView.setAdSize(AdSize.BANNER);
        //mAdView.setAdUnitId(getString(R.string.banner_home_footer));

        AdRequest adRequest = new AdRequest.Builder()

                .build();


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    public void JSON_DATA_WEB_CALL(){

        showProgressBar();

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        hideProgressBar();
                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Featured.this, "Could not connect to remote database.", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            Deals Deal = new Deals();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                Deal.setImage(json.getString(IMAGE));

                Deal.setTown(json.getString(TOWN));

                Deal.setEmail(json.getString(EMAIL));

                Deal.setPhone(json.getString(PHONE));

                Deal.setDescription(json.getString(DESCRIPTION));

                Deal.setPostId(json.getString(POSTID));

                Deal.setPrice(json.getString(PRICE));

                Deal.setTitle(json.getString(TITLE));


            } catch (JSONException e) {

                e.printStackTrace();
            }
            Deals.add(Deal);
            hideProgressBar();
        }

        recyclerViewadapter = new RecyclerViewAdapter(Deals, this);

        recyclerView.setAdapter(recyclerViewadapter);

    }


    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
