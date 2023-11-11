package BusinessLayer;

import java.util.ArrayList;

import DataAccessLayer.FileIO;

public class ClimateRecord {
    private ArrayList<Country> countries;
    private ArrayList<City> cities;

    public ClimateRecord() {
    	setCitiesAndCountries();
    	setMeasurementsForCountriesAndCities();
    }

    private void setCitiesAndCountries() {
    	countries = (ArrayList<Country>) FileIO.readCountries();
        cities = (ArrayList<City>) FileIO.readCities();
    }
    
    public ArrayList<Country> getCountries() {
        return countries;
    }
    
    public ArrayList<City> getCities() {
		return cities;
	}

    public void addCountry(Country country) {
        countries.add(country);
    }
    
    public void addCity(City city) {
        cities.add(city);
    }
    
    private void setMeasurementsForCountriesAndCities() {
    	for(int year = 2020; year <= 2022; year++) {
    		for(int month = 1; month <= 12; month++) {
        		for(City city: cities) {
        			Humidity humidity = new Humidity(year, month);
            		RadiationAbsorption rA = new RadiationAbsorption(year, month);
            		Temperature temp = new Temperature(year, month);
            		WindSpeed wS = new WindSpeed(year, month);
            		city.addMeasurement(humidity);
            		city.addMeasurement(rA);
            		city.addMeasurement(temp);
            		city.addMeasurement(wS);
        		}
        		for(Country country: countries) {
        			Temperature temp = new Temperature(year, month);
        			country.addTemperatureMeasurement(temp);
        		}
        	}
    	}
    }

}