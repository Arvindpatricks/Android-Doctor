package com.example.login;

import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientRegstration extends Activity {
	EditText et1,et2,et3,et4,et5,et6;
	Button b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_regstration);
		et1=(EditText)findViewById(R.id.fname);
		
		et3=(EditText)findViewById(R.id.editText3);
		et4=(EditText)findViewById(R.id.editText4);
		et5=(EditText)findViewById(R.id.editText5);
		et6=(EditText)findViewById(R.id.editText6);
		b3=(Button)findViewById(R.id.get);
	b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int randomPIN = (int)(Math.random()*9000)+1000;
				 Log.v("PIN", ""+randomPIN);
               String sms = ""+randomPIN;
				ParseObject gameScore = new ParseObject("Patient");
				gameScore.put("NAME", et1.getText().toString());
			
				gameScore.put("EMAIL", et3.getText().toString());
				gameScore.put("PATIENT_ID", et4.getText().toString());
				gameScore.put("MOBILE", et4.getText().toString());
				gameScore.put("ADDRESS", et5.getText().toString());
				gameScore.put("AGE", et6.getText().toString());
				gameScore.put("OTP", randomPIN);
				gameScore.saveInBackground();
				try {
                	String messageText = sms;
                	SmsManager sm = SmsManager.getDefault();
                	sm.sendTextMessage(et4.getText().toString(), null, messageText, null, null);
                	
                	
                	Toast.makeText(getApplicationContext(), "OTP Sent!",Toast.LENGTH_LONG).show();
                	Intent inent = new Intent(PatientRegstration.this,PatientVerification.class);
  			      startActivity(inent); 
  			      inent.putExtra("patid",et4.getText().toString());
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
		getMenuInflater().inflate(R.menu.patient_regstration, menu);
		return true;
	}

}
