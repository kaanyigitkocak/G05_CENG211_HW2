package PresentationLayer;

import BusinessLayer.City;
import BusinessLayer.ClimateRecord;
import BusinessLayer.Country;

public class ClimateApp {

	public static void main(String[] args) {
		
		ClimateRecord cr = new ClimateRecord();
		for(Country country: cr.getCountries()) {
			System.out.println(country.getName());
		}
		for(City city: cr.getCities()) {
			System.out.println(city.getName());
		}
		System.out.println("Mama let my heart go");
	}

}
