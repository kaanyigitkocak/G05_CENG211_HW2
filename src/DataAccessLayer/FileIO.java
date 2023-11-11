package DataAccessLayer;

import BusinessLayer.Country;
import BusinessLayer.City;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
	public static final String filePath = "Files/countries_and_cities.csv";

	public static List<Country> readCountries() {
		ArrayList<Country> countries = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Country country = new Country(data[0]);
                countries.add(country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
		return countries;
	}
	
	public static List<City> readCities() {
		
		ArrayList<City> cities = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLoop = true;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", "); 
                for(String cityName: data) {
                	if(!firstLoop) {
                		City city = new City(cityName);
                		cities.add(city);
                	} else {
                		firstLoop = false;
                	}
                }
                firstLoop = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
		return cities;
	}
}
