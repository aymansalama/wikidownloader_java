package Testing;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Extend this class to use the parsing function, input the json file into the same folder (Testing) and voila
 * JSON file must be in an array format. Non-array form will fail
 */
class Parser {

    /**
     * @param fileName fileName of JSON file
     * @return ArrayList of object inside
     */
    static ArrayList<String> parseJSON (String fileName) {
        String filePath = new File("").getAbsolutePath().concat("\\src\\Testing\\");
        String file = filePath.concat(fileName);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            // open file
            Object obj = parser.parse(new FileReader(file));

            // convert to array
            jsonArray = (JSONArray) (obj);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>(jsonArray);
    }
}
