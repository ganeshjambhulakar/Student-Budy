package com.example.studentbudy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends Activity implements AdapterView.OnItemSelectedListener {
    EditText e1,e2,e3,e4,e5;
    database g;
    int user_type=0;
    public void Register(View view){
        e1 = findViewById(R.id.fname);
        e2 = findViewById(R.id.lname);
        e3 = findViewById(R.id.o_contact);
        e4 = findViewById(R.id.password);
        e5 = findViewById(R.id.conf_password);
        g=new database(this);
        g.getReadableDatabase();
        String fName = e1.getText().toString();
        String lName = e2.getText().toString();
        String username = e3.getText().toString();
        String password = e4.getText().toString();
        String conf_password = e5.getText().toString();
        if(fName.equals("")) {
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
        }else if(lName.equals("")){
            Toast.makeText(this,"Enter La Name",Toast.LENGTH_SHORT).show();
        }else if(username.equals("")){
            Toast.makeText(this,"Enter Username Name",Toast.LENGTH_SHORT).show();
        }else if(password.equals("")){
            Toast.makeText(this,"Enter Password ",Toast.LENGTH_SHORT).show();
        }else if(conf_password.equals("")){
            Toast.makeText(this,"Enter Confirm Password ",Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(conf_password)){
            Toast.makeText(this,"Password is not match",Toast.LENGTH_SHORT).show();
        }
        else if(user_type==0){
            Toast.makeText(this,"Enter Select User Type",Toast.LENGTH_SHORT).show();
        }
        else{
            boolean  i =g.insert_data(fName,lName,username,password,user_type);
            if(i==true){
                Toast.makeText(RegisterActivity.this,"Registrattion successfull",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RegisterActivity.this,"Registrattion Fail",Toast.LENGTH_SHORT).show();

            }
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        Button button=(Button)findViewById(R.id.button);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select User Type");
        categories.add("User");
        categories.add("Owner");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        user_type = position;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}