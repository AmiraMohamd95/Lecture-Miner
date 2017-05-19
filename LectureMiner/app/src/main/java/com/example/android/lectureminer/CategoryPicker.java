package com.example.android.lectureminer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryPicker extends ListActivity {

    ArrayList<String> categoryArrList;
    ArrayAdapter<String> categoryAdpList;
    String selectedCategory = "";
    Button next;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
        categoryArrList = new ArrayList<>();
        categoryArrList.add("Articles");
        categoryArrList.add("Courses");
        categoryArrList.add("Papers");
        categoryArrList.add("Tutorials");
        categoryAdpList = new ArrayAdapter<String>(this, R.layout.row, categoryArrList);
        setListAdapter(categoryAdpList);
        next = (Button) findViewById(R.id.bNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent startResultsProducer = new Intent(getApplicationContext(), ResultsProducer.class);
                startActivity(startResultsProducer); */
                Thread thread;
                try {
                    if (selectedCategory.length() != 0) {
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    GoogleSearch.getInstance().findLinks(selectedCategory, 1);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                        // Thread.sleep();
                        thread.join();


                        /* while (!GoogleSearch.getInstance().finished()) {
                            Toast.makeText(getApplicationContext(),
                                    "Searcing isn't finshed yet, please wait",
                                    Toast.LENGTH_LONG).show();
                        }*/

                        if (GoogleSearch.getInstance().finished()){

                            System.out.println("hereeeeeeeeeeeeee");
                            Intent startResultsProducer = new Intent(getApplicationContext(), ResultsProducer.class);
                            startActivity(startResultsProducer);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //   if (selectedCategory.length() != 0) {
        selectedCategory = categoryArrList.get(position);
        // try {
        //   GoogleSearch.getInstance().findLinks(selectedCategory, 1);
        //}catch (Exception ex){
        //   ex.printStackTrace();
        //}
    }
}