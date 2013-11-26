package com.example.grchat;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//Declare
	private LinearLayout slidingPanel;
	private boolean isExpanded;
	private DisplayMetrics metrics;	
	private ListView listView;
	private RelativeLayout headerPanel;
	private RelativeLayout menuPanel;
	private int panelWidth;
	private ImageView menuViewButton,go,attech;
	private EditText chatInput;
	
	private static final String TAG_ID = "id";
	private static final String TAG_CHAT = "chat";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_ERRORS = "errors";
	private final Context context = this;
	
	FrameLayout.LayoutParams menuPanelParameters;
	FrameLayout.LayoutParams slidingPanelParameters;
	LinearLayout.LayoutParams headerPanelParameters ;
	LinearLayout.LayoutParams listViewParameters;
	
	final ArrayList<String> values = new ArrayList<String>();
	ArrayList<Bitmap> mBit = new ArrayList<Bitmap>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.list);
		String token = BeanClass.getApiToken();
		Toast.makeText(getApplicationContext(),"Token : "+ token, 1000).show();
		
		//Initialize
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		panelWidth = (int) ((metrics.widthPixels)*0.75);
	    
		headerPanel = (RelativeLayout) findViewById(R.id.header);
		headerPanelParameters = (LinearLayout.LayoutParams) headerPanel.getLayoutParams();
		headerPanelParameters.width = metrics.widthPixels;
		headerPanel.setLayoutParams(headerPanelParameters);
		
		menuPanel = (RelativeLayout) findViewById(R.id.menuPanel);
		menuPanelParameters = (FrameLayout.LayoutParams) menuPanel.getLayoutParams();
		menuPanelParameters.width = panelWidth;
		menuPanel.setLayoutParams(menuPanelParameters);
		
		slidingPanel = (LinearLayout) findViewById(R.id.slidingPanel);
		slidingPanelParameters = (FrameLayout.LayoutParams) slidingPanel.getLayoutParams();
		slidingPanelParameters.width = metrics.widthPixels;
		slidingPanel.setLayoutParams(slidingPanelParameters);
		
		chatInput = (EditText)findViewById(R.id.chat_input);
		go = (ImageView)findViewById(R.id.go);
		attech = (ImageView)findViewById(R.id.attech);
		
		go.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		    	
		    	String chat  =  chatInput.getText().toString();
		    	chatInput.setText("");
		    	values.add(chat);
		    	initChat();
		  }
		});
		attech.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				CharSequence[] items = {"Capture and send aphoto", "Send an image","Send a video","Send a file"};
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
		    builder.setItems(items, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int item) {

		            if(item == 0) {
		            	
		            	Intent i = new Intent(getApplicationContext(), CameraPhotoCapture.class);
		          		startActivity(i);
		          		finish();

		            } else if(item == 1) {

		            } else if(item == 2) {

		            }
		        }
		    });

		     AlertDialog dialog = builder.create();
		     dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		     WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

		  wmlp.gravity = Gravity.TOP | Gravity.LEFT;
		  wmlp.x = 100;   //x position
		  wmlp.y = 280;   //y position

		  dialog.show();
			}
		});
		
		//Slide the Panel	
		menuViewButton = (ImageView) findViewById(R.id.menuViewButton);
		menuViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		    	if(!isExpanded){
		    		isExpanded = true;   		    				        		
		        	
		    		//Expand
		    		new ExpandAnimation(slidingPanel, panelWidth,
		    	  Animation.RELATIVE_TO_SELF, 0.0f,
		    	  Animation.RELATIVE_TO_SELF, 0.75f, 0, 0.0f, 0, 0.0f);		    			         	    
		    	}else{
		    		isExpanded = false;
		    		
		    		//Collapse
		    		new CollapseAnimation(slidingPanel,panelWidth,
            TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
            TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);
		    	}         	   
		  }
		});
				
	}	
	
	private void initChat(){
		
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, R.id.label, values);
		listView.setAdapter(adapter); 
		
	}
	
	@Override
	public void onBackPressed() {
		
		Intent i = new Intent(getApplicationContext(), Login.class);
		startActivity(i);
		finish();
	}
}




