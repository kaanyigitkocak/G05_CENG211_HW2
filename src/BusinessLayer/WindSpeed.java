package BusinessLayer;

import java.util.Random;

public class WindSpeed extends ClimateMeasurement {
    private double metersPerSecond;
    private double kmPerHour;

    public WindSpeed(int year, String month) {
        super(year, month);
        initializeMeasurements();
    }

    private void initializeMeasurements() {
        Random random = new Random();
        metersPerSecond = 0.0 + (113.2 - 0.0) * random.nextDouble();
        kmPerHour = metersPerSecondToKmPerHour(metersPerSecond);
    }

    private double metersPerSecondToKmPerHour(double metersPerSecond) {
        return metersPerSecond * 3.6;
    }

	public double getKmPerHour() {
		return kmPerHour;
	}
	
	public double getMetersPerSecond() {
		return metersPerSecond;
	}
	
	 @Override
	    public String toString() {
	        return "Wind Speed Measurement - Year: " + getYear() + ", Month: " + getMonth()
	                + ", Meters Per Second: " + metersPerSecond + ", Km Per Hour: " + kmPerHour;
	    }
}