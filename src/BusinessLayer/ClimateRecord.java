package BusinessLayer;

import java.util.ArrayList;

public class ClimateRecord {
    private ArrayList<Country> countries;

    public ClimateRecord() {
        this.countries = new ArrayList<>();
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

}