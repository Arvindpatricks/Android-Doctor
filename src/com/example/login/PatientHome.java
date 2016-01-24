package com.example.login;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;



import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;  
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PatientHome extends Activity {
	Spinner spinner,spinner2;
	 private String[] arraySpinner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_home);
		  spinner = (Spinner)findViewById(R.id.spinner1);
		  spinner2 = (Spinner)findViewById(R.id.spinner2);
		  this.arraySpinner = new String[] {
		            "1", "2", "3", "4", "5"
		        };
		  final ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
	        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner2.setAdapter(spinnerAdapter2);

	        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
	        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner.setAdapter(spinnerAdapter);
	        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
	        query.whereEqualTo("USER_TYPE","DOCTOR");
	        query.findInBackground(new FindCallback<ParseObject>() {
	            @Override
	            
	            public void done(List<ParseObject> userList, ParseException e) {
	        
	            	if (e == null) {
	            		Toast.makeText(getApplicationContext(),"GOT....", Toast.LENGTH_LONG).show();    
	            	     	 
	                     if (userList.size()>0) {
	                    	 Toast.makeText(getApplicationContext(),"Fetching...", Toast.LENGTH_LONG).show();
	                     for (int i = 0; i < userList.size(); i++) {
	                         
	                    	 ParseObject p = userList.get(i); 
	                      
	                       spinnerAdapter.add(p.getString("FNAME"));
	                  	 } }
	                     
	                 }
	                 else {
	                        Log.d("PARSE ERROR", "Error: " + e.getMessage());
	                       // Alert.alertOneBtn(getActivity(),"Something went wrong!");
	                    }   
	            }
	        });		
  spinnerAdapter.notifyDataSetChanged();
  spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
    		Toast.makeText(getApplicationContext(),"Content Changed..."+spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
    		
	    }
	    

	    @Override
	    public void onNothingSelected(AdapterView<?> parentView) {
	        // your code here
	    }

	});
  spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
  		Toast.makeText(getApplicationContext(),"Content Changed..."+spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
  		
	    }
	    

	    @Override
	    public void onNothingSelected(AdapterView<?> parentView) {
	        // your code here
	    }

	});
	         
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_home, menu);
		return true;
	}
	public void addListenerOnSpinnerItemSelection(){
        
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
}

//get the selected dropdown list value

public void addListenerOnButton() {

spinner = (Spinner) findViewById(R.id.spinner1);

}
    
 
}
