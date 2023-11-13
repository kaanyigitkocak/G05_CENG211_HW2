package BusinessLayer;

import java.util.ArrayList;
import java.util.Scanner;

import DataAccessLayer.FileIO;

public class ClimateRecord {
    private ArrayList<Country> countries;
    private ArrayList<City> cities;

    public ClimateRecord() {
    	setCitiesAndCountries();
    	setMeasurementsForCountriesAndCities();
    }

    private void setCitiesAndCountries() {
    	countries = (ArrayList<Country>) FileIO.readCountries();
        cities = (ArrayList<City>) FileIO.readCities();
    }
    
    public ArrayList<Country> getCountries() {
        return countries;
    }
    
    public ArrayList<City> getCities() {
		return cities;
	}

    public void addCountry(Country country) {
        countries.add(country);
    }
    
    public void addCity(City city) {
        cities.add(city);
    }
    
    public void calculateAverageTemperatureCountry(Scanner scanner) {

        Country selectedCountry = null;
        
        boolean countryFound = false;

        while (!countryFound) {
            System.out.print("Enter the name of the country: ");
            String countryName = scanner.nextLine();
            

            for (Country country : countries) {
                if (country.getName().equalsIgnoreCase(countryName)) {
                    selectedCountry = country;
                    countryFound = true;
                    break;
                }
            }

            if (!countryFound) {
                System.out.println("Country is invalid: " + countryName);
            }
        }
        
        System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
        System.out.print("Please select the temperature unit: ");
        int temperatureType = scanner.nextInt();

        // Handle incorrect temperature type input
        while (temperatureType < 1 || temperatureType > 3) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            temperatureType = scanner.nextInt();
        }
        String temperatureName = null	;

        System.out.println("[1] 2020 [2] 2021 [3] 2022");
        System.out.print("Please select the year: ");
        int selectedYearNumber = scanner.nextInt();

        // Handle incorrect year input
        while (selectedYearNumber < 0 || selectedYearNumber > 4) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            selectedYearNumber = scanner.nextInt();
        }


        int year = selectedYearNumber + 2019;
        double totalTemperature = 0;
        int count = 0;

        for (Temperature temperature : selectedCountry.getTemperatures()) {
            if (temperature.getYear() == year) {
                count++;
                switch (temperatureType) {
                    case 1:
                    	temperatureName = "Celcius";
                        totalTemperature = temperature.getCelciusMeasurement();
                        break;
                    case 2:
                    	temperatureName = "Fahrenheit";
                        totalTemperature = temperature.getFahrenheitMeasurement();
                        break;
                    case 3:
                    	temperatureName = "Kelvin";
                        totalTemperature = temperature.getKelvinMeasurement();
                        break;

                }
                break;
            }
        }
        if (count == 0) {
            System.out.println("No temperature measurements found for the specified year");
        }

        System.out.println("Average temperature of " + selectedCountry.getName() + " in " + temperatureName + " in " + year + ": " + totalTemperature +"\n");
    }
    
    public void calculateAverageTemperatureCity(Scanner scanner) {

        City selectedCity = null;
        
        boolean cityFound = false;

        while (!cityFound) {
            System.out.print("Enter the name of the city: ");
            String cityName = scanner.nextLine();
            

            for (City city : cities) {
                if (city.getName().equalsIgnoreCase(cityName)) {
                    selectedCity = city;
                    cityFound = true;
                    break;
                }
            }

            if (!cityFound) {
                System.out.println("City is invalid: " + cityName);
            }
        }
        
        System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
        System.out.print("Please select the temperature unit: ");
        int temperatureType = scanner.nextInt();

        // Handle incorrect temperature type input
        while (temperatureType < 1 || temperatureType > 4) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            temperatureType = scanner.nextInt();
        }
        String temperatureName = null	;

        System.out.println("[1] 2020 [2] 2021 [3] 2022");
        System.out.print("Please select the year: ");
        int selectedYearNumber = scanner.nextInt();

        // Handle incorrect year input
        while (selectedYearNumber < 0 || selectedYearNumber > 4) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            selectedYearNumber = scanner.nextInt();
        }


        int year = selectedYearNumber + 2019;
        double totalTemperature = 0;
        int count = 0;

        for (Temperature temperature : selectedCity.getTemperatures()) {
            if (temperature.getYear() == year) {
                count++;
                switch (temperatureType) {
                    case 1:
                    	temperatureName = "Celcius";
                        totalTemperature = temperature.getCelciusMeasurement();
                        break;
                    case 2:
                    	temperatureName = "Fahrenheit";
                        totalTemperature = temperature.getFahrenheitMeasurement();
                        break;
                    case 3:
                    	temperatureName = "Kelvin";
                        totalTemperature = temperature.getKelvinMeasurement();
                        break;

                }
                break;
            }
        }
        if (count == 0) {
            System.out.println("No temperature measurements found for the specified year");
        }

        System.out.println("Average temperature of " + selectedCity.getName() + " in " + temperatureName + " in " + year + ": " + totalTemperature +"\n");
    }
    
    public void calculateAverageWindSpeedForCity(Scanner scanner) {
    	
        City selectedCity = null;
        
        boolean cityFound = false;

        while (!cityFound) {
            System.out.print("Enter the name of the city: ");
            String cityName = scanner.nextLine();
            

            for (City city : cities) {
                if (city.getName().equalsIgnoreCase(cityName)) {
                    selectedCity = city;
                    cityFound = true;
                    break;
                }
            }

            if (!cityFound) {
                System.out.println("City is invalid: " + cityName);
            }
        }
        
    	String input;
    	Month month;

        do {
            System.out.print("Enter a month: ");
            input = scanner.nextLine().toUpperCase();

            try {
                month = Month.valueOf(input);
  
                break; 
                } 
            catch (IllegalArgumentException e) {
                System.out.println("Invalid month name");
            }
        } while (true);
        
        
        System.out.println("[1] m/s [2] km/h");
        System.out.print("Please select the temperature unit: ");
        int speedPerTimeType = scanner.nextInt();
        
        while (speedPerTimeType < 1 || speedPerTimeType > 3) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            speedPerTimeType = scanner.nextInt();
        }
        for (WindSpeed windSpeed : selectedCity.getWindSpeeds()) {
            if (windSpeed.getMonth().equals(month.toString())) {
            	switch (speedPerTimeType) {
                case 1:
                    System.out.println("Meters Per Second: " + windSpeed.getMetersPerSecond());
                    break;
                case 2:
                    System.out.println("Kilometers Per Hour: " + windSpeed.getKmPerHour());
                    break;
                default:
                    System.out.println("Unknown speed type");
            }
            }
        }
        

    }
    
    private void setMeasurementsForCountriesAndCities() {
    	for(int year = 2020; year <= 2022; year++) {
    		for(int monthIndex = 0; monthIndex < 12; monthIndex++) {
    			String month = Month.values()[monthIndex].name();
        		for(City city: cities) {
        		
        			Humidity humidity = new Humidity(year, month);
            		RadiationAbsorption radiationAbsorption = new RadiationAbsorption(year, month);
            		Temperature temperature = new Temperature(year, month);
            		WindSpeed windSpeed = new WindSpeed(year, month);
            		city.addMeasurement(humidity);
            		city.addMeasurement(radiationAbsorption);
            		city.addMeasurement(temperature);
            		city.addMeasurement(windSpeed);
        		}
        		for(Country country: countries) {
        			Temperature temp = new Temperature(year, month);
        			country.addTemperatureMeasurement(temp);
        		}
        	}
    	}
    }

}