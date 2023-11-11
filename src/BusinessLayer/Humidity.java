package BusinessLayer;

import java.util.Random;

public class Humidity extends ClimateMeasurement {
    private double humidityPercentage;

    public Humidity(int year, int month) {
        super(year, month);
        initializeMeasurements();
    }

    private void initializeMeasurements() {
        Random random = new Random();
        humidityPercentage = 100.0 * random.nextDouble();
    }

	public double getHumidityPercentage() {
		return humidityPercentage;
	}
	
	@Override
    public String toString() {
        return "Humidity Measurement - Year: " + getYear() + ", Month: " + getMonth()
                + ", Humidity Percentage: " + humidityPercentage;
    }
}