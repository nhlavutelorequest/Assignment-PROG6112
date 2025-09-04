/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesapp;


import java.util.ArrayList;
import java.util.Scanner;

// SeriesModel class to store series information
class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
    
    public SeriesModel(String seriesId, String seriesName, String seriesAge, String seriesNumberOfEpisodes) {
        this.SeriesId = seriesId;
        this.SeriesName = seriesName;
        this.SeriesAge = seriesAge;
        this.SeriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }
}

// Main Series class containing all working methods
class Series {
    private ArrayList<SeriesModel> seriesList;
    private Scanner scanner;
    
    public Series() {
        seriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Method to display the main menu
    public void displayMenu() {
        System.out.println("LATEST SERIES - 2025");
        System.out.println("*");
        System.out.println("Enter (1) to launch menu or any other key to exit");
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
    }
    
    // Method to capture a new series
    public void CaptureSeries() {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("*");
        
        System.out.print("Enter the series id: ");
        String seriesId = scanner.nextLine();
        
        System.out.print("Enter the series name: ");
        String seriesName = scanner.nextLine();
        
        String seriesAge;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            seriesAge = scanner.nextLine();
            
            // Validate age restriction (must be numeric and between 2-18)
            try {
                int age = Integer.parseInt(seriesAge);
                if (age >= 2 && age <= 18) {
                    break;
                } else {
                    System.out.println("You have entered a incorrect series age!!!");
                    System.out.print("Please re-enter the series age >> ");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered a incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
        }
        
        System.out.print("Enter the number of episodes for " + seriesName + ": ");
        String numberOfEpisodes = scanner.nextLine();
        
        // Create and add new series to the list
        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, seriesAge, numberOfEpisodes);
        seriesList.add(newSeries);
        
        System.out.println("Series processed successfully!!!");
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    
    // Method to search for a series
    public void SearchSeries() {
        System.out.print("Enter the series id to search: ");
        String searchId = scanner.nextLine();
        System.out.println("------------------------------------");
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(searchId)) {
                System.out.println("SERIES ID: " + series.SeriesId);
                System.out.println("SERIES NAME: " + series.SeriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
                System.out.println("SERIES NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
                System.out.println("------------------------------------");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series Id: " + searchId + " was not found!");
            System.out.println("------------------------------------");
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    
    // Method to update a series
    public void UpdateSeries() {
        System.out.print("Enter the series id to update: ");
        String updateId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(updateId)) {
                System.out.print("Enter the series name: ");
                series.SeriesName = scanner.nextLine();
                
                String newAge;
                while (true) {
                    System.out.print("Enter the age restriction: ");
                    newAge = scanner.nextLine();
                    
                    try {
                        int age = Integer.parseInt(newAge);
                        if (age >= 2 && age <= 18) {
                            series.SeriesAge = newAge;
                            break;
                        } else {
                            System.out.println("You have entered a incorrect series age!!!");
                            System.out.print("Please re-enter the series age >> ");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You have entered a incorrect series age!!!");
                        System.out.print("Please re-enter the series age >> ");
                        continue;
                    }
                }
                
                System.out.print("Enter the number of episodes: ");
                series.SeriesNumberOfEpisodes = scanner.nextLine();
                
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series Id: " + updateId + " was not found!");
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    
    // Method to delete a series
    public void DeleteSeries() {
        System.out.print("Enter the series id to delete: ");
        String deleteId = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).SeriesId.equals(deleteId)) {
                System.out.print("Are you sure you want to delete series " + deleteId + " from the system? Yes (y) to delete, ");
                System.out.print("? ");
                String confirmation = scanner.nextLine();
                
                if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
                    seriesList.remove(i);
                    System.out.println("------------------------------------");
                    System.out.println("Series with Series Id: " + deleteId + " was deleted!");
                    System.out.println("------------------------------------");
                } else {
                    System.out.println("Deletion cancelled.");
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series Id: " + deleteId + " was not found!");
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    
    // Method to generate and display series report
    public void SeriesReport() {
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application.");
        System.out.println("5");
        System.out.println();
        
        if (seriesList.isEmpty()) {
            System.out.println("No series data available.");
        } else {
            for (int i = 0; i < seriesList.size(); i++) {
                SeriesModel series = seriesList.get(i);
                System.out.println("Series " + (i + 1));
                System.out.println("------------------------------------");
                System.out.println("SERIES ID: " + series.SeriesId);
                System.out.println("SERIES NAME: " + series.SeriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
                System.out.println("NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
                System.out.println("------------------------------------");
            }
        }
        
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    
    // Method to exit the application
    public void ExitSeriesApplication() {
        System.out.println("Thank you for using the TV Series Management Application!");
        System.exit(0);
    }
    
    // Main application loop
    public void runApplication() {
        while (true) {
            displayMenu();
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    CaptureSeries();
                    break;
                case "2":
                    SearchSeries();
                    break;
                case "3":
                    UpdateSeries();
                    break;
                case "4":
                    DeleteSeries();
                    break;
                case "5":
                    SeriesReport();
                    break;
                case "6":
                    ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }
}

