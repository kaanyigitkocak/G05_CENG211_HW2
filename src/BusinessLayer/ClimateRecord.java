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
    		for(int monthIndex = 0; monthIndex < 12; monthIndex++) {
    			String month = Month.values()[monthIndex].name();
        		for(City city: cities) {
        		
        			Humidity humidity = new Humidity(year, month);
            		RadiationAbsorption radiationAbsorption = new RadiationAbsorption(year, month);
            		Temperature temperature = new Temperature(year, month);
            		WindSpeed windSpeed = new WindSpeed(year, month);
            		city.addMeasurement(humidity);
            		city.addMeasurement(radiationAbsorption);
            		city.addMeasurement(temperature);
            		city.addMeasurement(windSpeed);
        		}
        		for(Country country: countries) {
        			Temperature temp = new Temperature(year, month);
        			country.addTemperatureMeasurement(temp);
        		}
        	}
    	}
    }

}