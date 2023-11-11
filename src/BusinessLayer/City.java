package BusinessLayer;
import java.util.ArrayList;

class City {
    private String name;
    private ArrayList<ClimateMeasurement> measurements;  // Change here

    public City(String name) {
        this.name = name;
        this.measurements = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<ClimateMeasurement> getMeasurements() {  // Change here
        return measurements;
    }

    public void addMeasurement(ClimateMeasurement measurement) {
        measurements.add(measurement);
    }
}
