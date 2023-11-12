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

    //belirtilen ay ve yıl için feltTemperature değerini döndürür yiğit adam.
    public double calculateFeltTemperature(int year, String month) {
    	Temperature temperature = null;
        WindSpeed windSpeed = null;
        Humidity humidity = null;
        RadiationAbsorption rA = null;
    	for (Temperature t : temperatures) {
            if ((t.getYear() == year) && (t.getMonth() == month)) {
                temperature = t;
                break; 
            }
        }

        for (WindSpeed ws : windSpeeds) {
            if (ws.getYear() == year && ws.getMonth()== month) {
                windSpeed = ws;
                break;
            }
        }

        for (Humidity h : humidities) {
            if (h.getYear() == year && h.getMonth() == month) {
                humidity = h;
                break;
            }
        }

        for (RadiationAbsorption ra : radiationAbsorptions) {
            if (ra.getYear() == year && ra.getMonth() == month) {
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
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("City: ").append(name).append("\n");

        stringBuilder.append("Temperatures:\n");
        for (Temperature t : temperatures) {
            stringBuilder.append(t.toString()).append("\n");
        }

        stringBuilder.append("Wind Speeds:\n");
        for (WindSpeed ws : windSpeeds) {
            stringBuilder.append(ws.toString()).append("\n");
        }

        stringBuilder.append("Humidities:\n");
        for (Humidity h : humidities) {
            stringBuilder.append(h.toString()).append("\n");
        }

        stringBuilder.append("Radiation Absorptions:\n");
        for (RadiationAbsorption ra : radiationAbsorptions) {
            stringBuilder.append(ra.toString()).append("\n");
        }

        return stringBuilder.toString();
    }
}
