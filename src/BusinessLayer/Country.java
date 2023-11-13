package BusinessLayer;

import java.util.ArrayList;


public class Country {
    private String name;
    private ArrayList<Temperature> temperatures;

    public Country(String name) {
        this.name = name;
        this.temperatures = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

	public ArrayList<Temperature> getTemperatures() {
		return temperatures;
	}
	
	public void addTemperatureMeasurement(Temperature t) {
		temperatures.add(t);
	}
	
	
	@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Country: ").append(name).append("\n");

        stringBuilder.append("Temperatures:\n");
        for (Temperature t : temperatures) {
            stringBuilder.append(t.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}