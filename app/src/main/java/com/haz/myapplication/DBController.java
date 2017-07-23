package com.haz.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by GOBLIN on 11/20/2016.
 */
public class DBController {
    Activity context;
    SQLiteDatabase db;
    Cursor kursor_surat;


    public DBController(Activity paramActivity) {
        // TODO Auto-generated constructor stub
        this.context = paramActivity;
    }

    public List<adaptervb> get_pel() {
        ArrayList<adaptervb> localArrayList = new ArrayList<adaptervb>();
        try {
            this.db = SQLiteDatabase.openDatabase(
                    "data/data/" + context.getPackageName() + "/"
                            + "/databases/vb", null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
            this.kursor_surat = this.db.rawQuery("SELECT  * FROM tb_bb", null);
            if (this.kursor_surat.moveToFirst()) {
                do {
                    localArrayList.add(new adaptervb(
                            kursor_surat.getString(0),
                            kursor_surat.getString(1),
                            this.kursor_surat.getString(3),
                            this.kursor_surat.getString(2)));
                } while (this.kursor_surat.moveToNext());
                this.kursor_surat.close();
                this.db.close();
            }
            return localArrayList;
        } catch (Exception paException) {

        }
        return localArrayList;

    }



}
