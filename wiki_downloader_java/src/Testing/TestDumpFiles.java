//Class to test whether the url is correct and able to download the requested dump files

package Testing;

import application.JsonParse;
import java.util.ArrayList;

public class TestDumpFiles {

    public static void main(String[] args)
    {
        testUrl();
    }

    private static void testUrl()
    {
        int i;
        String result;


        String URL = "https://dumps.wikimedia.org/enwikinews/20180720/";
        JsonParse jp = new JsonParse(URL);
        ArrayList<String> listOftitles = jp.get_titles();
        String [] urlName = new String[listOftitles.size()];

        for(i =0 ; i < listOftitles.size() ;i++)
        {
            urlName[i]= listOftitles.get(i);
            result = URL + urlName[i];
            System.out.println(result);
        }
    }
}


