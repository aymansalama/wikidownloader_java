package Testing;

import application.Languages;

import java.util.ArrayList;
import java.util.HashMap;

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

        String input = "wikipedia";
        ArrayList<String> expectedOut = parseJSON(file);

        HashMap actualOut = Languages.getLanguagesFromProject(input);
        final int[] correct = {0};
        final int[] incorrect = {0};
        HashMap langNotCorrect = new HashMap();

//        Loop through all the outputs and get similarity percentage
        actualOut.forEach((langid,lang) -> {
            if (expectedOut.contains(langid))
                correct[0]++;
            else{
                incorrect[0]++;
                langNotCorrect.put(langid,lang);
            }
        });


        System.out.println("Correct: " + correct[0] + " / " + expectedOut.size());
        System.out.println("No of incorrect: " + incorrect[0]);
        System.out.println("Incorrect: " + langNotCorrect);

    }
}
