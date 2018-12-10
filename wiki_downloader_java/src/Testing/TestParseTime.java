package Testing;

import application.TimeStamp;

import java.util.ArrayList;

public class TestParseTime extends Parser {

    public static void main(String[] args) {
        execute();
    }

    private static void execute() {


//        Insert function here
//        Load csv file
//        Parse into arraylist
//        Loop through all arraylist object
//        Display similarity percentage

        String file = "enwikipediatime.json";
        String URL = "https://dumps.wikimedia.org/enwiki/";

        String[] tsArray = new TimeStamp(URL).get_time();
        ArrayList<String> outputTime = parseJSON(file);

        long totalItem = outputTime.size();
        long correctItem = 0;
        for(int i=0; i<tsArray.length; i++) {
            if (tsArray[i].equals(outputTime.get(i)))
                correctItem++;
        }

        System.out.println("Similarity = " + totalItem/correctItem);
    }
}
