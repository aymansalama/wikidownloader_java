package Testing;

import application.TimeStamp;

import java.util.ArrayList;

public class TestParseTime extends Parser {

    public static void main(String[] args) {
        execute();
    }

    private static void execute() {

        ArrayList<String> projectList = new ArrayList<>();
        ArrayList<String> languageList = new ArrayList<>();
        projectList.add("wiki");
        projectList.add("wiktionary");

//        Loop through certain inputs of the lang + project
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

            languageList.forEach(lang -> {

                String combine = lang + projects;
                String url = "https://dumps.wikimedia.org/".concat(combine);

                String file = combine.concat("time.json");
                ArrayList<String> expectedOut = parseJSON(file);
                int totalExpectedOut = expectedOut.size();

                TimeStamp ts = new TimeStamp(url);
                String[] actualOut = ts.get_time();

                final int[] correct = {0};
                final int[] incorrect = {0};
                ArrayList<String> timeNotCorrect = new ArrayList<>();

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
