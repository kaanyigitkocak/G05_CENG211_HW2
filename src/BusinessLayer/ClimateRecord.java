package BusinessLayer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
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
        
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedTotalTemperature = decimalFormat.format(totalTemperature);

        System.out.println("Average temperature of " + selectedCountry.getName() + " in " + temperatureName + " in " + selectedYear + ": " + formattedTotalTemperature +"\n");
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
        
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedTotalTemperature = decimalFormat.format(totalTemperature);
        
        System.out.println("Average temperature of " + selectedCity.getName() + " in " + temperatureName + " in " + selectedYear + ": " + formattedTotalTemperature +"\n");
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
            input = scanner.nextLine().trim();

            if (input.length() <= 0) {
                System.out.println("Please enter a valid month name.");
                continue;
            }

            // Türkçe karakterleri büyük harfe dönüştürmek için Locale kullanımı
            input = input.toUpperCase(new Locale("tr-TR"));

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
            if (windSpeed.getMonth().equals(month.toString()) ) {
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
        
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedAverageSpeed = decimalFormat.format(averageSpeed);
        
        switch (speedPerTimeType) {
        case 1:
            System.out.println("Meters Per Second " + formattedAverageSpeed + " for "+ selectedCity.getName()+" in "+ month.toString().toLowerCase());
            break;
        case 2:
            System.out.println("Kilometers Per Hour: " + formattedAverageSpeed+ " for "+ selectedCity.getName()+" in "+ month.toString().toLowerCase());
            break;
        default:
            System.out.println("Unknown speed type");
        }
    }
    
    private int selectRadiationIntensity(Scanner scanner) {
    	System.out.println("[1] LOW [2] MEDIUM [3] HIGH");
        System.out.print("Please enter the radiation intensity value: ");

        int radiationValue = scanner.nextInt();

        while (radiationValue < 1 || radiationValue > 4) {
            System.out.println("Incorrect option input! Please reenter another option input: ");
            radiationValue = scanner.nextInt();
        }
        return radiationValue;
    }
    
    public void calculateIntensityValueTimesForYearAndCity(Scanner scanner) {
    	City selectedCity = selectCityMenu(scanner);
        int selectedYear = selectYearMenu(scanner);
        RadiationIntensity intensity = null;   
        intensity = determineRadiationIntensity(selectRadiationIntensity(scanner));
        int count = 0;
            
        for (RadiationAbsorption radiationAbsorption : selectedCity.getRadiationAbsorptions()) {
        	if(radiationAbsorption.getRadiationIntensity().toString() == intensity.toString() && radiationAbsorption.getYear() == selectedYear) {
        		count++;
            }
            System.out.println("Selected radiation intensity " + count + " times for "+ intensity.toString().toLowerCase() +" in "+ selectedCity.getName());
        }
	}
    
    private RadiationIntensity determineRadiationIntensity(int value) {
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
        		topHumidity += humidity.getHumidityPercentage();
        		count++;
        }
        double averageHumidity= topHumidity/count;
        
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedAverageHumidity = decimalFormat.format(averageHumidity);
        
        System.out.println("Average humidity of city : " + formattedAverageHumidity + " for "+ selectedCity.getName());
        
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
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedAFeltTemperature = decimalFormat.format(feltTemperature);
        System.out.println("Felt Temperature : " +formattedAFeltTemperature+ " for "+ selectedCity.getName()+" in "+ month.toString().toLowerCase() );
        
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
    
    public void showMenu() {
    	Scanner scanner = new Scanner(System.in);
        int option;
        
        while (true) {
            System.out.println("\n****************************************************");
            System.out.println("[1] Calculate average temperature for a country according to temperature unit and year.");
            System.out.println("[2] Calculate average temperature for a city according to temperature unit and year.");
            System.out.println("[3] Calculate average wind speed for a city according to speed unit and year.");
            System.out.println("[4] Calculate average humidity of a city for every year.");
            System.out.println("[5] Count how many times a year a specific radiation intensity value appears for a city.");
            System.out.println("[6] Calculate the “felt temperature” value of a city for a specific month and year.");
            System.out.println("[7] Exit the application.");
            System.out.print("Please select an option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); 
                
	            switch (option) {
	                case 1:
	                    calculateAverageTemperatureCountry(scanner);
	                    break;
	                case 2:
	                	calculateAverageTemperatureCity(scanner);
	                    break;
	                case 3:
	                	calculateAverageWindSpeedForCity(scanner);
	                    break;
	                case 4:
	                	calculateAverageHumidityCity(scanner);
	                    break;
	                case 5:
	                	calculateIntensityValueTimesForYearAndCity(scanner);
	                    break;
	                case 6:
	                	calculateFeltTemperatureForCity(scanner);
	                    break;
	                case 7:
	                    System.out.println("==> Closing the application...");
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Incorrect option input! Please reenter another option input.");
	            }
            } else {
                System.out.println("Invalid input. Please enter a valid option number.");
                scanner.nextLine(); 
            }
        }
    }
}