package com.example.grchat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectImageGalleryActivity extends Activity {
    
	
	private static int RESULT_LOAD_IMAGE1 = 1;

	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_image_gallery);
        
        TextView buttonLoadImage1 = (TextView) findViewById(R.id.buttonLoadPicture1);
        buttonLoadImage1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, RESULT_LOAD_IMAGE1);
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
		if (requestCode == RESULT_LOAD_IMAGE1 && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			
			//String ext = picturePath.substring(picturePath.lastIndexOf("."));
			
			
			ImageView imageView = (ImageView) findViewById(R.id.imgView1);
			imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			
			Bitmap yourBitmap = BitmapFactory.decodeFile(picturePath);
			
			
			File image = new File("/Download/");
			
			try {
			       FileOutputStream out = new FileOutputStream(image);
			       yourBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); //100-best quality
			       out.close();
			} catch (Exception e) {
			       e.printStackTrace();
			}
			
			//yourBitmap.compress(Bitmap.CompressFormat.PNG, 100, out); //100-best quality
			
			MediaStore.Images.Media.insertImage(getContentResolver(), yourBitmap, null , null);
		
		}
		
    }
    @Override
  	public void onBackPressed() {
  		
  		Intent i = new Intent(getApplicationContext(), MainActivity.class);
  		startActivity(i);
  		finish();
  	}
}