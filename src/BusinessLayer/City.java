package BusinessLayer;
import java.util.ArrayList;

public class City {
    private String name;
    ArrayList<Temperature> temperatures;
    ArrayList<WindSpeed> windSpeeds;
    ArrayList<Humidity> humidities;
    ArrayList<RadiationAbsorption> radiationAbsorptions; 

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
            System.out.println("Geçersiz ölçüm türü");
        }
    }

    //belirtilen ay için feltTemperature değerini döndürür yiğit adam.
    public double calculateFeltTemperature(int year, String month) {
    	Temperature temperature = null;
        WindSpeed windSpeed = null;
        Humidity humidity = null;
        RadiationAbsorption rA = null;
    	for (Temperature t : temperatures) {
            if (t.year == year && t.month.equals(month)) {
                temperature = t;
                break; 
            }
        }

        for (WindSpeed ws : windSpeeds) {
            if (ws.year == year && ws.month.equals(month)) {
                windSpeed = ws;
                break;
            }
        }

        for (Humidity h : humidities) {
            if (h.year == year && h.month.equals(month)) {
                humidity = h;
                break;
            }
        }

        for (RadiationAbsorption ra : radiationAbsorptions) {
            if (ra.year == year && ra.month.equals(month)) {
                rA = ra;
                break;
            }
        }
        
        if (temperature == null) {
            System.out.println("Temperature measurement could not be found for the specified year and month.");
            return 0;
        }
        if (windSpeed == null) {
            System.out.println("Wind speed measurement could not be found for the specified year and month.");
            return 0;
        }

        if (humidity == null) {
            System.out.println("Humidity measurement could not be found for the specified year and month.");
            return 0;
        }
        if (rA == null) {
            System.out.println("Radiation absorption measurement could not be found for the specified year and month.");
            return 0;
        }
        
        double feltTemperatureValue = 
        		temperature.getCelciusMeasurement() 
        		+ 0.3 * (humidity.getHumidityPercentage() / 100.0) 
        		- 0.7 * (rA.getUnitAbsorptionValue() / (windSpeed.getMetersPerSecond() + 10));
        return feltTemperatureValue;
    }
}
