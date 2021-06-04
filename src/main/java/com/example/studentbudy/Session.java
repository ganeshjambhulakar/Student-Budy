package com.example.studentbudy;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARE_PREF_NAME="session";
    String SESSION_KEY="user";

    public Session(Context c) {
        sharedPreferences = c.getSharedPreferences(SHARE_PREF_NAME,c.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveSession(User user){
        int id = user.getId();
        int type = user.getType();
        editor.putInt(SESSION_KEY, id).commit();
        editor.putInt("TYPE", type).commit();
    }
    public void setOrgSessionId(int id ,int position){
        editor.putInt("org"+position, id).commit();
    }
    public int getOrgSessionId(int position){
        return sharedPreferences.getInt("org"+position, -1);
    }
    public void setOrgId(int id ,int position){
        editor.putInt("ORG_ID"+position, id).commit();
    }
    public int getOrgId(int position){
        return sharedPreferences.getInt("ORG_ID"+position, -1);
    }
    public int getSession(){
    return sharedPreferences.getInt(SESSION_KEY, -1);
}
    public int getType(){
        return sharedPreferences.getInt("TYPE", -1);
    }
    public void removeSession(){
        editor.clear().commit() ;
    }
}
