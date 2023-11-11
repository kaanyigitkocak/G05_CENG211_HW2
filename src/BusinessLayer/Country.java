package BusinessLayer;

import java.util.ArrayList;


public class Country {
    private String name;
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
}