package application;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

/**
 * @author Amirul Umar Bin Pandai
 *
 * Languages takes the name of the project and goes to an external wiki page
 * to return a list of all the supported languages, according to the
 * wiki page, in a HashMap.
 *
 */
public class Languages {

    // Static HashMap of project names and their column numbers
    private static final HashMap<String, Integer> projects;
    // put() methods were used as there was no other way to
    // initialize like an array
    static {
        projects = new HashMap<>();
        projects.put("WIKIPEDIA", 3);
        projects.put("WIKTIONARY", 4);
        projects.put("WIKIBOOKS", 5);
        projects.put("WIKINEWS", 6);
        projects.put("WIKIQUOTE", 7);
        projects.put("WIKISOURCE", 8);
        projects.put("WIKIVERSITY", 9);
        projects.put("WIKIVOYAGE", 10);
    }

    /**
     *
     * @param project Name of the project.
     * @return HashMap of languages (key = language code, value = English name of language)
     */
    public static HashMap getLanguagesFromProject(String project) {
        // Site URL of table that contains references for supported languages
        String html = "https://meta.wikimedia.org/wiki/Table_of_Wikimedia_projects";

        // Return HashMap
        HashMap languages = new HashMap();

        try {
            // Gets full HTML document using site URL
            Document doc = Jsoup.connect(html).get();
            // Query for main table
            Elements tableElements = doc.select("table.wikitable.sortable");
            // Query for table rows
            Elements rows = tableElements.select("tr");

            // Uppercase project name for flexibility
            String projectUp = project.toUpperCase();
            // Gets column number from project value
            int column = projects.get(projectUp);

            // Delete deprecated languages indicated by the del tag in the table
            for (Element element : doc.select("del")) {
                element.remove();
            }

            // Add languages to languages HashMap
            for (int i = 1; i < rows.size(); i++) {
                Elements cols = rows.get(i).select("td");
                // Checks if language exists
                if (!cols.get(column).text().equals("")) {
                    String languageCode = cols.get(0).text();
                    // Remove ":" character from end of language code
                    languages.put(
                            cols.get(1).text(),
                            languageCode.substring(0, languageCode.length() - 1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
    }
}
