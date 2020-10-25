/**
 * <class movie to create the object of a movie which will 
 * be used to evaluate and compare>
 *
 * CSC 1351 Programming Project No <4> Section <1>
 *
 * @author <Uluc Ozdenvar>
 * @since <April 1 2019>
 *
 */
public class Movie implements Comparable<Movie> {

	 private String movieTitle;
     private int releaseYear;
     private String rating;
     private int movieReview;

     /**
 	 * <method to act as a constructor>
 	 *
 	 * CSC 1351 Programming Project No <4> Section <1>
 	 *
 	 * @author <Uluc Ozdenvar>
 	 * @since <April 1 2019>
 	 *
 	 */ 
     public Movie(String Title, int Year, String Rating, int Review) {
    	 
    	 movieTitle = Title;
    	 releaseYear = Year;
    	 rating = Rating;
    	 movieReview = Review;
     }
     
     /**
 	 * <accesor method to getTitle of movie>
 	 *
 	 * CSC 1351 Programming Project No <4> Section <1>
 	 *
 	 * @author <Uluc Ozdenvar>
 	 * @since <April 1 2019>
 	 *
 	 */
     
     public String getTitle() {
    	 return movieTitle;
     }
     
     /**
  	 * <accesor method to getYEar of movie>
  	 *
  	 * CSC 1351 Programming Project No <4> Section <1>
  	 *
  	 * @author <Uluc Ozdenvar>
  	 * @since <April 1 2019>
  	 *
  	 */
     public int getYear() {
    	 return releaseYear;
     }
     
     /**
  	 * <accesor method to getRating of movie>
  	 *
  	 * CSC 1351 Programming Project No <4> Section <1>
  	 *
  	 * @author <Uluc Ozdenvar>
  	 * @since <April 1 2019>
  	 *
  	 */
     public String getRating() {
    	 return rating;
     }
     
     /**
  	 * <accesor method to getReview of movie>
  	 *
  	 * CSC 1351 Programming Project No <4> Section <1>
  	 *
  	 * @author <Uluc Ozdenvar>
  	 * @since <April 1 2019>
  	 *
  	 */
     public int getReview() {
    	 return movieReview;
     }
     
     /**
  	 * <compareTo method to compare movie objects to one another>
  	 *
  	 * CSC 1351 Programming Project No <4> Section <1>
  	 *
  	 * @author <Uluc Ozdenvar>
  	 * @since <April 1 2019>
  	 *
  	 */
     
	@Override
	public int compareTo(Movie other) { 
		
		//return (movieTitle.compareTo(other.getTitle()));
		
		if(movieTitle.compareTo(other.getTitle()) < 0) {
			System.out.println(movieTitle + "Movie 1" + other.getTitle());
			System.out.println("HELLLLO3");
			return -1;
		}
		else if(movieTitle.equals(other.getTitle()) && releaseYear < other.getYear()) {
			System.out.println("HELLLLO2");
			return -1;
		}
		else if(movieTitle.equals(other.getTitle()) && releaseYear == other.getYear()) {
			return 0;
		}
		else
			return 1;
	}
	
	/**
 	 * <toString to return the string of the movie>
 	 *
 	 * CSC 1351 Programming Project No <4> Section <1>
 	 *
 	 * @author <Uluc Ozdenvar>
 	 * @since <April 1 2019>
 	 *
 	 */
	public String toString() {
		String rString = "Title: " + movieTitle + ", Year : "+
				releaseYear + ", Rating: " + rating + ", Review:"+ 
				movieReview + ";";
						
		return rString;
	}

}
