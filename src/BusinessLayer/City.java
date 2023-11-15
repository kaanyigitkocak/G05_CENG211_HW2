package BusinessLayer;
import java.util.ArrayList;

public class City {
    private String name;
    private ArrayList<Temperature> temperatures;
    private ArrayList<WindSpeed> windSpeeds;
    private ArrayList<Humidity> humidities;
    private ArrayList<RadiationAbsorption> radiationAbsorptions; 

    public City(String name) {
        this.name = name;
        this.temperatures = new ArrayList<>();
        this.windSpeeds = new ArrayList<>();
        this.humidities = new ArrayList<>();
        this.radiationAbsorptions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

	public ArrayList<Temperature> getTemperatures() {
		return temperatures;
	}
	public ArrayList<WindSpeed> getWindSpeeds() {
		return windSpeeds;
	}
	public ArrayList<Humidity> getHumidities() {
		return humidities;
	}
	public ArrayList<RadiationAbsorption> getRadiationAbsorptions() {
		return radiationAbsorptions;
	}
	
    public void addMeasurement(ClimateMeasurement measurement) {
        if (measurement instanceof Temperature) {
            temperatures.add((Temperature) measurement);
        } else if (measurement instanceof WindSpeed) {
            windSpeeds.add((WindSpeed) measurement);
        } else if (measurement instanceof Humidity) {
            humidities.add((Humidity) measurement);
        } else if (measurement instanceof RadiationAbsorption) {
            radiationAbsorptions.add((RadiationAbsorption) measurement);
        } else {
            System.out.println("Invalid measurement type.");
        }
    }

    public double calculateFeltTemperature(int year, String month) {
    	Temperature temperatureOfCity = null;
        WindSpeed windSpeedOfCity = null;
        Humidity humidityOfCity = null;
        RadiationAbsorption radiationAbsorptionOfCity = null;
    	for (Temperature temperature : temperatures) {
            if ((temperature.getYear() == year) && (temperature.getMonth() == month)) {
                temperatureOfCity = temperature;
                break; 
            }
        }

        for (WindSpeed windSpeed : windSpeeds) {
            if (windSpeed.getYear() == year && windSpeed.getMonth()== month) {
                windSpeedOfCity = windSpeed;
                break;
            }
        }

        for (Humidity humidity : humidities) {
            if (humidity.getYear() == year && humidity.getMonth() == month) {
                humidityOfCity = humidity;
                break;
            }
        }

        for (RadiationAbsorption radiationAbsorption : radiationAbsorptions) {
            if (radiationAbsorption.getYear() == year && radiationAbsorption.getMonth() == month) {
            	radiationAbsorptionOfCity = radiationAbsorption;
                break;
            }
        }
        
        if (temperatureOfCity == null) {

            System.out.println("Temperature measurement could not be found for the specified year and month.");
            return 0;
        }
        if (windSpeedOfCity == null) {
            System.out.println("Wind speed measurement could not be found for the specified year and month.");
            return 0;
        }

        if (humidityOfCity == null) {
            System.out.println("Humidity measurement could not be found for the specified year and month.");
            return 0;
        }
        if (radiationAbsorptionOfCity == null) {
            System.out.println("Radiation absorption measurement could not be found for the specified year and month.");
            return 0;
        }
        
        double feltTemperatureValue = 
        		temperatureOfCity.getCelciusMeasurement() 
        		+ 0.3 * (humidityOfCity.getHumidityPercentage() / 100.0) 
        		- 0.7 * (radiationAbsorptionOfCity.getUnitAbsorptionValue() / (windSpeedOfCity.getMetersPerSecond() + 10));
        return feltTemperatureValue;
    }
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("City: ").append(name).append("\n");

        stringBuilder.append("Temperatures:\n");
        for (Temperature temperature : temperatures) {
            stringBuilder.append(temperature.toString()).append("\n");
        }

        stringBuilder.append("Wind Speeds:\n");
        for (WindSpeed windSpeed : windSpeeds) {
            stringBuilder.append(windSpeed.toString()).append("\n");
        }

        stringBuilder.append("Humidities:\n");
        for (Humidity humidity : humidities) {
            stringBuilder.append(humidity.toString()).append("\n");
        }

        stringBuilder.append("Radiation Absorptions:\n");
        for (RadiationAbsorption radiationAbsorption : radiationAbsorptions) {
            stringBuilder.append(radiationAbsorption.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
