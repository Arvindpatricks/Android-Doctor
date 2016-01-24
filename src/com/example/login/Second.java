package com.example.login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

public class Second extends Activity {
EditText et1,et2,et3,et4,et5,et6;
Button b3;
private String data;
private String file = "mydata";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		et1=(EditText)findViewById(R.id.fname);
		et2=(EditText)findViewById(R.id.editText2);
		et3=(EditText)findViewById(R.id.editText3);
		et4=(EditText)findViewById(R.id.editText4);
		et5=(EditText)findViewById(R.id.editText5);
		et6=(EditText)findViewById(R.id.editText6);
		b3=(Button)findViewById(R.id.get);
		
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				data = et4.getText().toString();
				try {
			         FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);
			         fOut.write(data.getBytes());
			         
			         fOut.close();
			         Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
			         Toast.makeText(getBaseContext(),"file saved",
			         Toast.LENGTH_LONG).show();
			      } catch (Exception e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      }
				int randomPIN = (int)(Math.random()*9000)+1000;
				 Log.v("PIN", ""+randomPIN);
               String sms = ""+randomPIN;
				ParseObject gameScore = new ParseObject("User");
				gameScore.put("FNAME", et1.getText().toString());
				gameScore.put("LNAME", et2.getText().toString());
				gameScore.put("EMAIL", et3.getText().toString());
				gameScore.put("DOCID", et4.getText().toString());
				gameScore.put("MOBILE", et4.getText().toString());
				gameScore.put("ADDRESS", et5.getText().toString());
				gameScore.put("DOB", et6.getText().toString());
				gameScore.put("PIN", randomPIN);
				gameScore.saveInBackground();
				try {
                	String messageText = sms;
                	SmsManager sm = SmsManager.getDefault();
                	sm.sendTextMessage(et4.getText().toString(), null, messageText, null, null);
                	
                	
                	Toast.makeText(getApplicationContext(), "OTP Sent!",Toast.LENGTH_LONG).show();
                	Intent inent = new Intent(Second.this,DoctorVerification.class);
  			      startActivity(inent); 
  			      inent.putExtra("docid",et4.getText().toString());
                } catch (Exception e) {
                	Log.v("Errorrrrrr",e.toString());
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                
			
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
