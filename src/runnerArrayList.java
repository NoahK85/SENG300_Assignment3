/*
 * File: runnerArrayList.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: runs program using ArrayLists.
*/
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class runnerArrayList{
	public static void main(String[] args) throws IOException {
		//setup array
		File csv = new File("Data\\books.csv");
		BookArrayList bookList = new BookArrayList();
		bookList.importList(ImportCSV.importCSV(csv));

		LibraryFrame libraryFrame = new LibraryFrame(bookList);
		
		
	}
}
