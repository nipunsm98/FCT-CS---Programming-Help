package lk.ac.kln.fct.covid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataReader {
	private static final String JSON_ARRAY = "records";
    private static final String COUNTRY = "countriesAndTerritories";
    private static final String SELECTED_COUNTRY = "Sri_Lanka";
    private static final String DATE = "dateRep";
    private static final String REPORTED_CASES = "cases";
    private static final String DEATHS = "deaths";
    
    /**
     * This method reads the contents form a JSON file and prints the required data.
     * 
     * @param filePath the path to JSON file.
     */
    
	public void readData(String filePath) {
		try {
            String fileContent = Files.readString(Paths.get(filePath));
            JSONObject obj  = new JSONObject(fileContent);
            JSONArray arr = obj.getJSONArray(JSON_ARRAY);
            
            System.out.println("Date\t\tReported Cases\tDeaths");
            for(int i = 0; i < arr.length(); i ++) {
            	JSONObject record = arr.getJSONObject(i);
            	if(record.getString(COUNTRY).equals(SELECTED_COUNTRY)) {
            		System.out.printf("%s\t%s\t\t%s\n", record.getString(DATE), record.getString(REPORTED_CASES), record.getString(DEATHS));
            	}
            }
        } catch(IOException e) {
            e.printStackTrace();
        }		
	}
}
