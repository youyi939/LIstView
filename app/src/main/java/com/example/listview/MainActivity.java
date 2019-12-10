package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview.Adapter.LeftAdapter;
import com.example.listview.Adapter.RightAdapter;
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
    private List<Good> initList = new ArrayList<>();
    private List<Good> rightList = new ArrayList<>();
    private Queue<Good2> initData = new LinkedList<>();
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        add = findViewById(R.id.add_);

        initData.offer(new Good2("汽车"));
        initData.offer(new Good2("飞机"));
        initData.offer(new Good2("轮船"));
        initData.offer(new Good2("大炮"));


        initList.add(new Good("汽车","小车1号"));
        initList.add(new Good("汽车","小车2号"));
        initList.add(new Good("汽车","小车3号"));
        initList.add(new Good("飞机","飞机1号"));
        initList.add(new Good("轮船","轮船1号"));
        initList.add(new Good("轮船","轮船2号"));
        initList.add(new Good("大炮","大炮1号"));

        initData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Good2 good2 = new Good2("xxx");
                if (initData.size()>0){
                    good2 = initData.poll();
                    initData.offer(good2);
                   leftList.add(good2);
                    leftAdapter.notifyDataSetChanged();
                }

                for (int i = 0; i < initList.size() ; i++) {
                    if (initList.get(i).getTitle() == good2.getTitle()){
                        rightList.add(initList.get(i));
                    }else {
                        Log.d("chen",initList.get(i).getTitle()+" "+initList.get(i).getName());
                    }
                }

            }
        });


    }

    private void initData(){

        leftAdapter = new LeftAdapter(MainActivity.this,R.layout.left_item, leftList,initList,rightAdapter,rightList);
        lv1.setAdapter(leftAdapter);
        rightAdapter = new RightAdapter(MainActivity.this,R.layout.right_item, rightList,rightList,leftAdapter,leftList);
        lv2.setAdapter(rightAdapter);
        Toast.makeText(MainActivity.this,"数据初始化成功",Toast.LENGTH_SHORT).show();

    }

}