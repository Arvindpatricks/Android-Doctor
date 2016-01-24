package com.example.login;

import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import android.app.ListActivity;
public class DoctorHome  extends Activity {
TextView tv1,tv3,tv4;
Button b;
private String data;
private String file = "mydata";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_home);
		 try{
	         FileInputStream fin = openFileInput(file);
	         int c;
	         String temp="";
	         while( (c = fin.read()) != -1){
	            temp = temp + Character.toString((char)c);
	         }
	         data=temp;
	         Toast.makeText(getBaseContext(),"file read"+temp,
	         Toast.LENGTH_LONG).show();

	      }catch(Exception e){

	      }
		tv1= (TextView)findViewById(R.id.textView1);
		tv3 =(TextView)findViewById(R.id.textView3);
		tv4 =(TextView)findViewById(R.id.textView4);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy    HH:mm:ss");
		String currentDateandTime = sdf.format(new Date());
		tv1.setText(currentDateandTime);
	  b= (Button)findViewById(R.id.get);
	  b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointments");
	        query.whereEqualTo("DOC_ID",data);
	        query.findInBackground(new FindCallback<ParseObject>() {
	            @Override
	            public void done(List<ParseObject> userList, ParseException e) {
	                
	                 String text="";
	                 String timetext="";
	                 if (e == null) {
	                     if (userList.size()>0) {
	                    	 Toast.makeText(getApplicationContext(),"Fetching...", Toast.LENGTH_LONG).show();
	                     for (int i = 0; i < userList.size(); i++) {
	                         ParseObject p = userList.get(i); 
	                       String name=p.getString("PATIENT_NAME");
	                       String time=p.getString("TIME");
	                       timetext=timetext+"\n\n"+time;
	                       text=text+"\n\n"+name;
	                       //Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
	                         tv3.setText(name);
	                         tv4.setText(timetext);
	                     } }
	                     tv3.setText(text);
	                     Log.v("Values", text);
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
		getMenuInflater().inflate(R.menu.doctor_home, menu);
		return true;
	}

}

 class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
        List<String> objects) {
      super(context, textViewResourceId, objects);
      for (int i = 0; i < objects.size(); ++i) {
        mIdMap.put(objects.get(i), i);
      }
    }

    @Override
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

  }
