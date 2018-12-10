package Testing;

import java.util.ArrayList;

public class TestParseLang extends Parser {

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

        String file = "langwikipedia.json";

        ArrayList<String> outputLanguage = parseJSON(file);
        System.out.println(outputLanguage.size());
    }
}
