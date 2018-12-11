package Testing;

import application.Main;

import java.io.IOException;
import java.util.ArrayList;

public class TestParseProject extends Parser {

    public static void main(String[] args) throws IOException {
        execute();
    }

    private static void execute() throws IOException {

//        Obtain the expected output from the JSON file
        String file = "project.json";
        ArrayList<String> expectedOut = parseJSON(file);
        int totalExpectedOut = expectedOut.size();

//        Obtain the actual output from the provided function
        String url = "";
        ArrayList<String> actualOut = Main.getListOfProjects(url);

        final int[] correct = {0};
        final int[] incorrect = {0};
        ArrayList<String> projectNotCorrect = new ArrayList<>();

//        Loop through all outputs and print similarity
        for (String projects: actualOut){
            if (expectedOut.contains(projects)) {
                correct[0]++;
                expectedOut.remove(projects);
            }
            else {
                incorrect[0]++;
                projectNotCorrect.add(projects);
            }
        }

        System.out.println("Correct: " + correct[0] + " / " + totalExpectedOut);
        System.out.println("No of incorrect: " + incorrect[0]);
        System.out.println("Incorrect: " + projectNotCorrect);
        System.out.println("Missing: " + expectedOut);
    }
}
