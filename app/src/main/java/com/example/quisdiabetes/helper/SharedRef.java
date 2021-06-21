package com.example.quisdiabetes.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedRef {
    public static final String SP_KEY = "UUID";
    public static final String UUID = "ID";
    public static final String UUIP = "IP";
    public static final String RATING = "RATING";
    public static final String PERIJINAN = "PERIJINAN";

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedRef(Context context) {
        //dengan key sampahku
        sp = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
        this.context = context;
        spEditor = sp.edit();
    }

    public void saveUUID(String value) {
        spEditor.putString(UUID, value);
        spEditor.commit();
    }

    public String getUUID() {
        return sp.getString(UUID, "");
    }

    public void savePerijinan(boolean value) {
        spEditor.putBoolean(PERIJINAN, value);
        spEditor.commit();
    }

    public boolean getPerijinan() {
        return sp.getBoolean(PERIJINAN, false);
    }

    public void saveUUIP(String value) {
        spEditor.putString(UUIP, value);
        spEditor.commit();
    }

    public String getUUIP() {
        return sp.getString(UUIP, "");
    }

    public void saveRating(boolean value) {
        spEditor.putBoolean(RATING, value);
        spEditor.commit();
    }

    public boolean getRating() {
        return sp.getBoolean(RATING, false);
    }
}
