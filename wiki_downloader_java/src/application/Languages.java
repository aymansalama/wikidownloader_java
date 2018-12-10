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
    private static final HashMap<String, String> projects;
    // put methods were used as there was no other way to
    // initialize like an array
    static {
        // Values are site URL suffixes (e.g. https://meta.wikimedia.org/wiki/Wiktionary)
        projects = new HashMap<>();
        projects.put("WIKIPEDIA", "List_of_Wikipedias/sortable");
        projects.put("WIKTIONARY", "Wiktionary");
        projects.put("WIKIBOOKS", "Wikibooks");
        projects.put("WIKINEWS", "Wikinews");
        projects.put("WIKIQUOTE", "Wikiquote");
        projects.put("WIKISOURCE", "Wikisource");
        projects.put("WIKIVERSITY", "Wikiversity");
        projects.put("WIKIVOYAGE", "Wikivoyage");
    }

    /**
     *
     * @param project Name of the project.
     * @return HashMap of languages (key = language code, value = English name of language)
     */
    public static HashMap getLanguagesFromProject(String project) {
        // Uppercase project name for flexibility
        String projectUp = project.toUpperCase();
        int table;
        // Resolves issue with Wikivoyage web page having two tables with the same class
        // but the desired table is the second table, while the rest of the web pages
        // have the main table as the first table
        if (projectUp.equals("WIKIVOYAGE")) {
            table = 1;
        } else {
            table = 0;
        }
        // Site URL of primary table that contains list of supported languages
        String html = "https://meta.wikimedia.org/wiki/" + projects.get(projectUp);
        HashMap languages = new HashMap();
        try {
            // Gets full HTML document using site URL
            Document doc = Jsoup.connect(html).get();
            // Query for main table
            // (accepts sortable but doesn't accept jquery-tablesorter for some reason)
            Element tableElements = doc.select(".sortable").get(table);
            // Query for table rows
            Elements rows = tableElements.select("tr");

            // Add languages to languages HashMap
            for (int i = 1; i < rows.size(); i++) {
                Elements cols = rows.get(i).select("td");
                // Get language code
                String languageCode = cols.get(3).text();
                // Get English name of language
                String language = cols.get(1).text();
                // Insert key and value into HashMap
                languages.put(languageCode, language);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languages;
    }
}
