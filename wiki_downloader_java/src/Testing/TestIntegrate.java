package Testing;

import application.Download;
import application.JsonParse;
import application.Languages;
import application.TimeStamp;

import java.util.*;

public class TestIntegrate extends Parser {

    public static void main(String[] args) {
        execute();
    }

    private static void execute() {
        String projectName = "wikipedia";
        String projectCode = "wiki";

        // get list of languages
        // choose random language
        HashMap langList = Languages.getLanguagesFromProject(projectName);


        Random rand  = new Random();
        int n = rand.nextInt(langList.size() - 1);

        Iterator it = langList.entrySet().iterator();
        HashMap.Entry entry = null;
        while(--n > 0) {
            it.next();
        }

        HashMap.Entry pair = (HashMap.Entry)it.next();
        String langCode= pair.getValue().toString();
        System.out.println("Chosen language: " + pair.getKey().toString());

        // get list of timestamps
        // choose random timestamp
        String url = "https://dumps.wikimedia.org/" + langCode + projectCode;
        String[] ts = new TimeStamp(url).get_time();

        n = rand.nextInt(ts.length-1);
        String timeStamp = ts[n];
        System.out.println("Chosen timestamp: " + timeStamp);

        // get list of titles
        // choose random title
        url += "/" + timeStamp + "/";
        JsonParse jp = new JsonParse(url);
        ArrayList<String> listOfTitles = jp.get_titles();

        n = rand.nextInt(listOfTitles.size() - 1);
        String fileName = listOfTitles.get(n);
        System.out.println("Chosen file: " + fileName);

        Download download = new Download(url, fileName);
        download.run();

        String filePath = download.getFilePath();
        System.out.println(filePath);
    }
}
