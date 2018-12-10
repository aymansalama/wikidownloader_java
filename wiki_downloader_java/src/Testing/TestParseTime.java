package Testing;

import application.TimeStamp;

import java.util.ArrayList;

public class TestParseTime extends Parser {

    public static void main(String[] args) {
        execute();
    }

    private static void execute() {

//        Initialize inputs
        ArrayList<String> projectList = new ArrayList<>();
        ArrayList<String> languageList = new ArrayList<>();
        projectList.add("wiki");
        projectList.add("wiktionary");

//        Loop through all projects and initialize the language correspondingly
        projectList.forEach(projects -> {
            switch (projects){
                case "wiki":
                    languageList.add("en");
                    languageList.add("ceb");
                    languageList.add("sv");
                    break;

                case "wiktionary":
                    languageList.add("en");
                    languageList.add("mg");
                    languageList.add("als");
            }

//            Loop through all initialized languages
            languageList.forEach(lang -> {

//                Obtain expected output from JSON file
                String combine = lang + projects;
                String file = combine.concat("time.json");
                ArrayList<String> expectedOut = parseJSON(file);
                int totalExpectedOut = expectedOut.size();

//                Obtain actual output from provided function
                String url = "https://dumps.wikimedia.org/".concat(combine);
                TimeStamp ts = new TimeStamp(url);
                String[] actualOut = ts.get_time();

                final int[] correct = {0};
                final int[] incorrect = {0};
                ArrayList<String> timeNotCorrect = new ArrayList<>();

//                Loop through all outputs and print similarity
                for (String time: actualOut){
                    if (expectedOut.contains(time)) {
                        correct[0]++;
                        expectedOut.remove(time);
                    }
                    else {
                        incorrect[0]++;
                        timeNotCorrect.add(time);
                    }
                }

                System.out.println("%----------" + lang + projects + "----------%");
                System.out.println("Correct: " + correct[0] + " / " + totalExpectedOut);
                System.out.println("No of incorrect: " + incorrect[0]);
                System.out.println("Incorrect: " + timeNotCorrect);
                System.out.println("Missing: " + expectedOut);
            });

            languageList.clear();
        });

    }
}
