package com.healthcaremanagement;

import javafx.application.Application;
import javafx.stage.Stage;
import com.healthcaremanagement.view.LoginView;

/**
 * HealthCareManagementSystem - Main Application Entry Point
 * 
 * This is the primary launcher for the Healthcare Management System application.
 * It initializes the JavaFX application and displays the login view.
 */
public class HealthCareManagementSystem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        loginView.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
