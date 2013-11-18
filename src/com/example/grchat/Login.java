package com.example.grchat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	// url to make request
	private static String msg_url = "http://api.androidhive.info/contacts/";
	
	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	
	private String userEmail,userPassword;
	
	private EditText uName, password;
	private Button signinSubmit, signIn, signUp;
	
    
	// contacts JSONArray
	//JSONArray contacts = null;
	private TextView timer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		signIn = (Button)findViewById(R.id.sign_in);
		signUp = (Button)findViewById(R.id.sign_up);
		uName = (EditText)findViewById(R.id.u_name);
		password = (EditText)findViewById(R.id.password);
		signinSubmit = (Button)findViewById(R.id.signin_submut);
		
		signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {				
            	
            	//Toast.makeText(getApplicationContext(), "clicked", 1000).show();
            	Intent i = new Intent(getApplicationContext(), Signup.class);
				startActivity(i);
				finish();
	        	
            }
           });
		signinSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {				
            	
            	 userEmail = uName.getText().toString();
   			     userPassword = password.getText().toString();
            	
   			     
            	
            	Toast.makeText(getApplicationContext(), userEmail, 1000).show();
            	Toast.makeText(getApplicationContext(), userPassword, 1000).show();
	        	
            }
           });
			
		
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		// DatabaseHandler db = new DatabaseHandler(this);
		
		// Hashmap for ListView
		//ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		//JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		//JSONObject json = jParser.getJSONFromUrl(msg_url);
		
		//System.out.println("---------------"+json+"--------------");
		
		
		
		 /**
         * CRUD Operations
         * */
		
        // Inserting Contacts
     /*   Log.d("Insert: ", "Inserting .."); 
        db.addContact(new Contact("Hello, this is a msg from DAN Electronics", "1"));        
        db.addContact(new Contact("Hi, this is a msg from DAN Electronics", "2"));
        db.addContact(new Contact("This is a msg from DAN Electronics", "3"));
        db.addContact(new Contact("Test, this is a msg from DAN Electronics", "4"));
        db.addContact(new Contact("hiii, this is a msg from DAN Electronics", "5"));
        db.addContact(new Contact("hi, this is a msg from DAN Electronics", "6"));
         
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts.."); 
        List<Contact> contacts = db.getAllContacts();  
		
		

		//try {
			// Getting Array of Contacts
		//	contacts = json.getJSONArray(TAG_CONTACTS);
			
			// looping through All Contacts
			//for(int i = 0; i < contacts.length(); i++){
			//	for(int i = 0; i < 5; i++){
					
				
				/*JSONObject c = contacts.getJSONObject(i);
				
				// Storing each json item in variable
				String id = c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String email = c.getString(TAG_PHONE);*/
			
				
				// creating new HashMap
				//HashMap<String, String> map = new HashMap<String, String>();
				
				
				// adding each child node to HashMap key => value
				/*map.put(TAG_ID, id);
				map.put(TAG_NAME, name);
				map.put(TAG_PHONE, email);
				

				// adding HashList to ArrayList
				contactList.add(map);*/
			//}
			
			
			
		/*	 for (Contact cn : contacts) {
		            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
		            
		          
		            
		         // Storing each json item in variable
					String id =Integer.toString(cn.getID());
					String name = cn.getName();
					String email = cn.getPhoneNumber();
				    
					
					// creating new HashMap
					HashMap<String, String> maps = new HashMap<String, String>();
					
					
					// adding each child node to HashMap key => value
					maps.put(TAG_ID, id);
					maps.put(TAG_NAME, name);
					maps.put(TAG_PHONE, email);
					

					// adding HashList to ArrayList
					contactList.add(maps); 
		            
		         // Writing Contacts to log
		        Log.d("Name: ", log);
		    }
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		
		
		/*ListAdapter adapter = new SimpleAdapter(this, contactList,R.layout.list_item,new String[] { TAG_NAME, TAG_PHONE }, new int[] {R.id.name, R.id.email });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String phn = ((TextView) view.findViewById(R.id.email)).getText().toString();
				
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMessage.class);
				in.putExtra(TAG_NAME, name);
				in.putExtra(TAG_PHONE, phn);	
				startActivity(in);

			}
		});*/
	}
	
	

}