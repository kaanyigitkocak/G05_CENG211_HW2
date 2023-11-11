package BusinessLayer;

import java.util.Random;

class Humidity extends ClimateMeasurement {
    private double humidityPercentage;

    public Humidity(int year, String month) {
        super(year, month);
        initializeMeasurements();
    }

    private void initializeMeasurements() {
        Random random = new Random();
        humidityPercentage = 0.0 + (100.0 - 0.0) * random.nextDouble();
    }
}