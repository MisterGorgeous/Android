package com.example.siarhei.sweets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;


public class MyListAdapter extends ArrayAdapter<Sweet> {

    private List<Sweet> sweets;
    private ListView list;
    private Context context;

    public MyListAdapter(Context context, List<Sweet> sweets,ListView list) {
        super( context, R.layout.sweet, sweets);
        this.sweets = sweets;
        this.list = list;
        this.context = context;
    }

    public void populateListView() {
        ArrayAdapter<Sweet> adapter = this;
        list.setAdapter(adapter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            itemView = inflater.inflate(R.layout.sweet, parent, false);
        }

        // Find the car to work with.
        Sweet thisWeet = sweets.get(position);

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
}
