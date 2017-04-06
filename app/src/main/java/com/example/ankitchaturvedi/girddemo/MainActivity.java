package com.example.ankitchaturvedi.girddemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.ankitchaturvedi.girddemo.Adapter.GirdViewAdapter;
import com.example.ankitchaturvedi.girddemo.ResponseParser.MainGridResponseParser;
import com.example.ankitchaturvedi.girddemo.Utils.Constant;
import com.example.ankitchaturvedi.girddemo.Utils.ServiceCall;
import com.google.gson.Gson;
import org.apache.http.entity.StringEntityHC4;


public class MainActivity extends AppCompatActivity {

    GridView mgv_main;
    GirdViewAdapter mGirdViewAdapter;
    MainGridResponseParser mMainGridResponseParser;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mgv_main = (GridView) findViewById(R.id.gv_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        GridViewAsync mGridViewAsync = new GridViewAsync();
        mGridViewAsync.execute(Constant.TXT_BLANK);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.top_rated) {
            TopRatedUrlAsync mTopRatedUrlAsync=new TopRatedUrlAsync();
            mTopRatedUrlAsync.execute(Constant.TXT_BLANK);
            return true;
        }
        if(id==R.id.popular) {
            GridViewAsync mGridViewAsync = new GridViewAsync();
            mGridViewAsync.execute(Constant.TXT_BLANK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class GridViewAsync extends AsyncTask<String, Void, String> {

        private String response = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                progressBar.setVisibility(View.VISIBLE);
                mgv_main.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            response = CallLoginService();
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            mgv_main.setVisibility(View.VISIBLE);
            try {
                Gson gson = new Gson();

                mMainGridResponseParser = gson.fromJson(response, MainGridResponseParser.class);


                if (mMainGridResponseParser != null) {

                    mGirdViewAdapter = new GirdViewAdapter(MainActivity.this, mMainGridResponseParser);
                    mgv_main.setAdapter(mGirdViewAdapter);

                }
            }  catch (Exception e) {

                e.printStackTrace();

            }
        }

        private String CallLoginService() {

            String urls = Constant.popular_url+ Constant.api_key;


            Log.e("", "URL IS NOW " + urls);
            System.out.println(urls);

            try {

                StringEntityHC4 stringEntity = new StringEntityHC4("");
                ServiceCall serviceCall = new ServiceCall(MainActivity.this, urls, stringEntity);

                response = String.valueOf(serviceCall.request(urls));
                System.out.println("################### Response:" + response);


            }  catch (Exception e) {
                e.printStackTrace();
            }
            return response;


        }
    }


    private class TopRatedUrlAsync extends AsyncTask<String, Void, String> {

        private String response = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                progressBar.setVisibility(View.VISIBLE);
                mgv_main.setVisibility(View.GONE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            response = CallLoginService();
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            mgv_main.setVisibility(View.VISIBLE);

            try {
                Gson gson = new Gson();

                mMainGridResponseParser = gson.fromJson(response, MainGridResponseParser.class);
                if (mMainGridResponseParser != null) {

                    mGirdViewAdapter = new GirdViewAdapter(MainActivity.this, mMainGridResponseParser);
                    mgv_main.setAdapter(mGirdViewAdapter);

                }


            } catch (Exception e) {

                e.printStackTrace();

            }
        }

        private String CallLoginService() {

            String urls = Constant.top_rated_url+ Constant.api_key;


            Log.e("", "URL IS NOW " + urls);
            System.out.println(urls);

            try {

                StringEntityHC4 stringEntity = new StringEntityHC4("");
                ServiceCall serviceCall = new ServiceCall(MainActivity.this, urls, stringEntity);

                response = String.valueOf(serviceCall.request(urls));
                System.out.println("################### Response:" + response);


            }  catch (Exception e) {
                e.printStackTrace();
            }
            return response;

        }

    }

}
