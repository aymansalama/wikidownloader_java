//Class to test whether the url is correct and able to download the requested dump files

package Testing;

import application.Download;
import application.JsonParse;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class TestDumpFiles {

    public static void main(String[] args)
    {
        testUrl();
    }

    private static void testUrl()
    {
        int i;
        String result="";


        String URL = "https://dumps.wikimedia.org/enwikinews/20180720/";
        JsonParse jp = new JsonParse(URL);
        ArrayList<String> listOftitles = jp.get_titles();
        String [] urlName = new String[listOftitles.size()];


        //for loop to get each titles and put into an array named urlName and print out each URL to indicate that
        //the test to get project URL along with titles is successful
        for(i =0 ; i < listOftitles.size() ;i++)
        {
            urlName[i]= listOftitles.get(i);
            result = URL + urlName[i];
            System.out.println(result);
        }

        //create a random number generator to select one of the URL to download to test download function
        Random rand  = new Random();

        int n = rand.nextInt(listOftitles.size());

        testDownload(urlName,n);
    }

    //method to test download function
    private static void testDownload(String[] urlLinks, int randomNumber)
    {
        String URL = "https://dumps.wikimedia.org/enwikinews/20180720/";
        String dumpFileLink = urlLinks[randomNumber];

        Download download1 = new Download(URL,dumpFileLink);
        download1.run();
    }
}


