package com.example.login;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientVerification extends Activity {
	Button b1;
	EditText otp,docid;
	String parseId="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_verification);
		
		
     	b1=(Button)findViewById(R.id.reg);
		otp=(EditText)findViewById(R.id.otp);
		docid=(EditText)findViewById(R.id.docid);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String temp=docid.getText().toString();
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Patient");
				Log.d("PRefe ID",temp);
				Toast.makeText(getApplicationContext(),temp, Toast.LENGTH_LONG).show();
		        query.whereEqualTo("PATIENT_ID",temp);
		        query.findInBackground(new FindCallback<ParseObject>() {
		            @Override
		            public void done(List<ParseObject> userList, ParseException e) {
		                
		                
		                 if (e == null) {
		                     if (userList.size()>0) {
		                    	
		                     for (int i = 0; i < userList.size(); i++) {
		                         ParseObject p = userList.get(i); 
		                         String otpa = otp.getText().toString();
		                         String fetchedPIN=""+p.getInt("OTP");
		                         parseId=p.getString("objectId");
		                         Log.d("DBPIN",fetchedPIN);
		                         if(fetchedPIN.equals(otpa)){
		                        	 Toast.makeText(getApplicationContext(),"PIN Matched !!!", Toast.LENGTH_LONG).show();
		                        	 
		                        	 
		                        	 Intent inent = new Intent(PatientVerification.this,PatientHome.class);
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
		getMenuInflater().inflate(R.menu.patient_verification, menu);
		return true;
	}

}
