package com.example.studentbudy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Feature extends AppCompatActivity implements RecyclerViewClickInterface{
    RecyclerView recyclerView;
    ArrayList<model> dataholder;
    ImageView imageViewPPSave, imageViewPPShow;
    Button buttonPPSave, buttonPPShow;
    EditText e1,e2,e3;
    database g;

    public void add_Feture(View view){
        e1 = findViewById(R.id.title);
        e2 = findViewById(R.id.desc);
        String title = e1.getText().toString();
        String desc = e2.getText().toString();

        Session session = new Session(this);
        int user_id = session.getSession();
//        Toast.makeText(this,"Select image",Toast.LENGTH_SHORT).show();
//
        if(title.equals("") ){
            Toast.makeText(this,"Enter Title",Toast.LENGTH_SHORT).show();
        }else if(desc.equals("") ){
            Toast.makeText(this,"Enter Description",Toast.LENGTH_SHORT).show();
        }
        else{
            byte[] img =  saveUser();
            g=new database(this);
            g.getWritableDatabase();
            Intent obj = getIntent();
            int org_id = obj.getIntExtra("org_id",0);
            boolean  qry =g.insert_feature(title,desc,org_id,img);
            if(qry == true){
                Toast.makeText(Feature.this,"Feature add successful",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Feature.this," Fail",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        imageViewPPSave = findViewById(R.id.imageViewMainActivityPPSave);


    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
    public void chooseFeaturePicture(View view) {
//        Toast.makeText(this,"succes",Toast.LENGTH_SHORT).show();

        if(checkAndRequestPermissions()) {
            takePictureFromGallery();
        }

    }
    private boolean checkAndRequestPermissions() {

        if(Build.VERSION.SDK_INT >= 23){

            int cameraPermission = ActivityCompat.checkSelfPermission(Feature.this, Manifest.permission.CAMERA);
            if(cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(Feature.this, new String[]{Manifest.permission.CAMERA}, 20);
                return true;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//       Toast.makeText(this,"data"+resultCode,Toast.LENGTH_LONG).show();
        switch (requestCode)
        {
            case 1:
//                Toast.makeText(this,"data Case 1",Toast.LENGTH_SHORT).show();

                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    imageViewPPSave.setImageURI(selectedImageUri);
                }
                break;
            case 2:
//                Toast.makeText(this,"data Case 2",Toast.LENGTH_SHORT).show();

//                if(resultCode == RESULT_OK){
//                    Bundle bundle = data.getExtras();
//                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
//                    imageViewPPSave.setImageBitmap(bitmapImage);
//                }
                break;
        }
    }

    //
    private void takePictureFromGallery(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }
    private byte[] saveUser(){

        byte[] bytesPP = convertImageViewToByteArray(imageViewPPSave);
        return bytesPP;
    }

    private byte[] convertImageViewToByteArray(ImageView imageView){
//        Toast.makeText(this,"succes",Toast.LENGTH_SHORT).show();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
