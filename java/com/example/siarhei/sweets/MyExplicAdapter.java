package com.example.siarhei.sweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyExplicAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<Sweet>> groups;
    private Context context;

    public MyExplicAdapter(Context context,ArrayList<ArrayList<Sweet>> groups){
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.group_view, null);
        }

      /*  if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }*/

        TextView textGroup = (TextView) view.findViewById(R.id.textGroup);
        textGroup.setText("Group " + Integer.toString(i));
        /*ImageView imageView = (ImageView)view.findViewById(R.id.imageGroup);
        imageView.setImageResource(R.drawable.grcar);*/
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        View itemView = view;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            itemView = inflater.inflate(R.layout.sweet, viewGroup, false);
        }

        // Find the car to work with.
        Sweet thisWeet = groups.get(i).get(i1);

        // Fill the view
        ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView2);
        imageView.setImageResource(thisWeet.getIconId());

        // Make:
        TextView makeText = (TextView) itemView.findViewById(R.id.textView2);
        makeText.setText(thisWeet.getName());

        // Year:
        TextView yearText = (TextView) itemView.findViewById(R.id.textView3);
        yearText.setText(String.valueOf(thisWeet.getPrice()));

        return itemView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
