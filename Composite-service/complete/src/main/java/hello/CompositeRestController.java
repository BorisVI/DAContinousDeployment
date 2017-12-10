package hello;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CompositeRestController {
	private final String USER_AGENT = "Mozilla/5.0";
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	RestTemplate restTemplate;
	  
	public CompositeRestController(){
		
	}
	
	@CrossOrigin
	@RequestMapping("/get")
	public String getInfoByPc(@RequestParam String locationName) throws ClientProtocolException, IOException {
		String code = getCode(locationName);
		String weatherdata = getWeatherData(code);
		String destinationdata = getDestinationData(locationName);
		String reviews = getReviews(code);
		if(weatherdata.equals("\"\"")&&destinationdata.contains("No information found for city")){
			reviews = "[]";
		}
		return "{\"weatherdata\":"+weatherdata+",\"location\":\""+locationName+"\",\"destinationdata\":"+destinationdata+",\"reviews\":"+reviews+"}";
	}
	
	private String getWeatherData(String code) throws ClientProtocolException, IOException {
		String url = "http://Weather-API/get?code=" + code;
		String result = sendHttpGetRequest(url);
		if(result==null||result.trim().isEmpty()) {
			return "\"\"";
		}
		return result;
		
	}
	
	private String getCode(String name) throws ClientProtocolException, IOException {
		String url = "http://Weather-API/resolvename?name=" + name;
		String result= sendHttpGetRequest(url);
		result=result.replace("{\"postalcode\":\"", " ");
		result=result.substring(0,result.length()-2);
		result=result.trim();
		return result;
	}
	
	private String getDestinationData(String name) throws ClientProtocolException, IOException {
		String url = "http://Destination-service/getInfo/" + name;
		if(name.contains(" ")) {
			name = name.substring(0,name.indexOf(" "));
		}
		String result = sendHttpGetRequest(url);
		return result;
	}
	
	private String getReviews(String code) throws ClientProtocolException, IOException {
		String url = "http://Review-service/get?code=" + code;
		return sendHttpGetRequest(url);
	}
	private String sendHttpGetRequest(String url) throws ClientProtocolException, IOException {
		//HttpClient client = HttpClientBuilder.create().build();
		//HttpGet request = new HttpGet(url);
		//request.addHeader("User-Agent", USER_AGENT);
		//HttpResponse response = client.execute(request);
		//HttpEntity entity = response.getEntity();
		//String result = EntityUtils.toString(entity,"UTF-8");
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
}
