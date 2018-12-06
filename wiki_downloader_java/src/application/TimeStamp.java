package application;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TimeStamp {
	
	
	// using the path to get the html pages
	String base_path;
	
	// default constructor need to be changed to get a path for the url
	
	/**
	 * @param param base_path to connect to the url to get the string
	 **/
	public TimeStamp(String base_path) {
		
		this.base_path = base_path;
		
	}
	
	
	public String [] get_time() {
		
		 String array_string_timestamp[]=null;
		
		try {
			
			// using the Jsoup api to get the html file to parse
			Document doc = Jsoup.connect(base_path).get();
			
			
			// the array list is used to store all the time stamps
			ArrayList<String> array_list_timestamp=new ArrayList<String>();
			
			// used for storing the time stamp as a string and adding it to the arraylist
            String timeref = ""; 
            
            // used to store the size of the time stamp
            int size_array_list_time ;
			

			// using as reference the html tag a to get the string 
			 Elements links = doc.getElementsByTag("a");
			 // looping through the answers and storing it in the arraylist
             for (Element el : links) { 
            	 timeref = el.attr("href");
            	 array_list_timestamp.add(timeref); // add value to ArrayList
             }
             
             size_array_list_time = array_list_timestamp.size();
            
            // remove unnecessary "../" in the html file 
             array_list_timestamp.remove(0);
            
            
            // getting the size again as the arrays list has been updated
            size_array_list_time = array_list_timestamp.size();
            
           // remove unnecessary "latest" in the html file 
            array_list_timestamp.remove(size_array_list_time - 1);
           
    
            // System.out.println("the size of the resulting list is " +array_list_timestamp.size());
			
           //  System.out.println(array_list_timestamp);
             
            // making an array the size of the array list
             array_string_timestamp= new String[array_list_timestamp.size()];
             
            
             // converting the arraylist into an array
               array_list_timestamp.toArray(array_string_timestamp);
               
              
             //System.out.println(Arrays.toString(array_string_timestamp));
               
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array_string_timestamp;
	}
	
	

}
