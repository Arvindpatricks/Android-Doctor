package com.example.login;

import java.io.ByteArrayOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
public class DoctorVerification extends Activity {
	
	  
Button b1;
EditText otp,docid;
String parseId="";
private String data;
private String file = "registered";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_verification);
		
	
     	b1=(Button)findViewById(R.id.reg);
		otp=(EditText)findViewById(R.id.otp);
		docid=(EditText)findViewById(R.id.docid);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String temp=docid.getText().toString();
				ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
				Log.d("PRefe ID",temp);
				Toast.makeText(getApplicationContext(),temp, Toast.LENGTH_LONG).show();
		        query.whereEqualTo("DOCID",temp);
		        query.findInBackground(new FindCallback<ParseObject>() {
		            @Override
		            public void done(List<ParseObject> userList, ParseException e) {
		                
		                
		                 if (e == null) {
		                     if (userList.size()>0) {
		                    	
		                     for (int i = 0; i < userList.size(); i++) {
		                         ParseObject p = userList.get(i); 
		                         String otpa = otp.getText().toString();
		                         String fetchedPIN=""+p.getInt("PIN");
		                         parseId=p.getString("objectId");
		                         Log.d("DBPIN",fetchedPIN);
		                         if(fetchedPIN.equals(otpa)){
		                        	 Toast.makeText(getApplicationContext(),"PIN Matched !!!", Toast.LENGTH_LONG).show();
		                        	 try {
		                        		 data="yes";
		                                 FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);
		                                 fOut.write(data.getBytes());
		                                 fOut.close();
		                                 Toast.makeText(getBaseContext(),"OTL SVED",
		                                 Toast.LENGTH_SHORT).show();
		                              } catch (Exception e1) {
		                                 // TODO Auto-generated catch block
		                                 e1.printStackTrace();
		                              }
		                        	 ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
		                    		 
		                     		// Retrieve the object by id
		                     		query.getInBackground(parseId, new GetCallback<ParseObject>() {
		                     		  public void done(ParseObject gameScore, ParseException e) {
		                     		    if (e == null) {
		                     		    	 Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		                     							R.drawable.logo);
		                     					// Convert it to byte
		                     					ByteArrayOutputStream stream = new ByteArrayOutputStream();
		                     					// Compress image to lower quality scale 1 - 100
		                     					bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		                     					byte[] image = stream.toByteArray();
		                     					 ParseFile imgFile = new ParseFile ("braveImg.png", image);
		                     				        imgFile.saveInBackground();
		                     				       
		                     		      gameScore.put("ImageFile",imgFile);
		                     		      
		                     		      gameScore.saveInBackground();
		                     		    }
		                     		    else{Log.d("IMAGE ERROR",e.toString());}
		                     		  }
		                     		});
		                        	 
		                        	 Intent inent = new Intent(DoctorVerification.this,DoctorHome.class);
			      				      startActivity(inent);
		                        break; }
		                     } }
		                     
		                 }
		                 else {
		                        Log.d("score", "Error: " + e.getMessage());
		                       // Alert.alertOneBtn(getActivity(),"Something went wrong!");
		                    }   
		            }
		        });		
		 	}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctor_verification, menu);
		return true;
	}

}
