package hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import weatherdata.Example;

@Component

public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    
    private ArrayList<String> pclist;
    private Iterator<String> it;
   
    
    public ScheduledTasks(){
    	pclist = new ArrayList<String>();
    	try {
			readFile();
		} catch (FileNotFoundException e) {
			log.info(e.getMessage());
		}
    	it = pclist.iterator();
   }
    
    private void readFile() throws FileNotFoundException {
    	File file = new File("postalcodes.txt");
    	Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()){
			pclist.add(scanner.nextLine());
		}
		scanner.close();
	}
    private String getNextCity(){
    	try{
    		return it.next();
    	}catch(NoSuchElementException e){
    		it=pclist.iterator();
    		return it.next();
    	}
    }

	@Scheduled(fixedRate = 1100)
    public void reportWeather() throws ClientProtocolException, IOException {
		String postalcode = getNextCity();
    	RestTemplate restTemplate = new RestTemplate();
    	Example api = restTemplate.getForObject(
				"http://api.openweathermap.org/data/2.5/weather?zip="+postalcode+",be&appId=45b04770cedcb6bad2cff888edb2c390", Example.class);
		//log.info(api.toString());
		String url = "http://localhost:8082/add/" + postalcode;
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writeValueAsString(api);
		log.info(payload);
		StringEntity entity = new StringEntity(payload,ContentType.APPLICATION_JSON);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
	}
}
