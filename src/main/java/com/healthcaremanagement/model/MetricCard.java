package com.healthcaremanagement.model;

/**
 * MetricCard - Data model for dashboard metric cards
 * 
 * Represents a single metric card displayed on the dashboard.
 */
public class MetricCard {
    private String headline;
    private String counter;
    private String borderColor;
    
    /**
     * Constructor for MetricCard
     * 
     * @param headline The title/label of the metric
     * @param counter The value/number to display
     * @param borderColor Hex color for the card border
     */
    public MetricCard(String headline, String counter, String borderColor) {
        this.headline = headline;
        this.counter = counter;
        this.borderColor = borderColor;
    }
    
    // Getters and Setters
    public String getHeadline() {
        return headline;
    }
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    public String getCounter() {
        return counter;
    }
    
    public void setCounter(String counter) {
        this.counter = counter;
    }
    
    public String getBorderColor() {
        return borderColor;
    }
    
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
    
    @Override
    public String toString() {
        return "MetricCard{" +
                "headline='" + headline + '\'' +
                ", counter='" + counter + '\'' +
                ", borderColor='" + borderColor + '\'' +
                '}';
    }
}
