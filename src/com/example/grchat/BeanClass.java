package com.example.grchat;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.graphics.Bitmap;


public class BeanClass{
	

	public static String uName;
	public static String apiToken;
	public static String uEmail;
	private static String headerType;
	private static String urlType;
	private static String paramType;
	private static Bitmap bmpImg;
	
    
	
  
    public static void setUserName(String user)
    {
    	uName=user;
    }
    public static String getUserName()
    {
       return uName;
    }
    
    public static void setApiToken(String api)
    {
    	apiToken=api;
    }
    public static String getApiToken()
    {
       return apiToken;
    }
    
    public static void setEmail(String email)
    {
    	uEmail=email;
    }
    public static String getEmail()
    {
       return uEmail;
    }
    
    public static void setCaptureImage(Bitmap bmp)
    {
    	bmpImg=bmp;
    }
    public static Bitmap getCaptureImage()
    {
       return bmpImg;
    }
    
   
  
}
