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

        ArrayList<String> projectList = new ArrayList<>();
        projectList.add("wikipedia");
        projectList.add("wikibooks");
        projectList.add("wikinews");
        projectList.add("wikiquote");
        projectList.add("wikisource");
        projectList.add("wikiversity");
        projectList.add("wikivoyage");
        projectList.add("wiktionary");

//        Loop through all the input types
        projectList.forEach(type -> {

            String file = "lang".concat(type).concat(".json");
            ArrayList<String> expectedOut = parseJSON(file);
            int totalExpectedOut = expectedOut.size();

            HashMap actualOut = Languages.getLanguagesFromProject(type);
            final int[] correct = {0};
            final int[] incorrect = {0};
            HashMap langNotCorrect = new HashMap();

//        Loop through all the outputs and get similarity percentage
            actualOut.forEach((langid,lang) -> {
                if (expectedOut.contains(langid)) {
                    correct[0]++;
                    expectedOut.remove(langid);
                }
                else {
                    incorrect[0]++;
                    langNotCorrect.put(langid,lang);
                }
            });

            System.out.println("%----------" + type + "----------%");
            System.out.println("Correct: " + correct[0] + " / " + totalExpectedOut);
            System.out.println("No of incorrect: " + incorrect[0]);
            System.out.println("Incorrect: " + langNotCorrect);
            System.out.println("Missing: " + expectedOut);
        });

    }
}
