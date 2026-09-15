/*
 * File: BookArrayList.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: ArrayList objects that contains a list of books.
 * 				counterpart to the BookLinkedList object.
*/
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BookArrayList implements List_Functions{
	
	private ArrayList<Book> arrayList = new ArrayList<Book>();
	
	@Override
	//imports importList's contents into list
	public void importList(ArrayList<Book> importList) {
		for (Book book : importList) {
			arrayList.add(book);
		}
	}

	@Override
	public List searchList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sortList(String value, boolean value2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//gets Book object at index from list
	public Book getBook(int index) {
		return arrayList.get(index);
	}

	@Override
	//overwrites Book object at index from list with newBook
	public void editList(Book newBook, int index) {
		arrayList.set(index, newBook);
		
	}

	@Override
	//deletes Book object from list at index
	public void deleteList(int index) {
		arrayList.remove(index);
		
	}
	
	@Override
	//gets first ten book names as a arrayList of string type
	public ArrayList<String> getFirstTen() {
		
		ArrayList<String> firstTen = new ArrayList<String>();
		for(int i = 0; i < 10; i ++) {
			if (i < this.getSize()) {
				firstTen.add(this.getBook(i).getTitle());
			}
			else { return firstTen; }
		}
		return firstTen;
	}
	
	@Override
	//gets size of list
	public int getSize() {
		return arrayList.size();
	}

}
