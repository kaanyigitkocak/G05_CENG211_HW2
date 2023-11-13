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
    
    private int selectTemperatureType(Scanner scanner) {
        int temperatureType = 0;

        while (true) {
            System.out.println("[1] Celsius [2] Fahrenheit [3] Kelvin");
            System.out.print("Please select the temperature unit: ");

            if (scanner.hasNextInt()) {
                temperatureType = scanner.nextInt();

                if (temperatureType >= 1 && temperatureType <= 3) {
                    break;
                } else {
                    System.out.println("Incorrect option input! Please reenter another option input.");
                }
            } else {
                System.out.println("Incorrect input! Please enter a valid option number.");
                scanner.next(); 
            }
        }

        return temperatureType;
    }

    public void calculateAverageTemperatureCountry(Scanner scanner) {

    	Country selectedCountry = selectCountryMenu(scanner);
        
    	int temperatureType = selectTemperatureType(scanner);
    	
    	int selectedYear = selectYearMenu(scanner);
    	
        String temperatureName = null;
        
        double totalTemperature = 0;
        int count = 0;

        for (Temperature temperature : selectedCountry.getTemperatures()) {
            if (temperature.getYear() == selectedYear) {
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

        System.out.println("Average temperature of " + selectedCountry.getName() + " in " + temperatureName + " in " + selectedYear + ": " + totalTemperature +"\n");
    }
    
    private Country selectCountryMenu(Scanner scanner) {
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
        return selectedCountry;
    }
    
    public void calculateAverageTemperatureCity(Scanner scanner) {

    	City selectedCity = selectCityMenu(scanner);
        
    	int temperatureType = selectTemperatureType(scanner);
    	
    	int selectedYear = selectYearMenu(scanner);
    	
        String temperatureName = null;


        double totalTemperature = 0;
        int count = 0;

        for (Temperature temperature : selectedCity.getTemperatures()) {
            if (temperature.getYear() == selectedYear) {
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

        System.out.println("Average temperature of " + selectedCity.getName() + " in " + temperatureName + " in " + selectedYear + ": " + totalTemperature +"\n");
    }
 

    
    private City selectCityMenu(Scanner scanner) {
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
        return selectedCity;
    }
    private Month selectMonthMenu() {
    	String input;
    	Month month;
    	
    	Scanner scanner = new Scanner(System.in);
    	do {
    	    System.out.print("Enter a month: ");
    	    input = scanner.nextLine().trim().toUpperCase();

    	    // Kullanıcının boş giriş yapması durumunda tekrar sormak üzere döngüyü devam ettir
    	    if (input.length()<=0) {
    	        System.out.println("Please enter a valid month name.");
    	        continue;
    	    }

    	    try {
    	        month = Month.valueOf(input);
    	        break; 
    	    } catch (IllegalArgumentException e) {
    	        System.out.println("Invalid month name: " + input);
    	    }
    	} while (true);
    	return month;
    }
    public void calculateAverageWindSpeedForCity(Scanner scanner) {
    	
    	City selectedCity = selectCityMenu(scanner);
        
    	Month month = selectMonthMenu();
        
        System.out.println("[1] m/s [2] km/h");
        System.out.print("Please select the temperature unit: ");
        int speedPerTimeType = scanner.nextInt();
        
        while (speedPerTimeType < 1 || speedPerTimeType > 3) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            speedPerTimeType = scanner.nextInt();
        }
        double topSpeed = 0.0;
        for (WindSpeed windSpeed : selectedCity.getWindSpeeds()) {
            if (windSpeed.getMonth().equals(month.toString())) {
            	switch (speedPerTimeType) {
                case 1:
                    topSpeed += windSpeed.getMetersPerSecond();
                    break;
                case 2:
                	topSpeed += windSpeed.getKmPerHour();
                    break;
            		}	
            	}

        	}
        double averageSpeed = topSpeed/3;
        
        switch (speedPerTimeType) {
        case 1:
            System.out.println("Meters Per Second: " + averageSpeed);
            break;
        case 2:
            System.out.println("Kilometers Per Hour: " + averageSpeed);
            break;
        default:
            System.out.println("Unknown speed type");
        }
        

    }
    
    	public void calculateIntensityValueTimesForYearAndCity(Scanner scanner) {
    	
    		City selectedCity = selectCityMenu(scanner);
            System.out.println("[1] LOW [2] MEDIUM [3] HIGH");
            System.out.print("Please enter the radiation intensity value: ");

            int radiationValue = scanner.nextInt();
            RadiationIntensity intensity = null;
            
            while (radiationValue < 1 || radiationValue > 4) {
                System.out.println("Incorrect option input! Please reenter another option input: ");
                radiationValue = scanner.nextInt();
            }
            
            intensity = determineRadiationIntensity(radiationValue);

            
            System.out.println("Selected Radiation Intensity: " + intensity);


        

        
            int count = 0;
            
            for (RadiationAbsorption radiationAbsorption : selectedCity.getRadiationAbsorptions()) {
            		if(radiationAbsorption.getRadiationIntensity().toString() == intensity.toString()) 
            			count++;
            	}
            
            System.out.println("Times: " + count);
        	
        }
    
    	private static RadiationIntensity determineRadiationIntensity(int value) {
    		switch (value) {
    		case 1:
    			return RadiationIntensity.LOW;
    		case 2:
    			return RadiationIntensity.MEDIUM;
    		case 3:
    			return RadiationIntensity.HIGH;
    		default:
    			return null;
    			}
    	}
    public void calculateAverageHumidityCity(Scanner scanner) {
    	
    	City selectedCity = selectCityMenu(scanner);
        
        double topHumidity= 0.0;
        int count = 0;
        
        for (Humidity humidity : selectedCity.getHumidities()) {
        		topHumidity = humidity.getHumidityPercentage();
        		count++;
        	}
        double averageHumidity= topHumidity/count;
        System.out.println("Average humidity of city : " + averageHumidity);
        
        
    	
    }
    
    private int selectYearMenu(Scanner scanner) {
        int selectedYear = 0;

        while (true) {
            System.out.println("[1] 2020 [2] 2021 [3] 2022");
            System.out.print("Please select the year: ");

            if (scanner.hasNextInt()) {
                selectedYear = scanner.nextInt();

                if (selectedYear >= 1 && selectedYear <= 3) {
                    break;
                } else {
                    System.out.println("Incorrect option input! Please reenter another option input.");
                }
            } else {
                System.out.println("Incorrect input! Please enter a valid option number.");
                scanner.next(); 
            }
        }

        return selectedYear + 2019;
    }

    public void calculateFeltTemperatureForCity(Scanner scanner) {
    	City selectedCity = selectCityMenu(scanner);
    	
    	int selectedYear = selectYearMenu(scanner);
    	
    	Month month = selectMonthMenu();
        
        double feltTemperature = selectedCity.calculateFeltTemperature(selectedYear,month.toString());
        System.out.println("Felt Temperature : " +feltTemperature );
        
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