package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ChooseType extends Activity {
Button doctor,patient;
private String data;
private String file = "acctype";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_type);
		doctor=(Button)findViewById(R.id.get);
		patient=(Button)findViewById(R.id.button2);
		doctor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
					data="doctor";
			         FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);
			         fOut.write(data.getBytes());
			         fOut.close();
			         Toast.makeText(getBaseContext(),"Acc TYPE saved",
			         Toast.LENGTH_SHORT).show();
			      } catch (Exception e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      }
				Intent inent = new Intent(ChooseType.this,Second.class);
			      startActivity(inent);
			      
				
			}
		});
		patient.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					data="patient";
			         FileOutputStream fOut = openFileOutput(file,MODE_WORLD_READABLE);
			         fOut.write(data.getBytes());
			         fOut.close();
			         Toast.makeText(getBaseContext(),"ACC TYPE SAVED",
			         Toast.LENGTH_SHORT).show();
			      } catch (Exception e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      }
				Intent inent = new Intent(ChooseType.this,PatientRegstration.class);
			      startActivity(inent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_type, menu);
		
		
		return true;
	}
	

}
