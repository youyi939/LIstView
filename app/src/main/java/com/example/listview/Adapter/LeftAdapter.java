package com.example.listview.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.listview.R;
import com.example.listview.pojo.Good;
import com.example.listview.pojo.Good2;

import java.util.Iterator;
import java.util.List;

public class LeftAdapter extends ArrayAdapter<Good2> {
    private int resourceId;
    private List<Good>rightList;
    private List<Good> goodList;
    private RightAdapter rightAdapter;


    public LeftAdapter(@NonNull Context context, int resource, List objects, List<Good>mrightList, RightAdapter adapter, List<Good> mgoodList) {
        super(context, resource,objects);
        resourceId = resource;
        rightList = mrightList;
        rightAdapter = adapter;
        goodList = mgoodList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        final TextView textView = view.findViewById(R.id.txt1);
        final Good2 good = getItem(position);
        textView.setText(good.getTitle());


        return view;
    }
}