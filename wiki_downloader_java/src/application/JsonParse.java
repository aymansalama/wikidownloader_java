package application;
import java.io.BufferedReader;

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
	
	
	
	public JsonParse() {
		
		
	}
	
	public String json() throws IOException {
		
		  String result="";
		
		try {
			URL url= new URL("https://dumps.wikimedia.org/nowikisource/20181201/dumpstatus.json");
			
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
		
		System.out.println(result);
		return result;
		
	}
	
	public void get_titles() {
		
	      try {
	    	  
	    	  JSONObject obj = new JSONObject(json());
	    	  JSONObject jobs = (JSONObject) obj.get("jobs");
	    	  
	 
	    	  System.out.println("length of jibs is "+jobs.length());
	    	  
	    	  
	    	   int jobs_length = jobs.length();
	    	   
	    	   JSONArray jsonarray = jobs.names();
	    	 
	    	   
	    	   ArrayList titles = new ArrayList();
	    	   
	    	   for (int a =0 ; a < jobs_length;a++) {
	    		   titles.add(jsonarray.get(a).toString());
	    	   }
	    	   
	    	   System.out.println("size of the list is :"+titles.size() + titles);
	    	   
	   
	    	   
	    		
	    		
	    		   
	    	   	
	    	  jobs.get("categorytable");
	    	  
	    	  JSONObject cat=(JSONObject) jobs.get("categorytable");
	    	  
	    	  JSONObject files=(JSONObject) cat.get("files");
	    	  
	    	  
	    	  
	    
	    	  
	    	  System.out.println(cat.has("files"));
	    	  
	   
	    	  
	    	   System.out.println( jobs.toString());
	    	   
	    	   System.out.println( cat.toString());
	    	   
	    	   System.out.println( files.toString());
	    	   
	    	   
	    	
	   
	    	   
	 
	    	  
	    	 // JSONObject jobject
			
			
			

           // JSONArray arr = new JSONArray(titleinfo);
//            
//            for (int i =0;i < arr.length();i++){
//
//                JSONObject jsonPart = arr.getJSONObject(i);
//
//            System.out.println( jsonPart.getString("files"));
//         
//		} 
            
	      }catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
