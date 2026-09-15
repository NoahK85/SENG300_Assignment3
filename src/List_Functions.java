/*
 * File: List_Functions.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: interface with list functions.
*/
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public interface List_Functions {
	
	//Methods
	int getSize();
	List searchList(String id);
	void sortList(String value, boolean value2);
	Book getBook(int index);
	void editList(Book newBook, int index);
	void deleteList(int index);
	void importList(ArrayList<Book> List);
	ArrayList<String> getFirstTen();

}
