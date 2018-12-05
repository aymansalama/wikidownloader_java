/*
    author: Amirul Umar Bin Pandai

    Languages:
    takes the name of the project and goes to an external wiki page
    to return a list of all the supported languages, according to the
    wiki page, in String format

    input: String name of project
    output: ArrayList of languages
*/
package application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Languages {

    public static ArrayList<String> getLanguagesFromProject(String project) {
        String html = "https://meta.wikimedia.org/wiki/Table_of_Wikimedia_projects";
        ArrayList<String> languages = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(html).get();
            Elements tableElements = doc.select("table.wikitable.sortable");
            Elements rows = tableElements.select("tr");
			
            int column = 0;
            switch (project) {
                case "Wikipedia":
                    column = 3;
                    break;
                case "Wiktionary":
                    column = 4;
                    break;
                case "Wikibooks":
                    column = 5;
                    break;
                case "Wikinews":
                    column = 6;
                    break;
                case "Wikiquote":
                    column = 7;
                    break;
                case "Wikisource":
                    column = 8;
                    break;
                case "Wikiversity":
                    column = 9;
                    break;
                case "Wikivoyage":
                    column = 10;
                    break;
                default:
                    break;
            }
			
			// Delete deprecated languages indicated by the del tag
            for (Element element : doc.select("del")) {
                element.remove();
            }
			
            int index = 1;
            for (int i = 1; i < rows.size(); i++) {
                Elements cols = rows.get(i).select("td");
                // Checks if language exists
                if (!cols.get(column).text().equals("")) {
                    languages.add(cols.get(1).text());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
    }
}
