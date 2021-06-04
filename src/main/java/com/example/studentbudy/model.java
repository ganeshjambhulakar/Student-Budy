package com.example.studentbudy;

import android.graphics.Bitmap;

import java.sql.Blob;

public class model
{
    String name,contact,address,type,email;
    int id;
    byte[] image;
    public model(int id, String name, String contact, String address, byte[] image,String type,String email) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.image = image;
        this.type = type;
        this.email = email;

    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public byte[] getImage() {
        return image;
    }


    public String getContact() {
        return contact;
    }


    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
    public String getType() {
        return type;
    }

}
