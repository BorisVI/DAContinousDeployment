package database;

import java.util.Map;

import weatherdata.Example;

public interface WeatherDBInterface {
	public void addCity(String s,Example e);
	public Example getCity(String s);
	public Map<String,Example> getAllCities();
}
