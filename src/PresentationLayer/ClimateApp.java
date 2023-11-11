package PresentationLayer;

import BusinessLayer.City;
import BusinessLayer.ClimateRecord;
import BusinessLayer.Country;
import DataAccessLayer.FileIO;

public class ClimateApp {

	public static void main(String[] args) {
		
		ClimateRecord cr = new ClimateRecord();
		for(Country country: cr.getCountries()) {
			System.out.println(country.getName());
		}
		for(City city: FileIO.readCities()) {
			System.out.println(city.getName());
		}
		System.out.println(FileIO.readCities());
		System.out.println("anan");
	}

}
