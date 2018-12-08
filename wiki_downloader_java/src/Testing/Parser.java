package Testing;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Parser {

    static ArrayList<String> parseJSON (String fileName) {
        String filePath = new File("").getAbsolutePath().concat("\\wiki_downloader_java\\src\\Testing\\");
        String file = filePath.concat(fileName);

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) parser.parse(new FileReader(file));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>(jsonArray);
    }
}
