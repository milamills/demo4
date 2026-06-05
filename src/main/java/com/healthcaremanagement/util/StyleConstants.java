package com.healthcaremanagement.util;

/**
 * StyleConstants - Centralized styling constants
 * 
 * Contains all CSS styles, colors, and UI constants used throughout the application.
 */
public class StyleConstants {
    
    // Color Palette
    public static final String COLOR_PRIMARY_DARK = "#0f5132";
    public static final String COLOR_PRIMARY = "#198754";
    public static final String COLOR_PRIMARY_LIGHT = "#2e7d32";
    public static final String COLOR_SECONDARY = "#0d6efd";
    public static final String COLOR_WARNING = "#fd7e14";
    public static final String COLOR_DANGER = "#dc3545";
    public static final String COLOR_SUCCESS = "#198754";
    public static final String COLOR_INFO = "#6f42c1";
    
    public static final String COLOR_TEXT_DARK = "#212529";
    public static final String COLOR_TEXT_MUTED = "#6c757d";
    public static final String COLOR_TEXT_LIGHT = "#adb5bd";
    public static final String COLOR_BORDER = "#dee2e6";
    public static final String COLOR_BG_LIGHT = "#f8f9fa";
    public static final String COLOR_BG_LIGHTER = "#f4f7f6";
    public static final String COLOR_BG_WHITE = "#ffffff";
    public static final String COLOR_SUCCESS_LIGHT = "#e8f5e9";
    
    // Typography
    public static final String FONT_FAMILY = "Segoe UI";
    public static final int FONT_SIZE_LARGE = 26;
    public static final int FONT_SIZE_TITLE = 18;
    public static final int FONT_SIZE_NORMAL = 14;
    public static final int FONT_SIZE_SMALL = 13;
    public static final int FONT_SIZE_XSMALL = 11;
    
    // Application Constants
    public static final String APP_TITLE = "Healthcare Management System";
    public static final String APP_SUBTITLE = "Secure Access Portal";
    
    // Spacing
    public static final int PADDING_LARGE = 40;
    public static final int PADDING_MEDIUM = 25;
    public static final int PADDING_SMALL = 15;
    public static final int PADDING_XSMALL = 6;
    
    // Border Radius
    public static final int RADIUS_LARGE = 20;
    public static final int RADIUS_MEDIUM = 10;
    public static final int RADIUS_SMALL = 8;
    
    // Button Styles
    public static final String BUTTON_PRIMARY_STYLE =
            "-fx-background-color: " + COLOR_PRIMARY + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: " + RADIUS_MEDIUM + ";" +
            "-fx-cursor: hand;";
    
    public static final String BUTTON_LOGOUT_STYLE =
            "-fx-background-color: " + COLOR_DANGER + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: " + RADIUS_SMALL + ";" +
            "-fx-cursor: hand;";
    
    // Panel Styles
    public static final String PANEL_BACKGROUND_STYLE =
            "-fx-background-color: " + COLOR_BG_LIGHT + ";";
    
    public static final String NAV_BAR_STYLE =
            "-fx-background-color: " + COLOR_PRIMARY_DARK + ";";
}
