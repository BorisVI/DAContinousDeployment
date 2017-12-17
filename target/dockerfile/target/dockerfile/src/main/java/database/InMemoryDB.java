package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Review;

public class InMemoryDB implements ReviewDBInterface{
	private static InMemoryDB instance;
	private Map<String,ArrayList<Review>> map;
	
	public static InMemoryDB getInstance(){
		if(instance == null){
			return new InMemoryDB();
		}
		return instance;
	}
	
	private InMemoryDB(){
		map = new TreeMap<String,ArrayList<Review>>();
		instance = this;
	}

	@Override
	public void addReview(String postalcode,Review review) {
		ArrayList<Review> list;
		if(map.containsKey(postalcode)) {
			list = map.get(postalcode);
		}else {
			list = new ArrayList<Review>();
		}
		list.add(review);
		map.put(postalcode, list);
	}

	@Override
	public List<Review> getReviews(String postalcode) {
		return map.get(postalcode);
	}

	@Override
	public Map<String, ArrayList<Review>> getAllReviews() {
		return map;
	}
	
}


