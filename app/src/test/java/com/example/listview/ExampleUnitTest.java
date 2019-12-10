package com.example.listview;

import android.util.Log;

import com.example.listview.pojo.Good;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private List<Good> rightList = new ArrayList<>();
    private List<Good> removeList = new ArrayList<>();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        rightList.add(new Good("汽车","雪弗兰"));
        rightList.add(new Good("汽车","福特"));
        rightList.add(new Good("汽车","道奇"));
        rightList.add(new Good("飞机","波音747"));
        rightList.add(new Good("轮船","驱逐舰"));
        rightList.add(new Good("轮船","航空母舰"));
        rightList.add(new Good("大炮","山炮"));

        String title = "汽车";
//
//        for (int i = 0; i < rightList.size() ; i++) {
//            System.out.println(rightList.get(i).getName());
//            if (rightList.get(i).getTitle().equals("汽车") ){
//                System.out.println("删除："+rightList.get(i).getName());
//                rightList.remove(0);
//            }
//            System.out.println(rightList.size());
//
//        }

        for (int i = 0; i < rightList.size() ; i++) {

            if (rightList.get(i).getTitle() == "汽车"){
                System.out.println(rightList.get(i).getName());
                removeList.add(rightList.get(i));
            }

        }
        for (int i = 0; i <removeList.size() ; i++) {
            System.out.println("remove:"+removeList.get(i).getName());
        }
        rightList.removeAll(removeList);
        for (int i = 0; i <rightList.size() ; i++) {
            System.out.println(":"+rightList.get(i).getName());
        }




    }





}