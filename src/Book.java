/*
 * File: Book.java
 * Author: Chris Grimm & Noah Kabelman
 * Date: 9/14/26
 * Institution: Ferris State University
 * SENG300: Software Data Structures and Algorithms
 * Assignment 3
 * Description: 
*/

import java.net.URL;

public class Book implements Comparable<Book> {
	
	// Identifiers                       CSV Column Names
	private int bookId;                  // book_id
    private int goodreadsBookId;         // goodreads_book_id
    private int bestBookId;              // best_book_id
    private int workId;                  // work_id
    private int booksCount;              // books_count

    // Publication Info
    private String isbn;                 // isbn
    private String isbn13;               // isbn13
    private String authors;              // authors
    private int originalPublicationYear; // original_publication_year
    private String originalTitle;        // original_title
    private String title;                // title
    private String languageCode;         // language_code

    // Ratings & Reviews
    private double averageRating;        // average_rating
    private int ratingsCount;            // ratings_count
    private int workRatingsCount;        // work_ratings_count
    private int workTextReviewsCount;    // work_text_reviews_count
    private int ratings1;                // ratings_1
    private int ratings2;                // ratings_2
    private int ratings3;                // ratings_3
    private int ratings4;                // ratings_4
    private int ratings5;                // ratings_5

    // Image Links
    private URL imageUrl;             // image_url
    private URL smallImageUrl;        // small_image_url
    
    // Constructors
    public Book(int bookId, String isbn, String authors, int originalPublicationYear, String title, double averageRating) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.authors = authors;
        this.originalPublicationYear = originalPublicationYear;
        this.title = title;
        this.averageRating = averageRating;
    }
     
    public Book() {
    	
    }
    
    // Methods
    public int getId() { return bookId; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return authors; }
    public int getOriginalPublicationYear() { return originalPublicationYear; }
    public double getRating() { return averageRating; }

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return title + " by " + authors + " (" + averageRating + "★)";
    }

}
