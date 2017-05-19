package com.example.android.lectureminer;
/**
 * Created by amira on 08/02/17.
 */
        import android.content.Intent;

        import java.io.IOException;
        import java.util.ArrayList;
        import org.jsoup.HttpStatusException;

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;


public class GoogleSearch {


    public  boolean finished (){
        return doneSearching;
    }

    boolean doneSearching;
    private static GoogleSearch ourInstance = new GoogleSearch();
    public static GoogleSearch getInstance() {
        return ourInstance;
    }

    void setKeywords (ArrayList<String> keywords){
        this.keywords = keywords;
    }


    private GoogleSearch() {
        doneSearching = false;
    }


    ArrayList<String> keywords;
    private ArrayList<String>titles = new  ArrayList<String>();
    private ArrayList<String>urls = new  ArrayList<String>();
    private ArrayList<String>sites = new ArrayList<String>();



    public ArrayList<String> getURLs(){
        return urls;
    }

    public ArrayList<String> getTitles(){
        return titles;
    }

    public ArrayList<String> getSites(){
        return sites;
    }
    public ArrayList<String> getPreferredSites(String category) {

        ArrayList<String> preferredSites = new ArrayList<>();
        if(category.toUpperCase().equals("COURSES")) {
            preferredSites.add("Udacity.com");
            preferredSites.add("edx.org");
            preferredSites.add("coursera.com");
            preferredSites.add("udemy.com");
            preferredSites.add("tutorialspoint.com");
        } else if(category.toUpperCase().equals("ARTICLES")) {
            preferredSites.add("medium.com");
            preferredSites.add("techcrunch.com");
        } else {
            preferredSites.add("scholar.google.com");
        }
        return preferredSites;
    }

    public void findLinks(String category, int num) throws Exception {
        urls.clear();
        titles.clear();
        sites.clear();

        for (String searchTerm: keywords) {

            ArrayList<String> preferredSites = getPreferredSites(category);

            for (int i = 0; i < preferredSites.size(); i++) {
                String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + " site:" + preferredSites.get(i) + "&num=" + num;
                //without proper User-Agent, we will get 403 error
                System.out.println(searchURL);

                Document doc;
                try {
                    doc = Jsoup.connect(searchURL).userAgent("Mozilla\5.0").get();
                } catch (HttpStatusException e) {
                    System.err.println("Http Exception: Failed to retieve results.");
                    return;
                }
                //below will print HTML data, save it to a file and open in browser to compare
                //System.out.println(doc.html());
                //If google search results HTML change the <h3 class="r" trooto <h3 class="r1"
                //we need to change below accordingly
                Elements results = doc.select("h3.r > a");
                System.out.println("");
                System.out.println("Showing results of site " + preferredSites.get(i));


                int k = 0;
                for (Element result : results) {
                    String linkHref = result.attr("href");
                    //String linkText = result.text();
                    String url = linkHref.substring(7, linkHref.indexOf("&"));
                    String title = displayTitle(url);
                    System.out.println("title :      "+url);
                    // g.openLink(url);

                    urls.add(url);


                    sites.add(preferredSites.get(i));
                    // System.out.println(titles.get(k++) + " | " + linkHref.substring(6, linkHref.indexOf("&")));
                    //System.out.println(title + " | " + url);
                    titles.add(title);
                }
            }
        }
        doneSearching = true;

    }



    public String displayTitle(String s) throws Exception
    {
        Document doc  = Jsoup.connect(s).get();
        // Elements metaElems = doc.select("meta");
        return doc.title();

        /*
        try {
            UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
            //System.out.println("SETTINGS:\n" + userAgent.settings);      //print the userAgent's default settings.
            userAgent.settings.autoSaveAsHTML = true;                    //change settings to autosave last visited page.

            userAgent.visit(s);                        //visit a url.
            String title = userAgent.doc.findFirst("<title>").getText(); //get child text of title element.
            //System.out.print(title);    //print the title
            return title;

        } catch (Exception e) {   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
            //System.err.println(e);
            return "";
        }*/
    }


    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    /* public static void main(String[] args) throws IOException, JauntException {
        GoogleSearch googleSearch = new GoogleSearch();

        //Taking search term input from console
        Scanner scanner = new Scanner(System.in);

        GoogleSearch g = new GoogleSearch();
        System.out.println("Please enter the search term.");

        String searchTerm = scanner.nextLine();

        System.out.println("Please enter the number of results. Example: 5 10 20");

        int num = scanner.nextInt();
        String cat = "COURSES";
        googleSearch.findLinks(searchTerm, cat, num);
        scanner.close();
    }*/
}
