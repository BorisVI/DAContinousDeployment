package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import domain.Review;
import domain.Service;

@RestController
public class ReviewRestController {
	private ArrayList<String> reviewlist = new ArrayList<String>();
	
	private Service service;
	public ReviewRestController(){
		service = new Service();
		reviewlist.add("Zalige plaats, geweldige locatie om feestjes te geven of gewoon te zijn.");
		reviewlist.add("Geweldige sfeer!");
		reviewlist.add("Afschuwelijke vakantiebestemming. Absoluut niet gaan.");
		reviewlist.add("Zeker doen! Geweldige hotels.");
		reviewlist.add("Mooie zonsondergang.");
		reviewlist.add("Straaten zijn goed onderhouden.");
		reviewlist.add("Mensen zijn super onvriendelijk, afrader.");
		reviewlist.add("Nee, nee en nog is nee, echt niet gaan.");
	}
	@CrossOrigin
	@RequestMapping("/allreviews")
	public Map<String,ArrayList<Review>> getWeatherData(){
		return service.getAllReviews();
	}
	
	@CrossOrigin
	@RequestMapping("/get")
	public List<Review> getReviewByPc(@RequestParam String code) {
		if(service.getReviewsForCity(code)==null) {
			ArrayList<Review> list = new ArrayList<Review>();
			Random rn = new Random();
			int i = rn.nextInt(reviewlist.size()-1);
			Review review = new Review(reviewlist.get(i),"Anoniem");
			list.add(review);
			return list;
		}
		return service.getReviewsForCity(code);
	}
	
	@CrossOrigin
	@RequestMapping("/add/{pc}")
	public void addData(@RequestBody Review data,@PathVariable String pc){
		service.addReviewForCity(pc, data);
	}
	
}
