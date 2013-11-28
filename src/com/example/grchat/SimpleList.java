package com.example.grchat;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleList extends Activity {
	
	  private ImageView testImage;
	  private ListView listView;
	  ArrayList<ImageView> mBit = new ArrayList<ImageView>();
	  ArrayList<String> values = new ArrayList<String>();
	  
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    setContentView(R.layout.simple_list);
		    
		    Toast.makeText(getApplicationContext(), "hiiiii", 1000).show();
		    
		   // testImage = (ImageView)findViewById(R.id.test_img);
		   // testImage.setImageBitmap(BeanClass.getCaptureImage());
		    listView = (ListView) findViewById(R.id.list);
		    
		   // values.add("hiiiiii");
		    //mBit.add(R.drawable.ic_launcher);
		    ImageView droid = new ImageView(this);
		    droid.setImageResource(R.drawable.logo);
		    mBit.add(droid);
		    
		    final ArrayAdapter<ImageView> adapter = new ArrayAdapter<ImageView>(this, R.layout.rowlayout, R.id.chat_img, mBit);
		  	// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_row, R.id.label, values);
			 	listView.setAdapter(adapter);
		    
		    
		   
		  }

		 
		} 