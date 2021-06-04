package com.example.studentbudy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class OrganizationRegistration extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    database g;
    ImageView imageViewPPSave, imageViewPPShow;
    Button buttonPPSave, buttonPPShow;

    public void OrgRegister(View view){
        e1 = findViewById(R.id.o_name);
        e2 = findViewById(R.id.o_address);
        e3 = findViewById(R.id.o_contact);
        e4 = findViewById(R.id.o_type);
        e5 = findViewById(R.id.o_email);
        g=new database(this);
        g.getReadableDatabase();
        String name = e1.getText().toString();
        String address = e2.getText().toString();
        String contact = e3.getText().toString();
        String type = e4.getText().toString();
        String email = e5.getText().toString();
          Session session = new Session(this);
          int user_id = session.getSession();

        if(name.equals("") ){
            Toast.makeText(this,"Enter Organization name",Toast.LENGTH_SHORT).show();
        }else if(address.equals("") ){
            Toast.makeText(this,"Enter Address",Toast.LENGTH_SHORT).show();
        }else if(contact.equals("") ){
            Toast.makeText(this,"Enter Contact",Toast.LENGTH_SHORT).show();
        }else if(type.equals("") ){
            Toast.makeText(this,"Enter type",Toast.LENGTH_SHORT).show();
        }
        else if(email.equals("") ){
            Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show();
        }
        else{
            byte[] img =  saveUser();

            boolean  i =g.insert_org(name,contact,address,img,type,user_id,email);
            if(i==true){
                Toast.makeText(OrganizationRegistration.this,"Registration successful",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(OrganizationRegistration.this,"Registration Fail",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void chooseProfilePicture(View view) {
//        Toast.makeText(this,"succes",Toast.LENGTH_SHORT).show();

        if(checkAndRequestPermissions()) {
            takePictureFromGallery();
        }

    }
    private boolean checkAndRequestPermissions() {

        if(Build.VERSION.SDK_INT >= 23){

            int cameraPermission = ActivityCompat.checkSelfPermission(OrganizationRegistration.this, Manifest.permission.CAMERA);
            if(cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(OrganizationRegistration.this, new String[]{Manifest.permission.CAMERA}, 20);
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
                Toast.makeText(this,"data Case 1",Toast.LENGTH_SHORT).show();

                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    imageViewPPSave.setImageURI(selectedImageUri);
                }
                break;
            case 2:
                Toast.makeText(this,"data Case 2",Toast.LENGTH_SHORT).show();

                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    imageViewPPSave.setImageBitmap(bitmapImage);
                }
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_registration);
        imageViewPPSave = findViewById(R.id.imageViewMainActivityPPSave);
  }
}