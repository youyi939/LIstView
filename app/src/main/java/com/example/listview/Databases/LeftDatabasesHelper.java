package com.example.listview.Databases;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LeftDatabasesHelper extends SQLiteOpenHelper {

    public static final String CREAT_LEFT = "create table L ("
            + "id integer primary key autoincrement, "
            + "title text)";
    private Context mcontext;


    public LeftDatabasesHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREAT_LEFT);
        Toast.makeText(mcontext,"数据库创建成功 successed",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");                     //执行SQL语句
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}