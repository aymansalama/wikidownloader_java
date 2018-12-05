package application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * @author Amirul Umar Bin Pandai
 *
 * Languages takes the name of the project and goes to an external wiki page
 * to return a list of all the supported languages, according to the
 * wiki page, in String format
 *
 */
public class Languages {

    /**
     *
     * @param project Name of the project.
     * @return ArrayList of languages.
     */
    public static ArrayList<String> getLanguagesFromProject(String project) {
        String html = "https://meta.wikimedia.org/wiki/Table_of_Wikimedia_projects";
        ArrayList<String> languages = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(html).get();
            Elements tableElements = doc.select("table.wikitable.sortable");
            Elements rows = tableElements.select("tr");

            project.toUpperCase();
            int column = 0;
            switch (project) {
                case "WIKIPEDIA":
                    column = 3;
                    break;
                case "WIKTIONARY":
                    column = 4;
                    break;
                case "WIKIBOOKS":
                    column = 5;
                    break;
                case "WIKINEWS":
                    column = 6;
                    break;
                case "WIKIQUOTE":
                    column = 7;
                    break;
                case "WIKISOURCE":
                    column = 8;
                    break;
                case "WIKIVERSITY":
                    column = 9;
                    break;
                case "WIKIVOYAGE":
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
