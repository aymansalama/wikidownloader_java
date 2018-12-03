package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author kesaven vulliamay
 * 
 * This class pulls the html characters from the webpage and return it as a string
 * 
 * Instruction to use:
 * create an instance of Html_get with parameters the link of the webpage as link
 * call the run function to get the characters 
 * the fucntion  get_result() can be called to retreive the result of the 
 * 
 * */

public class HtmlGet implements Runnable {

	String html_link;
	String result="";// used to text of the 

	/**
	 *@param  html_link is used to get the link of the web page
	 * 
	 * */

	public HtmlGet (String html_link) {

		this.html_link=html_link;


	}

	@Override
	public void run() {


		try {

			// create an URL object
			URL url = new URL(html_link);
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			InputStream input = connect.getInputStream();

			BufferedReader reader =  new BufferedReader(new InputStreamReader(input));


			// using string builder to construct the string
			StringBuilder str = new StringBuilder();
			String line = null;


			while((result = reader.readLine()) != null){
				str.append(result);
			}
			result = str.toString();

			System.out.println(result);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public String get_result() {

		return result; 

	}

}
