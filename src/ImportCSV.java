/*
 * File: ImportCSV.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: Imports CSV of books and returns that as an array list of
 * 				books.
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {
	public static ArrayList<Book> importCSV(File csvFile) {
		
		//read from csv file
		ArrayList<Book> returnList = new ArrayList<Book>();
		String line;
		int    i = 0;
		final String REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				//read into words array
				String[] book = line.split(REGEX);
				
				returnList.add(new Book(Integer.parseInt(book[0]), 
														 book[5], 
														 book[7], 
							    (int) Double.parseDouble(book[8]), 
														 book[9], 
									  Double.parseDouble(book[12]),
									  					 book[11],
												(new URI(book[21])).toURL()));
				
			}
			br.close();
		}
		catch (Exception e){ //print exception
			System.out.println(e);
			System.out.println(e.getMessage());
		}
		return returnList;
	}
}
