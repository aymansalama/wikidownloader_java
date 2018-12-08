package Testing;

import java.util.ArrayList;

public class TestParseTime extends Parser {

    public static void main(String[] args)
    {
        execute();
    }

    private static void execute() {


//        Insert function here
//        Load csv file
//        Parse into arraylist
//        Loop through all arraylist object
//        Display similarity percentage

        String file = "enwikipediatime.json";

        ArrayList<String> outputTime = parseJSON(file);
        System.out.println(outputTime.size());
    }
}
