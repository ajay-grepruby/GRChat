package com.example.grchat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Signup extends Activity
{
	private EditText uname,email,password,c_password;
	private Button submit,signIn;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_ERRORS = "errors";
	private String server_error;
	private TextView wrongUname,wrongEmail,wrongPassword,wrongCpassword;
	private int responseToken=0;
	private ProgressDialog pDialog;
	private ApiHendlar jsonParser = new ApiHendlar();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		uname=(EditText)findViewById(R.id.u_name);
		email=(EditText)findViewById(R.id.email);
		password=(EditText)findViewById(R.id.password);
		c_password=(EditText)findViewById(R.id.c_password);
		submit=(Button)findViewById(R.id.signup_submut);
		signIn=(Button)findViewById(R.id.sign_in);
		wrongUname=(TextView)findViewById(R.id.wrong_uname);
		wrongEmail=(TextView)findViewById(R.id.wrong_email);
		wrongPassword=(TextView)findViewById(R.id.wrong_password);
		wrongCpassword=(TextView)findViewById(R.id.wrong_cpassword);
		
		
		// button click event
		submit.setOnClickListener(new View.OnClickListener() {

					@SuppressLint("ResourceAsColor")
					@Override
					public void onClick(View view) {
						// signup in background thread
						 int token=0;
						if(uname.getText().toString().equals("") || uname.getText().toString().length()<6){
							wrongUname.setText("User name must be at least 6 characters.");
							token=1;
						}else{
							wrongUname.setText("");
						}
						if(email.getText().toString().equals("")){
							token=1;
							wrongEmail.setText("You can't leave this empty.");
						}else{
							wrongEmail.setText("");
						}
						if(password.getText().toString().equals("")){
							token=1;
							wrongPassword.setText("You can't leave this empty.");
						}else{
							wrongPassword.setText("");
						}
						if(c_password.getText().toString().equals("") || !c_password.getText().toString().equals(password.getText().toString())){
							token=1;
							wrongCpassword.setText("Confirm your password here.");
						}else{
							wrongCpassword.setText("");
						}
						
						if(token==0){
							 new SignupData().execute();    // comment for some time
						}
						
					}
		});
		
		
		signIn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {				
	            	
				Intent i = new Intent(getApplicationContext(), Login.class);
				startActivity(i);
				finish();
		        	
			}
		});
		
	}
	
	/**
	 * Background Async Task to Create new product
	 * */
	class SignupData extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			pDialog = new ProgressDialog(Signup.this);
			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			
		}
		
		/**
		 * Creating new user
		 * */
		protected String doInBackground(String... args) {
			
			String userName = uname.getText().toString();
			String userEmail = email.getText().toString();
			String userPassword = password.getText().toString();
			String confirmPassword = c_password.getText().toString();
			String mobileNum = "9087654321";

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("user[username]", userName));
			params.add(new BasicNameValuePair("user[email]", userEmail));
			params.add(new BasicNameValuePair("user[password]", userPassword));
			params.add(new BasicNameValuePair("user[password_confirmation]", confirmPassword));
			params.add(new BasicNameValuePair("user[phone]", mobileNum));

			// getting JSON Object
			ApiHendlar obj = new ApiHendlar();
			JSONObject json = obj.signUpRequest(params);
			
			//JSONObject json = JsonHendlar.setGlobalMakeHttpRequest(GlobalVariables.url_signup,"POST",params)
		    
			// check for success tag
			try {
				String success = json.getString(TAG_SUCCESS);
				pDialog.dismiss();
				
				if (success.equals("true")) {
					// successfully sign up
					responseToken=1;
					Intent i = new Intent(getApplicationContext(), Login.class);
					startActivity(i);
					finish();
				} else {
					
					server_error = json.getString(TAG_ERRORS);
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			if(responseToken==0){
				server_error = server_error.replace("[", "");
				server_error = server_error.replace("]", "");
				server_error = server_error.replace("{", "");
				server_error = server_error.replace("}", "");
				server_error = server_error.replace(":", " ");
				server_error = server_error.replace("\"", "");
				alertDilog(server_error);
				System.out.println(server_error);
			}
		}
		
	}
	
	void alertDilog(String msg){
		
		// custom dialog
		final Context context = this;
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dilog);
		dialog.setTitle("Log In");
		

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(msg);
		text.setTextColor(Color.parseColor("#000000"));
		ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageResource(R.drawable.ic_launcher);

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {				
            	
            	dialog.dismiss();
	        	
            }
           });

		dialog.show();


}

}
