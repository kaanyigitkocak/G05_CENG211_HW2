package BusinessLayer;

import java.util.ArrayList;


public class Country {
    private String name;
    private ArrayList<Temperature> temperatures;
    private ArrayList<City> cities;

    public Country(String name) {
        this.name = name;
        this.cities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        cities.add(city);
    }

	public ArrayList<Temperature> getTemperatures() {
		return temperatures;
	}
	
	public void addMeasurement(Temperature t) {
		temperatures.add(t);
	}
}