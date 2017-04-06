package com.example.ankitchaturvedi.girddemo.Utils;

import android.content.Context;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.entity.StringEntityHC4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ankit.Chaturvedi on 01-Apr-17.
 */

public class ServiceCall {

    private HttpPostHC4 post;
    private StringEntityHC4 stringEntity;
    public static int status_code = 0;
    private Context mContext;

    private String url = "";

    public ServiceCall(Context context, String url, StringEntityHC4 stringEntity) {
        this.url = url;
        post = new HttpPostHC4(url);
        this.stringEntity = stringEntity;
        this.mContext = context;
    }

    public StringBuffer request(String urlString) {


        StringBuffer strBuffer = new StringBuffer("");
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                strBuffer.append(line);
            }
        }
        catch (IOException e) {
            // Writing exception to log
            e.printStackTrace();
        }
        return strBuffer;
    }
}