package com.example.studentbudy;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class fetchdata extends AppCompatActivity implements RecyclerViewClickInterface
{

    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    myadapter adapter;
    Session SessionObj;
    int type =0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);
        display();
        SessionObj = new Session(this);
        type = SessionObj.getType();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Check if user logged in or not
        Session session = new Session(this);
        int UserId = session.getSession();
        if(UserId == -1){
            moveToLogin();
        }
//        Toast.makeText(this,UserId ,Toast.LENGTH_LONG);
    }

        public void moveToLogin(){        //Function to move login page
            Intent show = new Intent(this, MainActivity.class);
            startActivity(show);
        }
    public void display(){

      recyclerView=(RecyclerView)findViewById(R.id.recview);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));

      Cursor cursor=new database(this).readalldata();
      dataholder=new ArrayList<>();
    int i=0;
        while(cursor.moveToNext())
      {
          byte[] img = cursor.getBlob(5);
          model obj=new model(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),img,cursor.getString(6),cursor.getString(7));
//          int id = cursor.getInt(0);
//          SessionObj.setOrgId(id,i);
//          i++;
          dataholder.add(obj);
      }
//
       adapter=new myadapter(dataholder,this);
       recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(this,"Short Clcik Listener"+position,Toast.LENGTH_SHORT).show();
        Intent show = new Intent(this, Organization.class);
        int id = position;
        show.putExtra("pos",id);
        show.putExtra("Menu",0);

//        Toast.makeText(this,"Short Clcik Listener"+id,Toast.LENGTH_SHORT).show();

        startActivity(show);
    }

    @Override
    public void onLongItemClick(int position) {
    }
//  For Menu

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        if(type == 2){
            getMenuInflater().inflate(R.menu.main_menu,menu);
        } if(type==1){
        getMenuInflater().inflate(R.menu.logout,menu);

    }
    MenuItem item  = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) item.getActionView();
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
         }

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
        }
    });
    return  true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(type == 2){
            if (id == R.id.add_org) {
                Intent show = new Intent(this, OrganizationRegistration.class);
                startActivity(show);
                return true;
            }
            if (id == R.id.my_org) {
                Intent show = new Intent(this, MyOrganization.class);
                startActivity(show);
                return true;
            }
        }
        if (id == R.id.logout) {
            Session session = new Session(this);
            session.removeSession();
            moveToLogin();
            return true;
        }
        return true;
    }
//    end
}