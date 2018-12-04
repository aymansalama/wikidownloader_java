package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.PrototypeObject;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    final String URL = null;
	
	final ComboBox projects = new ComboBox();
    final ComboBox languages = new ComboBox();
    final ComboBox timestamps = new ComboBox();
    final ComboBox titles = new ComboBox();
    final Button download = new Button();
	
    public static void main(String[] args) {
		launch(args);
		
		/*//exmaple to get the html characters
		HtmlGet Html = new HtmlGet("https://www.youtube.com/");
		Html.run();
		
		String result = Html.get_result();
		
		System.out.println(result);*/
		
	}
	
	@Override
	public void start(Stage primaryStage) {

	// Adding dummy lists
	//TODO Remove dummy lists
		List combox1List = new ArrayList();
        for (int i = 1; i < 10; i++) {
            combox1List.add(i);
        }

        final Map combox2Map = new HashMap();

        for (int i = 0; i < combox1List.size(); i++) {
            List l = new ArrayList();
            for (int j = 1; j < 10; j++) {
                int k = (int) combox1List.get(i) * 10 + j;
                l.add(k);
            }
            combox2Map.put(combox1List.get(i), l);
        }
        final Map combox3Map = new HashMap();
        for (Object o : combox1List) {
            for (Object o1 : (List) combox2Map.get(o)) {
                List l = new ArrayList();
                for (int i = 1; i < 10; i++) {
                    int value = (int) o1 * 10 + i;
                    l.add(value);
                }
                combox3Map.put(o1, l);
            }
        }
        
    //Adding UI for application
        ObservableList listOfprojects = FXCollections.observableList(combox1List);
        HBox hbox1 = new HBox(20);
        HBox hbox2 = new HBox(20);
        HBox hbox3 = new HBox(20);
        HBox hbox4 = new HBox(20);
        HBox hbox5 = new HBox(20);
        VBox box = new VBox(20);
        
        download.setText("Download");
        
        projects.setItems(listOfprojects);
        projects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
            	 //TODO set items of combobox languages
            	ObservableList combox2 = FXCollections.observableArrayList((List) combox2Map.get(t1));
                languages.setItems(combox2);
            }
        });

        languages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (t1 != null) {
                    //Disabling the previous combo list to prevent changes
                    projects.setDisable(true);
                    
                    //TODO set items of combobox timestamps
                    ObservableList combox3 = FXCollections.observableArrayList((List) combox3Map.get(t1));
                    timestamps.setItems(combox3);
                }
            }
        });
        
        timestamps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (t1 != null) {
                    //Disabling the previous combo list to prevent changes
                    languages.setDisable(true);
                    //TODO set items of combobox titles
                    
                }
            }
        });
        
        titles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                if (t1 != null) {
                    //Disabling the previous combo list to prevent changes
                    timestamps.setDisable(true);
                    //TODO set items of combobox titles
                    
                }
            }
        });
        
        //download button
        download.setOnAction(e -> downloadDumps());
        
        hbox1.getChildren().addAll(new Text("Choose Project"), projects);
        hbox2.getChildren().addAll(new Text("Choose Language"), languages);
        hbox3.getChildren().addAll(new Text("Choose Timestamp"), timestamps);
        hbox4.getChildren().addAll(new Text("Choose Titles"), titles);
        hbox5.getChildren().addAll(download);
        box.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);
        Scene scene = new Scene(box, 300, 500);

        primaryStage.setTitle("wikiDownloader_java");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//Download dumps button function
	private Object downloadDumps() {		
		// TODO Add download dumps function here
		
		return null;
	}

	
	
	
}
