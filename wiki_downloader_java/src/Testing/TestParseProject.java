package Testing;

import java.util.ArrayList;

public class TestParseProject extends Parser {

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

        String file = "project.json";

        ArrayList<String> outputProjects = parseJSON(file);
    }
}
