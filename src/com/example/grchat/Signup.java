package com.example.grchat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity {
	
	private Button signinSubmit,signIn,signUp;
	private EditText uName, email, password, cPassword;
	private String userName, userEmail, userPassword, userCpassword;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		signIn 			= (Button)findViewById(R.id.sign_in);
		signUp 			= (Button)findViewById(R.id.sign_up);
		uName 			= (EditText)findViewById(R.id.u_name);
		email 			= (EditText)findViewById(R.id.email);
		password 		= (EditText)findViewById(R.id.password);
		cPassword 		= (EditText)findViewById(R.id.c_password);
	    signinSubmit	= (Button)findViewById(R.id.signup_submut);
		
		signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {				
            	
            	
            	Intent i = new Intent(getApplicationContext(), Login.class);
				startActivity(i);
				finish();
	        	
            }
           });
		
		signinSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {				
            	
            	userName 		= uName.getText().toString();
            	userEmail 		= email.getText().toString();
            	userPassword 	= password.getText().toString();
            	userCpassword 	= cPassword.getText().toString();
            	
            	Toast.makeText(getApplicationContext(), userName, 1000).show();
            	Toast.makeText(getApplicationContext(), userEmail, 1000).show();
            	Toast.makeText(getApplicationContext(), userPassword, 1000).show();
            	Toast.makeText(getApplicationContext(), userCpassword, 1000).show();
            	
            	Intent i = new Intent(getApplicationContext(), Login.class);
				startActivity(i);
				finish();
	        	
            }
           });
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		//Hashmap for ListView
		ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		
		// Inserting Contacts
	       
	        db.addContact(new Contact("Hello, this is a msg from DAN Electronics", "1"));        
	        db.addContact(new Contact("Hi, this is a msg from DAN Electronics", "2"));
	        db.addContact(new Contact("This is a msg from DAN Electronics", "3"));
	        db.addContact(new Contact("Test, this is a msg from DAN Electronics", "4"));
	        db.addContact(new Contact("hiii, this is a msg from DAN Electronics", "5"));
	        db.addContact(new Contact("hi, this is a msg from DAN Electronics", "6"));
	         
	        // Reading all contacts
	        //List<Contact> contacts = db.getAllContacts();  
			
		
	}

}
