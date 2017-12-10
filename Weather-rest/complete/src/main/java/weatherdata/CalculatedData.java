package weatherdata;

import java.util.ArrayList;
import java.util.List;

public class CalculatedData {
	private double averagetemp;
	private String hottestCity;
	private double hottestTemp;
	
	public CalculatedData(List<Example> e){
		calculateData(e);
	}
	private void calculateData(List<Example> e) {
		calcAT((ArrayList<Example>)e);
		calcHC((ArrayList<Example>)e);
		
	}
	private void calcHC(ArrayList<Example> e) {
		double hottestTemp = e.get(0).getMain().getTemp();
		String cityName = e.get(0).getName();
		for(Example i : e){
			double temp = i.getMain().getTemp();
			if(temp>hottestTemp){
				hottestTemp = temp;
				cityName = i.getName();
			}
		}
		hottestCity = cityName;
		this.hottestTemp = hottestTemp;
	}
	private void calcAT(ArrayList<Example> e) {
		double total=0;
		for(Example i : e){
			total+=i.getMain().getTemp();
		}
		averagetemp=total/e.size();
	}
	public double getaverageTemp(){
		return averagetemp;
	}
	public String getHottestCity(){
		return hottestCity;
	}
	public double getHottestTemp(){
		return hottestTemp;
	}
	
}
