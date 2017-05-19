package com.example.android.lectureminer;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.microsoft.cognitive.textanalytics.sample.TextAnalyzer;

import java.util.ArrayList;

public class KeywordsPicker extends ListActivity {
    ArrayList<String> wordsArrList;
    //ArrayList<String> pickedWordsArrList;
    public static String pickedWord;

    ListView list;
    Button next;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywords_picker);

        wordsArrList = TextAnalyzer.WORDS;
        //pickedWordsArrList = new ArrayList<>();
        // dummy items to test activity
        /*wordsArrList.add("Algorithms");
        wordsArrList.add("Shortest Path Algorithms");
        wordsArrList.add("Priority Queues");
        wordsArrList.add("Parallelism ");
        wordsArrList.add("Graphs");
        wordsArrList.add("Cryptography");*/

        ArrayAdapter<String> wordsAdpList = new ArrayAdapter<String>(this, R.layout.row, wordsArrList);
        setListAdapter(wordsAdpList);

        list = (ListView) findViewById(android.R.id.list);
        next = (Button) findViewById(R.id.bNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startCategoryPicker = new Intent(getApplicationContext(), CategoryPicker.class);

                ArrayList<String> xyz = new ArrayList<String>();
                xyz.add(pickedWord);
               GoogleSearch.getInstance().setKeywords(xyz);

                startActivity(startCategoryPicker);
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO: CHECK IF ITEM IS ALREADY PRESSED AND CHANGE LAYOUT ACCORDINGLY

        String selected = wordsArrList.get(position);
        //pickedWordsArrList.add(selected);
        pickedWord = selected;
        //v.Layout
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
