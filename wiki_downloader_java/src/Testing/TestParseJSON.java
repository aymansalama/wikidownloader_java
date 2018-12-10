package Testing;

import application.JsonParse;

import java.util.ArrayList;
// Function for testing the Parsing JSON file to obtain dump file titles
public class TestParseJSON extends Parser{
    public static void main(String[] args)
    {
        runtest();
    }
    private static void runtest() {
        //Hardcoded a specific project title, language and timestamp as there are too many possible options if not hardcoded
        String test_url = "https://dumps.wikimedia.org/hiwikiquote/20180720/";
        //Apply the function created by development team
        JsonParse jp = new JsonParse(test_url);
        //Convert the obtained input into an arraylist
        ArrayList actualOutput = jp.get_titles();
        //Create a json file that using data obtained from the website itself (This is the expected output)
        String file = "hindititle.json";
        //Create a variable to store the number of correct outputs obtained when comparing actual output and expected output
        final int[] correct = {0};
        //Apply the parsing function created by the testing team
        ArrayList<String> expectedOut = parseJSON(file);
        //Create a loop to compare all the outputs from expected output and actual output
        actualOutput.forEach((n) -> {
            if (expectedOut.contains(n)) {
                correct[0]++;
            }
        });
        //Print out the results obtained from the test
        System.out.println("Correct: " + correct[0] + " / " + expectedOut.size());
        int incorrect = expectedOut.size() - correct[0];
        System.out.println("No of incorrect: " + incorrect);
    }

}
