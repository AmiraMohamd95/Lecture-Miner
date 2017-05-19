package com.example.android.lectureminer;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import  android.graphics.drawable.Drawable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CustomList extends ArrayAdapter<String>{

    HashMap<String,Integer> imageId;
    private final Activity context;
    private final ArrayList<String> titles;
    private final ArrayList<String> websites;


    public CustomList(Activity context, ArrayList<String> titles, ArrayList<String> websites) {
        super(context, R.layout.list_single, titles);
        this.context = context;
        this.titles = titles;
        this.websites = websites;

        imageId = new HashMap<String, Integer>();
        imageId.put("coursera.com",R.drawable.coursera);
        imageId.put("edx.org",R.drawable.edx);
        imageId.put("tutorialspoint.com",R.drawable.tuts);
        imageId.put("Udacity.com",R.drawable.udacity);
        imageId.put("udemy.com",R.drawable.udemy);
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtTitle.setText("   " + titles.get(position));

        /*
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                // doesn't work
               /* try {
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(urls[position]).getContent());
                    imageView.setImageBitmap(bitmap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

        // doesn't work also
                /*
                try {
                    InputStream is = (InputStream) new URL(urls[position]).getContent();
                    Drawable d = Drawable.createFromStream(is, "src name");
                    imageView.setImageDrawable(d);
                } catch (Exception e) {
                   e.printStackTrace();
                }
                */
        // failed
               /* Connection con2=Jsoup.connect(urls[position]);
                Document doc = con2.get();
                Element e1 = doc.head().select("link[href~=.*\\.(ico|png)]").first(); // example type 1 & 2
                String imageUrl1=e1.attr("href");
                Element e2 = doc.head().select("meta[itemprop=image]").first(); //example type 3
                String imageUrl2=e2.attr("itemprop");

            }
        });

        thread.start();
*/
        imageView.setImageResource(imageId.get(websites.get(position)));

        return rowView;
    }
}