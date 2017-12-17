package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.Review;

public interface ReviewDBInterface {
	public void addReview(String postalcode,Review review);
	public List<Review> getReviews(String s);
	public Map<String,ArrayList<Review>> getAllReviews();
}
