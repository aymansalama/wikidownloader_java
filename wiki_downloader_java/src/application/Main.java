package application;
	
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;

import javax.swing.JProgressBar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
    final String mainPage = "/backup-index.html";
    String base_URL = "https://dumps.wikimedia.org";
    String choice_url = null;
    String project_title,language,timestamp,title;
	
	final ComboBox<String> projects = new ComboBox<String>();
    final ComboBox<Object> languages = new ComboBox<Object>();
    final ComboBox<String> timestamps = new ComboBox<String>();
    final ComboBox<Object> titles = new ComboBox<Object>();
    final Button download = new Button();
    
	HashMap<String, String> arrayOfprojects = new HashMap<String, String>();
    HashMap<?, ?> arrayOflanguages = null;
    

	public static void main(String[] args) throws IOException {		
		// need to contain 
		JsonParse final_url_part = new JsonParse("https://dumps.wikimedia.org/nowikisource/20181201/");
	    final_url_part.get_titles();

		launch(args);
	
	}
	
	@Override
	public void start(Stage primaryStage) {
		//Adding project titles
		arrayOfprojects.put("Wikipedia", "wiki");
		arrayOfprojects.put("Wiktionary", "wiktionary");
		arrayOfprojects.put("Wikibooks", "wikibooks");
		arrayOfprojects.put("Wikinews", "wikinews");
		arrayOfprojects.put("Wikiquote", "wikiquote");
		arrayOfprojects.put("Wikisource", "wikisource");
		arrayOfprojects.put("Wikiversity", "wikiversity");
		arrayOfprojects.put("Wikivoyage", "wikivoyage");

        
    //Adding UI for application
        ObservableList<String> listOfprojects = FXCollections.observableList(new ArrayList<String>(arrayOfprojects.keySet()));
        HBox hbox1 = new HBox(20);
        HBox hbox2 = new HBox(20);
        HBox hbox3 = new HBox(20);
        HBox hbox4 = new HBox(20);
        HBox hbox5 = new HBox(20);
        VBox box = new VBox(20);
        box.setPadding(new Insets(10));;
        download.setText("Download");
        
        //Seting combo box for projects
        projects.setItems(listOfprojects);
        
        projects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> ov, Object t, Object t1) {
            	//Saving project title choice
            	project_title = arrayOfprojects.get(t1).toString();
            	
            	//Getting languages based on project title
            	System.out.println("proj, t1: " + t1.toString());
            	arrayOflanguages = Languages.getLanguagesFromProject(t1.toString().toUpperCase());
            	System.out.println("arrayLang.key: " + arrayOflanguages.keySet().toString());
            	
            	List<Object> list = new ArrayList<Object>(arrayOflanguages.keySet());
            	
            	ObservableList<Object> listOflanguages = FXCollections.observableArrayList(list);
            	//Setting combo box for languages
                languages.setItems(listOflanguages);
            }
        });

        languages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> ov, Object t, Object t1) {
            	//Saving language choice
            	language = t1.toString();
            	
            	//Disabling the previous combo list to prevent changes
                projects.setDisable(true);
                
                //Getting timestamps based on language
                //Creating URL based on user choice
                choice_url = base_URL + "/" + language + project_title + "/";
                System.out.println("choice url: "+choice_url);
                TimeStamp ts = new TimeStamp(choice_url);
                System.out.println("ts.gettime: " + ts.get_time()[0] + " " + ts.get_time()[1]);
            	ObservableList<String> listOftimestamps = FXCollections.observableArrayList(
            			ts.get_time());
            	//Setting combo box for timestamps
            	timestamps.setItems(listOftimestamps);
            }
        });
        
        timestamps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> ov, Object t, Object t1) {
            	//Saving timestamp choice
            	timestamp = t1.toString();
            	
            	//Disabling the previous combo list to prevent changes
                languages.setDisable(true);
                
            	//Getting titles based on timestamp
                //Creating URL based on user choice
                choice_url = base_URL + "/" + language + project_title + "/" + timestamp;
                System.out.println(choice_url);
                JsonParse jp = new JsonParse(choice_url);
            	ObservableList listOftitles = FXCollections.observableArrayList(
            		jp.get_titles());
            	//Setting combo box for titles
                titles.setItems(listOftitles);
            }
        });
        
        titles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> ov, Object t, Object t1) {
                //Saving title choice
            	title = t1.toString();
            	
            	//Disabling the previous combo list to prevent changes
                timestamps.setDisable(true);
                
                //Appending title choice to URL to download dump
                choice_url = base_URL + "/" + language + project_title + "/" + timestamp + title;
                System.out.println(choice_url);
            }
        });
        
        //download button
        download.setOnAction(e -> downloadDumps(primaryStage));
        
        hbox1.getChildren().addAll(new Text("Choose Project"), projects);
        hbox2.getChildren().addAll(new Text("Choose Language"), languages);
        hbox3.getChildren().addAll(new Text("Choose Timestamp"), timestamps);
        hbox4.getChildren().addAll(new Text("Choose Titles"), titles);
        hbox5.getChildren().addAll(download);
        box.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);
        Scene scene = new Scene(box, 470, 230);
        primaryStage.setTitle("wikiDownloader_java");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//Download dumps button function
	private Object downloadDumps(Stage primaryStage) {		
		// TODO Add download dumps function here
		
		Download download1 = new Download(choice_url, "/"+title, primaryStage);
		download1.get_path();
		download1.run();
		return null;
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
