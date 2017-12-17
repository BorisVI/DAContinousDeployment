package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.InMemoryDB;
import database.ReviewDBInterface;

public class Service {
	private ReviewDBInterface db;
	
	public Service(){
		db=InMemoryDB.getInstance();
	}
	
	public void addReviewForCity(String pc,Review review){
		db.addReview(pc,review);
	}
	public List<Review> getReviewsForCity(String pc){
		return db.getReviews(pc);
	}
	public Map<String,ArrayList<Review>> getAllReviews(){
		return db.getAllReviews();
	}
}
