package com.example.grchat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity
{
	
		private Button loginButton,signUp;
		private TextView wrongEmail,wrongPassword;
		private EditText email,password;
		private CheckBox chkRem;
		private String userEmail ;
		private String userPassword;
		private String success;
		private int responseToken = 0;
    private static final int DLG = 0;
    private static final int TEXT_ID = 0;
    private static final String TAG_SUCCESS = "success";
    private static final String TOKEN = "api_token";
    private static final String NAME="name";
    private String storedEmail;
    private String storedPassword;
	  private ProgressDialog pDialog;
    private LoginDataBaseAdapter loginDataBaseAdapter;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		loginButton = (Button)findViewById(R.id.signin_submut);
		signUp = (Button)findViewById(R.id.sign_up);
		email = (EditText)findViewById(R.id.u_name);
		password = (EditText)findViewById(R.id.password);
		wrongEmail = (TextView)findViewById(R.id.wrong_uname);
		wrongPassword = (TextView)findViewById(R.id.wrong_password);
		chkRem = (CheckBox) findViewById(R.id.check_rem);
		chkRem.setChecked(true);
		
		chkRem.setOnClickListener(new View.OnClickListener() {
			 
			  @Override
			  public void onClick(View v) {
		               
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(getApplicationContext(),"Bro, android checked :)", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getApplicationContext(),"Un checked :)", Toast.LENGTH_LONG).show();
				}
		 
			  }
			});
		
	// get Instance  of Database Adapter
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
				
		
		loginButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
        	  
        	  
        		  userEmail = email.getText().toString();
        		  userPassword = password.getText().toString();
    			
    			  if(userEmail.equals("")){
    				
    				  wrongEmail.setText("Enter your email address.");
    				
    				}else if(userPassword.equals("")){
    				
    					wrongPassword.setText("Enter your password.");
    					wrongEmail.setText("");
    					
    					  }else{
    						  
    						 // new SigninData().execute();
    					  	
    					  	Intent i = new Intent(getApplicationContext(), MainActivity.class);
    							startActivity(i);
    							finish();
    					
    					  }
            }
           });
		
		signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {				
            	
            	Intent i = new Intent(getApplicationContext(), Signup.class);
            	startActivity(i);
            	finish();
	        	
            }
           });
		
		
		storedEmail = loginDataBaseAdapter.getSinlgeEntryEmail();
		storedPassword = loginDataBaseAdapter.getSinlgeEntryPassword();
		
		email.setText(storedEmail);
		password.setText(storedPassword);
		//Toast.makeText(getApplicationContext(), storedEmail, Toast.LENGTH_SHORT).show();
		//Toast.makeText(getApplicationContext(), storedPassword, Toast.LENGTH_SHORT).show();
		
	}
	
	
    /**
     * If a dialog has already been created,
     * this is called to reset the dialog
     * before showing it a 2nd time. Optional.
     */
	
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
 
        switch (id) {
            case DLG:
                // Clear the input box.
                EditText text = (EditText) dialog.findViewById(TEXT_ID);
                text.setText("");
                break;
        }
    }
 
    
    
    /**
	 * Background Async Task to Create new product
	 * */
	class SigninData extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
			
		
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("user[email]", userEmail));
			params.add(new BasicNameValuePair("user[password]", userPassword));
			

			// getting JSON Object
			// Note that create product url accepts POST method
			ApiHendlar obj = new ApiHendlar(); 
			JSONObject json = obj.signInRequest(params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				success = json.getString(TAG_SUCCESS);
				pDialog.dismiss();
				if (success.equals("true")) {
					responseToken=1;
					String apiToken = json.getString(TOKEN);
					String uName = json.getString(NAME);
					
					BeanClass.setUserName(uName);
					BeanClass.setApiToken(apiToken);
					BeanClass.setEmail(userEmail);
					
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(i);
					finish();
					
				} else {
					
					//System.out.println("------"+fail+"-------");
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if(responseToken==0){
				alertDilog();
			}else{
				
				setupDataBase();
			}
		}
		
		
	}
	
			
			void alertDilog(){
				
				// custom dialog
				final Context context = this;
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.dilog);
				dialog.setTitle("Log In");
				
	 
				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.text);
				text.setText("The username or password you entered is incorrect.");
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
			
			
			
	  private void setupDataBase(){
		    	
		  loginDataBaseAdapter.insertEntry(userEmail, userPassword);
		        
		 }
	  
	  @Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			loginDataBaseAdapter.close();
			
		}
}