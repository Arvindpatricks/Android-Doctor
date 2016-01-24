package com.example.login;

import com.parse.Parse;
import com.parse.ParseObject;

public class Application extends android.app.Application{
	@Override
	public void onCreate() {
	    super.onCreate();
	 
	    Parse.initialize(this,"hEh3aotZCuUE9ZAV0ZqgLcX9vciWplWrK6uRrgR0","DF3lEbXQ0BqGujBjH8xI7m5R65ktHxJc0ojQ2k8m");
	
	}
}
