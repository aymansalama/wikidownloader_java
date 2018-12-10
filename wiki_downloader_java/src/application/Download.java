package application;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;

/**
 *@author kesaven vulliamay
 *
 *function getpath must be called first to choose download location,
 *only then the run function is called
 *
 **/


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JProgressBar;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Download implements Runnable {

	String link;// this is the download link
	String url_select;// used to name the file
	Stage stage;// stage to display the ui to choose download location
	String filepath;// used to store the file path where to download the numbs
	File out;//
	
	
	/**
	 *@param link used for the download link
	 *@param stage is used for the ui dispplay to choose download location
	 *@param url_select use to name the file
	 **/

	public Download(String link ,String url_select, Stage stage) {
		this.link=link;
		this.url_select=url_select;
		this.stage=stage;

	}
	
	
	public void get_path() {
		DirectoryChooser chooser = new DirectoryChooser();
		File  file =chooser.showDialog(stage);
		filepath = file.getAbsolutePath();
		out= new File(filepath+url_select);
	}

	@Override
	public void run() {
		
		try {
			
			// Url object to store the download link
			URL url= new URL(link);
			
			// opening an http connection to connect to the url
			HttpURLConnection connection= (HttpURLConnection) url.openConnection();
			
			// getting the file size in bytes
			double filesize = connection.getContentLengthLong();
			
			
			// getting the buffered input and storing it in the BufferedInputStream object
			BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
			
			// creating a file for the output stream
			FileOutputStream outputStream = new FileOutputStream(this.out);
			
			// getting the output buffer and specifying the file
			BufferedOutputStream bufferedoutput= new BufferedOutputStream(outputStream,1024);	
			
			
			// storing the data read
			byte [] buffer = new byte [1024];
			
			// used to calculate percentage of file being downloaded
			double download= 0.00;
			
			int read = 0;
			
			// keeping track of the download percentage
			double percentDowload= 0.00;

			// looping through the output stream
			while ((read = in.read(buffer, 0, 1024)) >= 0) {
				
				bufferedoutput.write(buffer, 0, read);
				
				download += read;
				
				percentDowload = (download * 100) /filesize;
				
				String percent = String.format("%.2f", percentDowload);
				
				System.out.println("percent download is " + percent);
					
			}

			System.out.println("download is complete");

			bufferedoutput.close();
			
			in.close();
			
			outputStream.close();
			
		
			// catching any malformedException
		} catch (MalformedURLException e) {
			
			System.out.print("malformed url" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
			// catching any IO exception
		} catch (IOException e) {
			
			System.out.print("IO Exeption is " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		

	}

}
