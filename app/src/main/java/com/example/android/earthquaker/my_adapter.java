package com.example.android.earthquaker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class my_adapter extends BaseAdapter { //class name capital no underscore :)
    Context activity;
    ArrayList<earth> earths;
    TextView mag, first, second, date, time;
    int magnitudeColor;

    public my_adapter(@NonNull Context activity, ArrayList<earth> earths) {
        this.activity = activity;
        this.earths = earths;
    }

    @Override
    public int getCount() {
        return earths.size();

    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.list, null, false);
        }


        mag = (TextView) view.findViewById(R.id.mag);
        mag.setText(earths.get(position).getMagnitute() + "");

        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude

      magnitudeColor=  getMagnitudeColor(earths.get(position).getMagnitute());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        Log.d("colorssss", "getView: the color is  "+magnitudeColor);
        first = (TextView) view.findViewById(R.id.first);
        first.setText(earths.get(position).getLocation());
        second = view.findViewById(R.id.second);
        second.setText(earths.get(position).getSecond());
        date = (TextView) view.findViewById(R.id.date);
        date.setText(earths.get(position).getDate());
        time = (TextView) view.findViewById(R.id.time);
        time.setText(earths.get(position).getTime());
        return view;
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(activity, magnitudeColorResourceId);
    }

   /* private void getMagnitudeColor(double magnitute) {
        if (magnitute >= 0 || magnitute <= 2) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude1;

        } else if (magnitute > 2 || magnitute <= 3) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude2;
        } else if (magnitute > 3 || magnitute <= 4) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude3;
        } else if (magnitute > 4 || magnitute <= 5) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude4;
        } else if (magnitute > 5 || magnitute <= 6) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude5;
        } else if (magnitute > 6 || magnitute <= 7)

        {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude6;
            Log.d("xcolor", "getMagnitudeColor: ");
        } else if (magnitute > 7 || magnitute <= 8) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude7;
        } else if (magnitute > 8 || magnitute <= 9) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude8;
        } else if (magnitute > 9 || magnitute <= 10) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude9;
        } else if (magnitute > 10) {
            Log.d("colorssss", "getMagnitudeColor: ");

            magnitudeColor = R.color.magnitude10plus;
        }


    }*/
}

