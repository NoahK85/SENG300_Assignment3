/*
 * File: BookLinkedList.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: linked list object containing a list of Books.
 * 				Counterpart to the BookArrayList object.
*/
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookLinkedList implements List_Functions{
	LinkedList<Book> linkedList = new LinkedList<Book>();
	
	@Override
	//imports importList's contents into list
	public void importList(ArrayList<Book> importList) {
		for (Book book : importList) {
			linkedList.add(book);
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
		linkedList.get(index);
		return null;
	}

	@Override
	//overwrites Book object at index from list with newBook
	public void editList(Book newBook, int index) {
		linkedList.set(index, newBook);
		
	}

	@Override
	//deletes Book object from list at index
	public void deleteList(int index) {
		linkedList.remove(index);
		
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
		return linkedList.size();
	}

}
