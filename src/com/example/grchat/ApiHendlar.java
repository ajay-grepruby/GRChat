package com.example.grchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class ApiHendlar {

	private InputStream httpResponseContent= null;
	private JSONObject jObj = null;
	private String json = "";
	private HttpResponse httpResponse;
	
	// constants
	private final String server_url = "http://grepruby-leave-app.herokuapp.com";
	
	// api path vars
	private  String api_path;
	private  String signin_path; 
	private  String signup_path;
	
	
	// constructor
	public ApiHendlar() {
		
		api_path = server_url + "/api/v1";
		signin_path =  "/session";
		signup_path =  "/registration";
	}
	
	// class methods
	 
	 
	//private instance methods
	private  String callUrl(String req_url){
	  	  
		String fatchUrl = api_path + req_url;
		return fatchUrl;
	}
	
	private HttpGet customHttpGet(String url, List params){
		
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url += "?" + paramString;
		HttpGet httpGet = new HttpGet(url);
		return httpGet;
	}
	
	private HttpPost customHttpPost(String url, List params){
		
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpPost;
	}
	
	private JSONObject responceProcessing(){
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponseContent, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			httpResponseContent.close();
			json = sb.toString();
			System.out.println("Json output in string formate : "+json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		
		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		
		// return JSON String
		return jObj;
	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	private JSONObject makeHttpRequest(String url, String method,List<NameValuePair> params) {

	// Making HTTP request
		try{			
			DefaultHttpClient httpClient = new DefaultHttpClient();
				
				// check for request method
			if(method == "POST"){
				httpResponse = httpClient.execute(customHttpPost(url, params));
			}else{
				httpResponse = httpClient.execute(customHttpGet(url, params));
			}	
			HttpEntity httpEntity = httpResponse.getEntity();
			httpResponseContent= httpEntity.getContent();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (ClientProtocolException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return responceProcessing();
	}
	 
	//public instance methods
	public JSONObject signUpRequest(List params){
		
		JSONObject json = makeHttpRequest(callUrl(signup_path),"POST", params);
		return json;
	}
		  
	public  JSONObject signInRequest(List params){
		 		
		JSONObject json = makeHttpRequest(callUrl(signin_path),"POST", params);
		//json = "";
		return json;
	}
}