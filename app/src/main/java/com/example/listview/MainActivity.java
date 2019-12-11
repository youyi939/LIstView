package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.listview.Adapter.LeftAdapter;
import com.example.listview.Adapter.RightAdapter;
import com.example.listview.Databases.LeftDatabasesHelper;
import com.example.listview.Databases.RightDatabasesHelper;
import com.example.listview.pojo.Good;
import com.example.listview.pojo.Good2;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private ListView lv2;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<Good2> leftList = new ArrayList<>();
    private List<Good> init_right_List = new ArrayList<>();
    private List<Good> rightList = new ArrayList<>();
    private Queue<Good2> init_left_queue = new LinkedList<>();
    private RightDatabasesHelper rightDatabasesHelper;
    private LeftDatabasesHelper leftDatabasesHelper;

    private List<Good2> init_leftDatabases = new ArrayList<>();
    private List<Good> init_rightDatabses = new ArrayList<>();

    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        add = findViewById(R.id.add_);
        leftDatabasesHelper = new LeftDatabasesHelper(MainActivity.this,"Left.db",null,1);
        rightDatabasesHelper = new RightDatabasesHelper(MainActivity.this,"Right.db",null,1);

//        initDatabasesData();                 //初始化List数据
//        initDatabases();            //初始化Databases数据
        initAdapter();              //初始化两个Adapter
        initData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Good2 good2 = new Good2("xxx");
                if (init_left_queue.size()>0){
                    good2 = init_left_queue.poll();
                    init_left_queue.offer(good2);
                   leftList.add(good2);                         //添加Left数据
                    leftAdapter.notifyDataSetChanged();
                }

                for (int i = 0; i < init_right_List.size() ; i++) {                      //按照title添加Right数据
                    if (init_right_List.get(i).getTitle() == good2.getTitle()){
                        rightList.add(init_right_List.get(i));
                        Log.d("hahaha", "插入的Right: "+init_right_List.get(i).getTitle()+" "+ init_right_List.get(i).getName());
                    }else {
                        System.out.println("hello word");
                    }
                }
                rightAdapter.notifyDataSetChanged();

            }

        });


        SQLiteDatabase db = leftDatabasesHelper.getWritableDatabase();
        Cursor cursor = db.query("L",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("title"));
                Log.d("chaxun",name);
            }while (cursor.moveToNext());
        }
        cursor.close();



    }

    private void initAdapter(){
        leftAdapter = new LeftAdapter(MainActivity.this,R.layout.left_item, leftList, init_right_List,rightAdapter,rightList);
        lv1.setAdapter(leftAdapter);
        rightAdapter = new RightAdapter(MainActivity.this,R.layout.right_item, rightList,rightList,leftAdapter,leftList);
        lv2.setAdapter(rightAdapter);
        Toast.makeText(MainActivity.this,"数据初始化成功",Toast.LENGTH_SHORT).show();
    }                     //初始化Adapter

    private void initDatabasesData() {
        init_leftDatabases.add(new Good2("汽车"));                    //初始化Databases的List的数据
        init_leftDatabases.add(new Good2("飞机"));
        init_leftDatabases.add(new Good2("轮船"));
        init_leftDatabases.add(new Good2("大炮"));

        init_rightDatabses.add(new Good("汽车","小车1号"));
        init_rightDatabses.add(new Good("汽车","小车2号"));
        init_rightDatabses.add(new Good("汽车","小车3号"));
        init_rightDatabses.add(new Good("飞机","飞机1号"));
        init_rightDatabses.add(new Good("轮船","轮船1号"));
        init_rightDatabses.add(new Good("轮船","轮船2号"));
        init_rightDatabses.add(new Good("大炮","大炮1号"));
    }                       //初始化List数据

    private void initDatabases(){

        SQLiteDatabase db = leftDatabasesHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < init_leftDatabases.size(); i++) {
            values.put("title", init_leftDatabases.get(i).getTitle());
            db.insert("L",null,values);
            Log.d("chen","left: "+init_leftDatabases.get(i).getTitle());
        }               //L表插入数据


        SQLiteDatabase db2 = leftDatabasesHelper.getWritableDatabase();
        Cursor cursor2 = db2.query("L",null,null,null,null,null,null);
        if (cursor2.moveToFirst()){
            do {
                String title = cursor2.getString(cursor2.getColumnIndex("title"));
                init_left_queue.offer(new Good2(title));
            }while (cursor2.moveToNext());
        }
        cursor2.close();                                                        //L表查询数据


        SQLiteDatabase db3 = rightDatabasesHelper.getReadableDatabase();
        ContentValues values3 = new ContentValues();
        for (int i = 0; i < init_rightDatabses.size(); i++) {
            values3.put("title", init_rightDatabses.get(i).getTitle());
            values3.put("name", init_rightDatabses.get(i).getName());
            db3.insert("R",null,values3);
        }               //R表插入数据

        SQLiteDatabase db4 = rightDatabasesHelper.getWritableDatabase();
        Cursor cursor4 = db4.query("R",null,null,null,null,null,null);
        if (cursor4.moveToFirst()){
            do {
                String title = cursor4.getString(cursor4.getColumnIndex("title"));
                String name = cursor4.getString(cursor4.getColumnIndex("name"));

                init_right_List.add(new Good(title,name));
            }while (cursor4.moveToNext());
        }
        cursor4.close();                                                        //L表查询数据


    }                      //初始化数据库数据


    private void initData(){
        init_left_queue.offer(new Good2("汽车"));
        init_left_queue.offer(new Good2("飞机"));
        init_left_queue.offer(new Good2("轮船"));
        init_left_queue.offer(new Good2("大炮"));

        init_right_List.add(new Good("汽车","小车1号"));
        init_right_List.add(new Good("汽车","小车2号"));
        init_right_List.add(new Good("汽车","小车3号"));
        init_right_List.add(new Good("飞机","飞机1号"));
        init_right_List.add(new Good("轮船","轮船1号"));
        init_right_List.add(new Good("轮船","轮船2号"));
        init_right_List.add(new Good("大炮","大炮1号"));






    }


}