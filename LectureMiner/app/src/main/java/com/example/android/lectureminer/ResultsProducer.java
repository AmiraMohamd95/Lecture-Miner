package com.example.android.lectureminer;

        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.AdapterView;
        import android.widget.ListView;

        import java.util.ArrayList;

public class ResultsProducer extends Activity {
    /*
        private ArrayList<String> urls;
        private ArrayList<String> titles;

    */
    // name, url
    ArrayList<String> titles;
    ArrayList<String> urls;
    ArrayList<String> sites;


    /*

    String[] titles = {"Deep Learning Foundations","Artificial Intelligence", "Machine Learning", "Algorithms, Part I", "Big Data", "Cryptography I" , "Algorithms Specialization" , "Data Structures and Algorithms Specialization" , "Ruby on Rails Web Development Specialization" , "Web Design for Everybody (Basics of Web Development and Coding) Specialization"};
    String[] urls  = {"https://www.udacity.com/course/deep-learning-nanodegree-foundation--nd101",
            "https://www.udacity.com/ai", "https://www.coursera.org/specializations/machine-learning",
            "https://www.coursera.org/learn/algorithms-part1",
            "https://www.coursera.org/specializations/big-data",
            "https://www.coursera.org/learn/crypto"
            , "https://www.coursera.org/specializations/algorithms"
            , "https://www.coursera.org/specializations/data-structures-algorithms"
            , "https://www.coursera.org/specializations/ruby-on-rails"
            , "https://www.coursera.org/specializations/web-design"
    };


    String[] websites = {"udacity","udacity","coursera","coursera","coursera","coursera", "coursera" , "coursera" ,"coursera", "coursera"};
*/
    ListView list;


/*	void setInfo (ArrayList<String> urls, ArrayList<String> titles){
		this.urls = urls;
		this.titles = titles;
	}
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Remove title bar
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_results_producer);


        urls = GoogleSearch.getInstance().getURLs();
        titles = GoogleSearch.getInstance().getTitles();
        sites = GoogleSearch.getInstance().getSites();

        CustomList adapter = new CustomList(ResultsProducer.this, titles, sites);
        if (adapter == null) {
            System.out.println("adapter *********************** ");

        }
        list = (ListView)findViewById(R.id.list);
        if (list == null) {
            System.out.println("list  *********************** ");
        }
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ResultsProducer.this, "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(urls.get(position)));
                startActivity(intent);
            }
        });
    }
}