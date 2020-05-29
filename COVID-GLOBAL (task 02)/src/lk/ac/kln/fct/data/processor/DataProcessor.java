package lk.ac.kln.fct.data.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lk.ac.kln.fct.data.pojo.Record;
import lk.ac.kln.fct.data.utils.Constants;

public class DataProcessor {
	private static DateFormat dateformat = new SimpleDateFormat(Constants.DATE_FORMAT);
	/**
	 * This method reads the contents form a given JSON file and prints the required data.
	 * 
	 * @param filePath the path to JSON file.
	 */
	public ArrayList<Record> getDataByDate(String filePath, String searchDate) {
		ArrayList<Record> covidStat = new ArrayList<Record>();
		
		try {
			String fileContent = Files.readString(Paths.get(filePath));
			JSONObject obj  = new JSONObject(fileContent);
			JSONArray arr = obj.getJSONArray(Constants.JSON_ARRAY);
			
			for(int i = 0; i < arr.length(); i ++) {
				JSONObject element = arr.getJSONObject(i);
				if(element.getString(Constants.DATE).equals(searchDate)) {
					try {
						Date date = dateformat.parse(element.getString(Constants.DATE));
						int deaths = element.getInt(Constants.DEATHS);
						int cases = element.getInt(Constants.CASES);
						String country = element.getString(Constants.COUNTRY);
						String continent = element.getString(Constants.CONTINENT);
						covidStat.add(new Record(date, deaths, cases, country, continent));
					} catch (JSONException | ParseException e) {
						e.printStackTrace();
					}					
				}				
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return covidStat;
	}
}
