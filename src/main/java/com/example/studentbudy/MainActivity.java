package com.example.studentbudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 EditText e1,e2;
 database g;

//
    public void btnClick(View view){
        Intent show = new Intent(this, RegisterActivity.class);

        startActivity(show);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Session session = new Session(this);
        int userId = session.getSession();
        if(userId != -1){
            moveToOrganization();
        }
    }

    public void Login(View view){
//            Toast.makeText(this, "Registration successfull" , Toast.LENGTH_SHORT).show();

        e1 = findViewById(R.id.o_contact);
        e2 = findViewById(R.id.o_type);
        g=new database(this);
        g.getReadableDatabase();
        String username = e1.getText().toString();
        String password = e2.getText().toString();

        if(username.equals("") ){
            Toast.makeText(this,"Enter username",Toast.LENGTH_SHORT).show();
        }else if(password.equals("") ){
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
        }
        else {
            Cursor i = g.login(username, password);

            if (i.getCount() == 0) {
                Toast.makeText(MainActivity.this, "Invalid detail", Toast.LENGTH_SHORT).show();
            } else {
                while (i.moveToNext()){
                    User user = new User(i.getInt(0),i.getString(1),i.getString(2),i.getInt(5));
                    Session  session = new Session(this);
                    session.saveSession(user);
                }

                moveToOrganization();
            }
      }
}

  public void moveToOrganization(){
      Intent show = new Intent(this, fetchdata.class);
      show.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(show);
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database g = new database(this);
        SQLiteDatabase db = g.getReadableDatabase();

       }


}