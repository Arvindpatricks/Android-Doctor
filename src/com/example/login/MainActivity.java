package com.example.login;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {
		Button b;
		private String data1,data2;
		   private String file1 = "registered";
		   private String file2 = "acctype";
		   
		   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b=(Button)findViewById(R.id.getotp);
		try{
	         FileInputStream fin = openFileInput(file1);
	         int c;
	         String temp="";
	         while( (c = fin.read()) != -1){
	            temp = temp + Character.toString((char)c);
	         }
	         
	         Toast.makeText(getBaseContext(),"file read",
	         Toast.LENGTH_SHORT).show();
	         data1=temp;
	      }catch(Exception e){
	    	  	Log.d("FIle Exception","Esdlkdslkdslkdslkdslk");
	      }
		
		try{
	         FileInputStream fin = openFileInput(file2);
	         int c;
	         String temp="";
	         while( (c = fin.read()) != -1){
	            temp = temp + Character.toString((char)c);
	         }
	         
	         Toast.makeText(getBaseContext(),"file read",
	         Toast.LENGTH_SHORT).show();
	         data2=temp;
	      }catch(Exception e){
	    	  Log.d("FIle Exception","Esdlkdslkdslkdslkdslk");
	      }
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
					Intent inent = new Intent(MainActivity.this,Introduction.class);
				      startActivity(inent);
					
				
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
