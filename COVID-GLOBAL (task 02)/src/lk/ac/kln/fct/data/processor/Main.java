package lk.ac.kln.fct.data.processor;

import java.util.ArrayList;

import lk.ac.kln.fct.data.pojo.Record;

public class Main {
	private static final String FILE_PATH = "/Users/ASUS/eclipse-workspace/TASK 02/src/covid-19-data.json";
	private static final String DATE = "18/05/2020";
	
	public static void main(String[] args) {
		DataProcessor processor = new DataProcessor();
		sortAndPrint(processor.getDataByDate(FILE_PATH, DATE));
	}
		
	public static void sortAndPrint(ArrayList<Record> records) {
		records.sort((record1, record2)-> Integer.compare(record2.getCases(), record1.getCases()));
		records.forEach((record) -> {
			if(record.getCases() >= 1000) {
				System.out.printf("%s : %d : %d\n", record.getCountriesAndTerritories(), record.getCases(), record.getDeaths());
			}
		}); 
	}
}
