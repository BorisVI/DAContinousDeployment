package weatherdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.InMemoryDB;
import database.WeatherDBInterface;

public class Service {
	private WeatherDBInterface db;
	
	public Service(){
		db=InMemoryDB.getInstance();
	}
	
	public void addWeatherData(String pc,Example data){
		db.addCity(pc,data);
	}
	public Example getWeatherData(String pc){
		return db.getCity(pc);
	}
	public List<Example> getAllWeatherData(){
		List<Example> list = new ArrayList<Example>(db.getAllCities().values());
		return list;
	}
	public CalculatedData getCalculatedData(){
		return new CalculatedData(getAllWeatherData());
	}
	public Map<String,Example> getAllData(){
		return db.getAllCities();
	}
}
