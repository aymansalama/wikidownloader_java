package application;
import java.io.BufferedReader;



/**
 * @author kesaven vulliamay
 * this class helps to get the links from the json file 
 * **/


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class JsonParse {
	
	// reveiced when user selects project name , language and timestamp
	String url_with_timestap_and_language;
	
	// used to get the json file to be used to get the links
	String add_json_extension ="/dumpstatus.json";
	
	
	
	public JsonParse(String url_with_timestap_and_language ) {
		
		this.url_with_timestap_and_language=url_with_timestap_and_language;
		
	}
	
	public String json() throws IOException {
		
		String url_parse= url_with_timestap_and_language + add_json_extension;
		
		  String result="";
		
		try {
			// parsing the url
			URL url= new URL(url_parse);
			
			  HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
              InputStream inputStream = httpURLConnection.getInputStream();
			
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

              StringBuilder str = new StringBuilder();

              try {
				while((result = reader.readLine()) != null){
				      str.append(result);
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              result = str.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		//System.out.println(result);
		return result;
		
	}
	
	public ArrayList<String> get_titles() {
		
		  ArrayList link_title = new ArrayList();
		
	      try {
	    	  
	    	  JSONObject obj = new JSONObject(json());
	    	  JSONObject jobs = (JSONObject) obj.get("jobs");
	    	  
	    	  
	    	   int jobs_length = jobs.length();
	    	   
	    	   JSONArray jsonarray = jobs.names();
	    	 
	    	   
	    	   ArrayList titles = new ArrayList();
	    	   
	    	   for (int a =0 ; a < jobs_length;a++) {
	    		   titles.add(jsonarray.get(a).toString());
	    	   }
	    	   
	    	 
	
	    	   // looping through the jobs and extracting the url
	    	   for (int a =0 ; a <titles.size();a++) {
	    		   JSONObject object_contain_key_files=(JSONObject) jobs.get(titles.get(a).toString());
	    		   
	    		   JSONArray array_contain_elements_in_title=object_contain_key_files.names();
	    		   
	    		   // index is zero because we want to access files if there is files
	    		   if(array_contain_elements_in_title.getString(0).equals("files")) {
	    			   JSONObject object_contain_in_file=  (JSONObject) object_contain_key_files.get(array_contain_elements_in_title.getString(0));
	    			   
	    			   JSONArray getting_object_link= object_contain_in_file.names();
			    	   
		    		   //getting the first one here 
			    	   String final_link_to_append= getting_object_link.getString(0).toString();
			    	   
			    	   link_title.add(final_link_to_append);
	    		   }
	    		 
	    	   }

	      }catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      return link_title;
			
	}

}
