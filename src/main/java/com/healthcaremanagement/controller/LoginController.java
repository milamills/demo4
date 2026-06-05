package com.healthcaremanagement.controller;

import javafx.stage.Stage;
import com.healthcaremanagement.view.DashboardView;

/**
 * LoginController - Authentication Handler
 * 
 * Handles user login validation and transitions to the dashboard on success.
 * Supports three roles: Admin, Doctor, and Patient.
 */
public class LoginController {
    
    /**
     * Authenticates user credentials and transitions to dashboard if valid.
     * 
     * Test credentials:
     * - Admin: username="admin", password="admin123"
     * - Doctor: username="doctor", password="doc123"
     * - Patient: username="patient", password="patient123"
     * 
     * @param username User's username
     * @param password User's password
     * @param role User's role (Admin, Doctor, Patient)
     * @param stage Primary stage for scene transition
     * @return true if authentication successful, false otherwise
     */
    public boolean login(String username, String password, String role, Stage stage) {
        String currentRole = role.toLowerCase();
        boolean authenticated = false;

        // Validate credentials based on role
        if ("admin".equals(currentRole) && "admin".equals(username) && "admin123".equals(password)) {
            authenticated = true;
        } else if ("doctor".equals(currentRole) && "doctor".equals(username) && "doc123".equals(password)) {
            authenticated = true;
        } else if ("patient".equals(currentRole) && "patient".equals(username) && "patient123".equals(password)) {
            authenticated = true;
        }

        if (authenticated) {
            DashboardView dashboard = new DashboardView();
            dashboard.show(stage, username, role);
            return true;
        }

        return false;
    }
}
