package Testing;

import application.JsonParse;

import java.util.ArrayList;
import java.util.HashMap;

public class TestParseJSON extends Parser{
    public static void main(String[] args)
    {
        runtest();
    }
    private static void runtest() {
        String test_url = "https://dumps.wikimedia.org/hiwikiquote/20180720/";
        JsonParse jp = new JsonParse(test_url);
        String file = "hindititle.json";
        ArrayList actualOutput = jp.get_titles();
        final int[] correct = {0};
        ArrayList<String> expectedOut = parseJSON(file);
        final String[] x = {""};
        String [] titleNotCorrect = {};
        actualOutput.forEach((n) -> {
            if (expectedOut.contains(n)) {
                correct[0]++;
            }
        });
        System.out.println("Correct: " + correct[0] + " / " + expectedOut.size());
        int incorrect = expectedOut.size() - correct[0];
        System.out.println("No of incorrect: " + incorrect);
    }

}
