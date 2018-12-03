package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			
			// test the function
			
			String wikidumps="https://dumps.wikimedia.org";
			
			String filter="/nlwiktionary/20181201/nlwiktionary-20181201-categorylinks.sql.gz";
			
			String link = wikidumps+filter;
			File out;
			
		
			
			
			// directorychooser is used to get the path for download location
			DirectoryChooser chooser = new DirectoryChooser();
			File  file =chooser.showDialog(primaryStage);
			
			// filepath stores the path for the download location
			String filepath = file.getAbsolutePath();
			
			out= new File(filepath+"/dumps.sql.gz");
			
		// download the file
			new Thread(new Download(link,out)).start();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	
}
