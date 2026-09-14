import java.net.URL;

public class Book {
	
	int      id;
	int      count;
	String   ISBN;
	String[] authors;
	int      publicationYear;
	String   originalTitle;
	String   title;
	String   languageCode;
	float    avgRating;
	int[]    ratings;
	URL      imageURl;
	URL      imageURLSmall;
	
	//constructor 
	Book(int id, int count, String ISBN, String[] authors, int publicationYear,
		 String originalTitle, String title, String languageCode, float avgRating,
		 int[] ratings, URL imageURL, URL imageURLSmall) {
		
		this.id              = id;
		this.count           = count;
		this.ISBN            = ISBN;
		this.authors         = authors;
		this.publicationYear = publicationYear;
		this.originalTitle   = originalTitle;
		this.title           = title;
		this.languageCode    = languageCode;
		this.avgRating       = avgRating;
		this.ratings         = ratings;
		this.imageURl        = imageURL;
		this.imageURLSmall   = imageURLSmall;
		
	}
	
}