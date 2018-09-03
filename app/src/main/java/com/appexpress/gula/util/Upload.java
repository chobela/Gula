package com.appexpress.gula.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Upload extends AsyncTask<String, Void, String> {

    private Context context;


    public Upload(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

        super.onPreExecute();

    }
//image, town, email, phone, description, postId, price, title
    @Override
    protected String doInBackground(String... arg0) {
        String image = arg0[0];
        String town = arg0[1];
        String email = arg0[2];
        String phone = arg0[3];
        String description = arg0[4];
        String postId = arg0[5];
        String price = arg0[6];
        String title = arg0[7];
        String userId = arg0[8];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?image=" + URLEncoder.encode(image, "UTF-8");
            data += "&town=" + URLEncoder.encode(town, "UTF-8");
 	        data += "&email=" + URLEncoder.encode(email, "UTF-8");
            data += "&phone=" + URLEncoder.encode(phone, "UTF-8");
            data += "&description=" + URLEncoder.encode(description, "UTF-8");
            data += "&postId=" + URLEncoder.encode(postId, "UTF-8");
            data += "&price=" + URLEncoder.encode(price, "UTF-8");
 	        data += "&title=" + URLEncoder.encode(title, "UTF-8");
 	        data += "&userId=" + URLEncoder.encode(userId, "UTF-8");

            link = "http://ksmr.000webhostapp.com/gula/upload.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {

        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                  //  Toast.makeText(context, "Password changed!", Toast.LENGTH_SHORT).show();
                   // Intent i = new Intent(context, Secret.class);context.startActivity(i);

                } else if (query_result.equals("FAILURE")) {
                   // Toast.makeText(context, "Password change Failed.", Toast.LENGTH_SHORT).show();
                } else {
                   // Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
               // Toast.makeText(context, "No Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        } else {
           // Toast.makeText((Context) context, "Could not connect to server.", Toast.LENGTH_SHORT).show();
        }
    }
}
