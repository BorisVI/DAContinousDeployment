package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class DestinationRestController {
	private final String USER_AGENT = "Mozilla/5.0";

	public DestinationRestController() {
	}

	@CrossOrigin
	@RequestMapping("/getInfo/{name}")
	public String getData(@PathVariable String name) throws ClientProtocolException, IOException {
		if(name.contains(" ")) {
			name=name.substring(0, name.indexOf(" "));
		}
		String url = "https://nl.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
				+ name;
		String result = sendHttpGetRequest(url);
		String result2 = result;
		if (result.contains(name + " kan verwijzen naar:")) {
			url = "https://nl.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
					+ name + "_(stad)";
			result2 = sendHttpGetRequest(url);
		}
		if (result2.contains("extract")&&!result2.contains("extract\":\"\"")) {
			
			result = result2;
		}else {
			url = "https://nl.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles="
					+ name + "_(België)";
			result2=sendHttpGetRequest(url);
		}
		if(result2.contains("extract")&&!result2.contains("extract\":\"\"")) {
			result=result2;
		}else {
			return "\"No information found for city.\"";
		}
		result = result.substring(result.indexOf("extract"));
		result = result.substring(9);
		result = result.substring(0, result.length() - 4);

		return "{\"info\":" + result + "}";
	}

	private String sendHttpGetRequest(String url) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}

}
