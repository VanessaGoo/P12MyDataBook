package sg.edu.rp.c346.p12_mydatabook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String>{

        private Context context;
        private String[] value;
        ImageView iv;
        TextView tv;

        public CustomAdapter(Context context, int resource,String[] values) {
            super(context, resource, values);
            value = values;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row, parent, false);

            iv = rowView.findViewById(R.id.iv);
            tv = rowView.findViewById(R.id.tv);
            tv.setText(value[position]);
            String list = value[position];

            if (list.startsWith("Bio")){
                iv.setImageResource(R.drawable.ic_bio);
            } else if (list.startsWith("Anniversary")){
                iv.setImageResource(R.drawable.ic_anniversary);
            } else if (list.startsWith("Vaccination")){
                iv.setImageResource(R.drawable.ic_vaccination);
            } else if (list.startsWith("About Us")){
                iv.setImageResource(R.drawable.ic_aboutus);
            }
            return rowView;
        }
    }