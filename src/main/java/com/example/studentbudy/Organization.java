package com.example.studentbudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class Organization extends AppCompatActivity {
    RecyclerView recyclerView,recyclerViewFeature;
    ArrayList<model> dataholder;
    ArrayList<FeatureModel> FeatureDataholder;
    database g;
    myOrgAdapter adapter;
    Session obj;
    int type =0;

    featureAdapter FeatureAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
//        Toast.makeText(Organization.this,"org",Toast.LENGTH_SHORT);
        display();
        obj = new Session(this);
        type = obj.getType();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Intent obj = getIntent();
        int pos =obj.getIntExtra("pos",0);
        int icon =obj.getIntExtra("Menu",0);
        if(type==2 && icon==1){
            getMenuInflater().inflate(R.menu.feature,menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent obj = getIntent();
        int pos =obj.getIntExtra("pos",0);
        int icon =obj.getIntExtra("Menu",0);
         int id = item.getItemId();
        if(type==2){
            if (id == R.id.feture) {
                Intent show = new Intent(this, Feature.class);
                show.putExtra("org_id",pos);
                startActivity(show);
                return true;
            }
            if (id == R.id.delete) {
                Integer cursor=new database(this).DeleteOrg(pos);
                Intent show = new Intent(this, MyOrganization.class);
                startActivity(show);
                Toast.makeText(this,"Organization deleted ",Toast.LENGTH_SHORT).show();

                return true;
            }
        }
        return true;
    }

    public void display(){

        recyclerViewFeature=(RecyclerView)findViewById(R.id.feature);
        recyclerViewFeature.setLayoutManager(new LinearLayoutManager(this));
        Intent show = getIntent();
        int pos =show.getIntExtra("pos",0);


        Cursor FeatureCursor=new database(this).OrgFeature(pos);
                FeatureDataholder=new ArrayList<>();

        while(FeatureCursor.moveToNext() )
        {
            byte[] img = FeatureCursor.getBlob(4);
            FeatureModel Featureobj=new FeatureModel(FeatureCursor.getInt(0),FeatureCursor.getString(1),FeatureCursor.getString(2),img);
            FeatureDataholder.add(Featureobj);
        }
       FeatureAdapter=new featureAdapter(FeatureDataholder);
        recyclerViewFeature.setAdapter(FeatureAdapter);

       }


}