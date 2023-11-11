package BusinessLayer;

import java.util.Random;

public class Temperature extends ClimateMeasurement {
    private double celciusMeasurement;
    private double fahrenheitMeasurement;
    private double kelvinMeasurement;

    public Temperature(int year, int month) {
        super(year, month);
        initializeMeasurements();
    }

    private void initializeMeasurements() {
        Random random = new Random();
        celciusMeasurement = -40.0 + (50.0 - (-40.0)) * random.nextDouble();
        fahrenheitMeasurement = celsiusToFahrenheit(celciusMeasurement);
        kelvinMeasurement = celsiusToKelvin(celciusMeasurement);
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
    
    public double getCelciusMeasurement() {
		return celciusMeasurement;
	}

	public double getFahrenheitMeasurement() {
		return fahrenheitMeasurement;
	}

	public double getKelvinMeasurement() {
		return kelvinMeasurement;
	}
	
	@Override
    public String toString() {
        return "Temperature Measurement - Year: " + getYear() + ", Month: " + getMonth()
                + ", Celsius: " + celciusMeasurement + ", Fahrenheit: " + fahrenheitMeasurement
                + ", Kelvin: " + kelvinMeasurement;
    }
}