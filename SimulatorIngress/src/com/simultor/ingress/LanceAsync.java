package com.simultor.ingress;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.simulator.element.AsyncTaskCompleteListener;
import com.simulator.element.IngressReso;


import android.app.Activity;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LanceAsync extends AsyncTask<Void, Integer, Void>{
	
	
	private ProgressBar progressBar;
	private String strLat = "";
	private String strLong = "";
	private String strUrl = "";
	private String resultJson = "";
	private Activity activity;
	private AsyncTaskCompleteListener callback;
	
	public LanceAsync(ProgressBar progressBar,  String strLat, String strLong, String url, Activity act){
		this.progressBar = progressBar;
		this.strLat = strLat;
		this.strLong = strLong;
		this.strUrl = url;
		this.activity = act;
		this.callback = (AsyncTaskCompleteListener)act;
	}

	@Override
	protected Void doInBackground(Void... params) {
		if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "doInBackground");}

		try {
			strUrl = strUrl + "?position_lat=" + strLat + "&position_lng=" + strLong; ;
			if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "strUrl = " + strUrl);}
			URL url = new URL(strUrl);
			HttpURLConnection urlConnection;
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    StringBuilder sb = new StringBuilder();
		    while ((line = reader.readLine()) != null) {
		    	//if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, line);}
		    	sb.append(line + "\n");
		    }
		    urlConnection.disconnect();
		    resultJson = sb.toString();
		    //if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, resultJson);}
		} catch (IOException e) {
			if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "erreur appSync = " + e.getMessage());}
		}
		   
		return null;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "onPreExecute");}
		progressBar.setVisibility(View.VISIBLE);
		
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "onPostExecute");}
		callback.onTaskComplete(resultJson);
		
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		if (BaseActivity.fgDebug){Log.d(BaseActivity.TAG, "onProgressUpdate");}
	}
	
}
