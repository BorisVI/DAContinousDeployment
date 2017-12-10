package hello;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import weatherdata.CalculatedData;
import weatherdata.Example;
import weatherdata.Service;

@RestController
public class WeatherRestController {
	private Service service;
	
	public WeatherRestController(){
		service = new Service();
	}
	@CrossOrigin
	@RequestMapping("/alldata")
	public List<Example> getWeatherData(){
		return service.getAllWeatherData();
	}
	@CrossOrigin
	@RequestMapping("/calc")
	public CalculatedData getCalcData(){
		return service.getCalculatedData();
	}
	@CrossOrigin
	@RequestMapping("/get")
	public Example getWeatherDataByPc(@RequestParam String code) {
		return service.getWeatherData(code);
	}
	@CrossOrigin
	@RequestMapping("/add/{pc}")
	public void addData(@RequestBody Example data,@PathVariable String pc){
		service.addWeatherData(pc, data);
	}
	@CrossOrigin
	@RequestMapping("/resolve")
	public String resolvePostalCode(@RequestParam String code) {
		return service.getWeatherData(code).getName();
	}
	@CrossOrigin
	@RequestMapping("resolvename")
	public String resolveName(@RequestParam String name) {
		Set<Entry<String,Example>> set  = service.getAllData().entrySet();
		Iterator<Entry<String,Example>> it = set.iterator();
		while(it.hasNext()) {
			Entry<String,Example> entry = it.next();
			if(entry.getValue().getName().contains(name)) {
				return "{\"postalcode\":\""+entry.getKey()+"\"}";
			}
		}
		return "{\"postalcode\":\"undefined\"}";
	}
}
