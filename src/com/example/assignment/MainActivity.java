package com.example.assignment;

import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter.LengthFilter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	Button submit;
	EditText etName, etAge, etBudget, etEmail;
	Spinner spJob;
	RadioGroup rdGender;
	User n;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        submit = (Button)findViewById(R.id.submit_button);
        etName = (EditText)findViewById(R.id.name_field);
        etAge = (EditText)findViewById(R.id.age_field);
        etBudget = (EditText)findViewById(R.id.budget_field);
        etEmail = (EditText)findViewById(R.id.email_field);
        rdGender = (RadioGroup)findViewById(R.id.gender_rg);
        spJob = (Spinner)findViewById(R.id.job_spinner);
        System.out.println("Running Ok after assigning viewbyid");
        
        //http://www.dcpagesapps.com/developer-resources/android/21-android-tutorial-spinners?start=1
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this, R.array.job_title, android.R.layout.simple_spinner_item);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJob.setAdapter(spAdapter);
        //End www.dcpagesapps.com
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SelectionActivity.class);
				
				if(!isEmpty(etName) && !isEmpty(etAge) && !isEmpty(etBudget) && !isEmpty(etEmail))
				{
			        System.out.println("Nothing is empty. Ready to create user");
					createUser();
			        System.out.println("User has been created. About to do intent");
			        intent.putExtra("user", n);
			        startActivity(intent);
					
				}
				else
				{	
					String emptyError = "All Fields are mandatory.";
					Toast.makeText(getApplicationContext(), emptyError, 5).show();
				}
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void createUser()
    {
    	System.out.println("Creating user");
    	int selected = rdGender.getCheckedRadioButtonId();
    	RadioButton selectedGender = (RadioButton) findViewById(selected);
        System.out.println("Radio button stuff done");
    	
    	String name = etName.getText().toString();
    	String email = etEmail.getText().toString();
    	System.out.println("About to parse job title spinner");
    	String jobTitle = spJob.getSelectedItem().toString();
    	String gender = selectedGender.getText().toString();
    	
    	int age = Integer.parseInt(etAge.getText().toString());
    	double budget = Double.parseDouble(etBudget.getText().toString());
    	
    	n = new User(name, name, jobTitle, email, age, budget);
    }
    
    private boolean isEmpty(EditText ed)
    {
    	String text = ed.getText().toString();
    	text.trim();
    	if(text.length()>0)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    		
    }
}
