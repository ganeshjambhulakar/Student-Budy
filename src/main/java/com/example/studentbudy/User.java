package com.example.studentbudy;

public class User {
    String fName,lName;
    int id,type;
    public User(int id,String f_name,String l_name,int type){
        this.fName=f_name;
        this.lName=l_name;
        this.id=id;
        this.type=type;
    }
    public  String getName(){
        return fName+lName;
    }
    public int getId(){
        return id;
    }
    public int getType(){
        return type;
    }
}
