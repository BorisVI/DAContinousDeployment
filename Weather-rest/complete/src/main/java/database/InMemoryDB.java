package database;

import java.util.Map;
import java.util.TreeMap;

import weatherdata.Example;

public class InMemoryDB implements WeatherDBInterface{
	private static InMemoryDB instance;
	private Map<String,Example> map;
	
	public static InMemoryDB getInstance(){
		if(instance == null){
			return new InMemoryDB();
		}
		return instance;
	}
	
	private InMemoryDB(){
		map = new TreeMap<String,Example>();
		instance = this;
	}
	
	public void addCity(String pc,Example obj){
		map.put(pc, obj);
	}
	
	public Example getCity(String pc){
			return map.get(pc);
	}
	
	public Map<String,Example> getAllCities(){
		return map;
	}
}


