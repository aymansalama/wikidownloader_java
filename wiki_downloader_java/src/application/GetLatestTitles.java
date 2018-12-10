package application;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * **
 * @author kesaven vulliamay
 * this class get the titles to be downloaded in the case timestamp is latest
 *
 */

public class GetLatestTitles {
	
	String base_path;
	
	public GetLatestTitles(String base_path) {
		this.base_path = base_path;
		
	}
	

	 // return an array of the titles
	public String [] get_title_latest() {
		
		 String array_string_titles[]=null;
		
		try {
			
			// using the Jsoup api to get the html file to parse
			Document doc = Jsoup.connect(base_path).get();
			
			
			// the array list is used to store all the time stamps
			ArrayList<String> array_list_titles=new ArrayList<String>();
			
			// used for storing the time stamp as a string and adding it to the arraylist
           String title_ref = ""; 
           
           // used to store the size of the time stamp
           int size_title ;
			

			// using as reference the html tag a to get the string 
			 Elements links = doc.getElementsByTag("a");
			 // looping through the answers and storing it in the arraylist
            for (Element el : links) { 
            	title_ref = el.attr("href");
            	array_list_titles.add(title_ref); // add value to ArrayList
            }
            
            size_title = array_list_titles.size();
            // remove unnecessary "../" in the html file 
            array_list_titles.remove(0);
         
          
   
           System.out.println("the size of the resulting list is " +array_list_titles.size());
			
           System.out.println(array_list_titles);
            
           // making an array the size of the array list
           array_string_titles= new String[array_list_titles.size()];
            
           
            // converting the arraylist into an array
           array_list_titles.toArray(array_string_titles);
              
             
            //System.out.println(Arrays.toString(array_string_timestamp));
              
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array_string_titles;
	}

}
