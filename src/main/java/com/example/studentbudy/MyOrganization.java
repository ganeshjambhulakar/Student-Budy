package com.example.studentbudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrganization extends AppCompatActivity implements RecyclerViewClickInterface {
    RecyclerView recyclerView,recyclerViewFeature;
    ArrayList<model> dataholder;
    MyOrganizationAdapter myOrganizationAdapter;
    Session obj;
    int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_organization);
        obj = new Session(this);
        id = obj.getSession();
        display();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void display(){

        recyclerView=(RecyclerView)findViewById(R.id.OrgPrev);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//

        Cursor cursor=new database(this).OrgSingle(id);
        dataholder=new ArrayList<>();
////
      int i =0;
        while(cursor.moveToNext())
        {

            Session sessionObj = new Session(this);

            byte[] img = cursor.getBlob(5);
            model obj=new model(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),img,cursor.getString(6),cursor.getString(7));
            int id = cursor.getInt(0);
            sessionObj.setOrgSessionId(id,i);
            i++;
            dataholder.add(obj);
        }


        myOrganizationAdapter=new MyOrganizationAdapter(dataholder,this);
        recyclerView.setAdapter(myOrganizationAdapter);

    }
    @Override
    public void onLongItemClick(int position) {
//        Session sessionObj = new Session(this);
//        Integer  org_id = sessionObj.getOrgSessionId(position);
//        Integer cursor=new database(this).DeleteOrg(org_id);
//        Intent show = new Intent(this, fetchdata.class);
//        startActivity(show);
//        Toast.makeText(this,"Organization deleted ",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onItemClick(int position) {
//        Toast.makeText(this,"Short Clcik Listener"+position,Toast.LENGTH_SHORT).show();
        Intent show = new Intent(this, Organization.class);
        show.putExtra("pos",position);
        show.putExtra("Menu",1);
        startActivity(show);

    }


}