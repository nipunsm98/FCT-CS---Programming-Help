package lk.ac.kln.fct.covid;

public class DataProcessor {	
	private static final String FILE_PATH = "/Users/ASUS/eclipse-workspace/COVID-19 (task 01)/src/covid-19-data.json";

	public static void main(String[] args) {
		DataReader reader = new DataReader();
        reader.readData(FILE_PATH);
	}
}
