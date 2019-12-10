package com.example.listview.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.listview.R;
import com.example.listview.pojo.Good;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;

public class RightAdapter extends ArrayAdapter<Good> {
    private int resourceId;
    private List<Good> goodList;
    public RightAdapter(@NonNull Context context, int resource, List objects,List<Good> mgoodList) {
        super(context, resource,objects);
        resourceId = resource;
        goodList = mgoodList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        final TextView textView = view.findViewById(R.id.txt2);

        final Button remove = view.findViewById(R.id.btn_4);
        final Good good = getItem(position);
        textView.setText(good.getName());

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = good.getTitle();
                List<Good> removeList = new ArrayList<>();

                for (int i = 0; i < goodList.size() ; i++) {

                    if (goodList.get(i).getTitle() == title){
                        removeList.add(goodList.get(i));
                    }

                }

                goodList.removeAll(removeList);

                notifyDataSetChanged();
            }
        });

        return view;
    }
}