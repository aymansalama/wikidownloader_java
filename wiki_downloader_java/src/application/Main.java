package application;
	
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			

					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
		
		String link = "https://dumps.wikimedia.org/backup-index.html";
		
		ArrayList<String> namesList = getListOfProjects(link);
		
		for(int i = 0; i < namesList.size(); i++) {
			if(namesList.get(i).equals(""))
				namesList.remove(i);
		}
		
		for(int i = 0; i < namesList.size(); i++) {
			System.out.println(namesList.get(i));
		}
		System.out.println("Total size is: " + namesList.size());
		
//		//example to get the HTML characters
//		HtmlGet Html = new HtmlGet("https://dumps.wikimedia.org/backup-index.html");
//		Html.run();
//		
//		String result = Html.get_result();
//		
//		System.out.println(result);	

	}
	
	public static ArrayList<String> getListOfProjects(String linkToWebsite) throws IOException {
		
		/*
		 * @param linkToWebsite is just a link to a web-page in the form of a String
		 * @return This function will return an ArrayList with all the Text elements of an <a href http://...> ... </a> attribute in a page 
		 */
		
		ArrayList<String> arrayList = new ArrayList<String>();
		Document doc = Jsoup.connect(linkToWebsite).get();
		
		Elements classElement = doc.getElementsByTag("li");
		
		int i = 0;
		
		for(Element src: classElement) {
			if(src.toString().contains("Dump complete")) {
				arrayList.add(src.select("a[href]").text());
			}
		}
		return arrayList;
	}
	
}
