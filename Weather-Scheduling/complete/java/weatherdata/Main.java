
package weatherdata;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "temp",
    "pressure",
    "humidity",
    "temp_min",
    "temp_max"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    @JsonProperty("temp")
    private double temp;
    @JsonProperty("pressure")
    private int pressure;
    @JsonProperty("humidity")
    private int humidity;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(double temp, int pressure, int humidity, double tempMin, double tempMax) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    @JsonProperty("temp")
    public double getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(double temp) {
        this.temp = temp;
    }

    @JsonProperty("pressure")
    public int getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("humidity")
    public int getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("temp_min")
    public double getTempMin() {
        return tempMin;
    }

    @JsonProperty("temp_min")
    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    @JsonProperty("temp_max")
    public double getTempMax() {
        return tempMax;
    }

    @JsonProperty("temp_max")
    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "Main [temp=" + temp + ", pressure=" + pressure + ", humidity=" + humidity + ", tempMin=" + tempMin
				+ ", tempMax=" + tempMax + "]";
	}





}
