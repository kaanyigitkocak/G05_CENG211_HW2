package PresentationLayer;

import java.util.Scanner;

import BusinessLayer.City;
import BusinessLayer.ClimateRecord;
import BusinessLayer.Country;
import BusinessLayer.Month;

public class ClimateApp {

	public static void main(String[] args) {
		
		ClimateRecord climateRecord = new ClimateRecord();
		Scanner scanner = new Scanner(System.in);
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

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    climateRecord.calculateAverageTemperatureCountry(scanner);
                    break;
                case 2:
                	climateRecord.calculateAverageTemperatureCity(scanner);
                    break;
                case 3:
                	climateRecord.calculateAverageWindSpeedForCity(scanner);
                    break;
                case 4:
                	climateRecord.calculateAverageHumidityCity(scanner);
                    break;
                case 5:
                	climateRecord.calculateIntensityValueTimesForYearAndCity(scanner);
                    break;
                case 6:
                	System.out.println("6");
                    break;
                case 7:
                    System.out.println("==> Closing the application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect option input! Please reenter another option input.");
            }
        }
	}

}
