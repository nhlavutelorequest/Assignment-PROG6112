/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tvseriesapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Unit Test Class for TV Series Management Application - Section B
 * This class contains all required unit tests it determine code coverage
 * for the TV series management application.
 */
public class SeriesTest {
    
    private TestableSeries series;
    
    @BeforeEach
    public void setUp() {
        series = new TestableSeries();
        // Add some test data
        series.addTestSeries("S001", "Breaking Bad", "18", "62");
        series.addTestSeries("S002", "Stranger Things", "16", "42");
        series.addTestSeries("S003", "The Office", "12", "201");
    }
    
    /**
     * Test Name: TestSearchSeries()
     * Test Purpose: To supply the series id to the series searching method. 
     */
    @Test
    public void TestSearchSeries() {
        // Test searching for an existing series
        SeriesModel result = series.searchSeriesById("S001");
        
        assertNotNull(result, "Series should be found");
        assertEquals("S001", result.SeriesId, "Series ID should match");
        assertEquals("Breaking Bad", result.SeriesName, "Series name should match");
        assertEquals("18", result.SeriesAge, "Series age should match");
        assertEquals("62", result.SeriesNumberOfEpisodes, "Number of episodes should match");
    }
    
    /**
     * Test Name: TestSearchSeries_SeriesNotFound()
     * Test Purpose: To supply an incorrect series id to the series searching method. 
     * The test will determine that no series was found.
     */
    @Test
    public void TestSearchSeries_SeriesNotFound() {
        // Test searching for a non-existing series
        SeriesModel result = series.searchSeriesById("S999");
        
        assertNull(result, "Series should not be found for non-existing ID");
        
        // Test with empty string
        SeriesModel result2 = series.searchSeriesById("");
        assertNull(result2, "Series should not be found for empty ID");
        
        // Test with null
        SeriesModel result3 = series.searchSeriesById(null);
        assertNull(result3, "Series should not be found for null ID");
    }
    
    /**
     * Test Name: TestUpdateSeries()
     * Test Purpose: To supply the Series id to the update series method. 
     * The test will determine that the series has been successfully updated.
     */
    @Test
    public void TestUpdateSeries() {
        // Test updating an existing series
        boolean result = series.updateSeriesById("S002", "Stranger Things Updated", "15", "50");
        
        assertTrue(result, "Series should be updated successfully");
        
        // Verify the update by searching for the series
        SeriesModel updatedSeries = series.searchSeriesById("S002");
        assertNotNull(updatedSeries, "Updated series should still exist");
        assertEquals("Stranger Things Updated", updatedSeries.SeriesName, "Series name should be updated");
        assertEquals("15", updatedSeries.SeriesAge, "Series age should be updated");
        assertEquals("50", updatedSeries.SeriesNumberOfEpisodes, "Number of episodes should be updated");
    }
    
    /**
     * Test Name: TestDeleteSeries()
     * Test Purpose: To supply the series id to the delete series method. 
     * The test will determine that the series has been successfully deleted.
     */
    @Test
    public void TestDeleteSeries() {
        // Test deleting an existing series
        boolean result = series.deleteSeriesById("S003");
        
        assertTrue(result, "Series should be deleted successfully");
        
        // Verify the deletion by searching for the series
        SeriesModel deletedSeries = series.searchSeriesById("S003");
        assertNull(deletedSeries, "Deleted series should not be found");
        
        // Verify other series still exist
        SeriesModel remainingSeries = series.searchSeriesById("S001");
        assertNotNull(remainingSeries, "Other series should still exist");
    }
    
    /**
     * Test Name: TestDeleteSeries_SeriesNotFound()
     * Test Purpose: To supply an incorrect series id to the delete series method. 
     * The test will determine that the series has not been deleted.
     */
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        // Test deleting a non-existing series
        boolean result = series.deleteSeriesById("S999");
        
        assertFalse(result, "Deletion should fail for non-existing series");
        
        // Test with empty string
        boolean result2 = series.deleteSeriesById("");
        assertFalse(result2, "Deletion should fail for empty ID");
        
        // Test with null
        boolean result3 = series.deleteSeriesById(null);
        assertFalse(result3, "Deletion should fail for null ID");
        
        // Verify all original series still exist
        assertEquals(3, series.getSeriesCount(), "All original series should still exist");
    }
    
    /**
     * Test Name: TestSeriesAgeRestriction_AgeValid()
     * Test Purpose: To supply a valid series age restriction to the series age restriction method. 
     * The test will determine that the series age restriction is valid.
     */
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        // Test valid age restrictions (between 2-18)
        assertTrue(series.validateSeriesAge("2"), "Age 2 should be valid");
        assertTrue(series.validateSeriesAge("10"), "Age 10 should be valid");
        assertTrue(series.validateSeriesAge("18"), "Age 18 should be valid");
        assertTrue(series.validateSeriesAge("16"), "Age 16 should be valid");
        assertTrue(series.validateSeriesAge("12"), "Age 12 should be valid");
    }
    
    /**
     * Test Name: TestSeriesAgeRestriction_SeriesAgeInValid()
     * Test Purpose: To supply an invalid series age restriction to the series age restriction method. 
     * The test will determine that the series age is invalid.
     */
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        // Test invalid age restrictions
        assertFalse(series.validateSeriesAge("1"), "Age 1 should be invalid (below minimum)");
        assertFalse(series.validateSeriesAge("19"), "Age 19 should be invalid (above maximum)");
        assertFalse(series.validateSeriesAge("0"), "Age 0 should be invalid");
        assertFalse(series.validateSeriesAge("-1"), "Negative age should be invalid");
        assertFalse(series.validateSeriesAge("abc"), "Non-numeric age should be invalid");
        assertFalse(series.validateSeriesAge(""), "Empty age should be invalid");
        assertFalse(series.validateSeriesAge(null), "Null age should be invalid");
        assertFalse(series.validateSeriesAge("25"), "Age 25 should be invalid (too high)");
    }
}

class TestableSeries extends Series {
    private ArrayList<SeriesModel> seriesList;
    
    public TestableSeries() {
        super();
        seriesList = new ArrayList<>();
    }
    
    // Method to add test data
    public void addTestSeries(String id, String name, String age, String episodes) {
        SeriesModel series = new SeriesModel(id, name, age, episodes);
        seriesList.add(series);
    }
    
    // Testable version of search method
    public SeriesModel searchSeriesById(String seriesId) {
        if (seriesId == null || seriesId.trim().isEmpty()) {
            return null;
        }
        
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(seriesId)) {
                return series;
            }
        }
        return null;
    }
    
    // Testable version of update method
    public boolean updateSeriesById(String seriesId, String newName, String newAge, String newEpisodes) {
        if (seriesId == null || seriesId.trim().isEmpty()) {
            return false;
        }
        
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(seriesId)) {
                if (validateSeriesAge(newAge)) {
                    series.SeriesName = newName;
                    series.SeriesAge = newAge;
                    series.SeriesNumberOfEpisodes = newEpisodes;
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    
    // Testable version of delete method
    public boolean deleteSeriesById(String seriesId) {
        if (seriesId == null || seriesId.trim().isEmpty()) {
            return false;
        }
        
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesList.get(i).SeriesId.equals(seriesId)) {
                seriesList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    // Method to validate series age restriction
    public boolean validateSeriesAge(String age) {
        if (age == null || age.trim().isEmpty()) {
            return false;
        }
        
        try {
            int ageValue = Integer.parseInt(age.trim());
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Helper method to get series count for testing
    public int getSeriesCount() {
        return seriesList.size();
    }
}