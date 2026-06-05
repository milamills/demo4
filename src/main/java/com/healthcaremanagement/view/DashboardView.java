package com.healthcaremanagement.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * DashboardView - User Dashboard Interface
 * 
 * Displays the main dashboard after successful authentication.
 * Shows role-specific metrics and provides logout functionality.
 */
public class DashboardView {

    private static final String APP_TITLE = "Healthcare Management System";

    public void show(Stage stage, String user, String activeRole) {
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #f8f9fa;");

        // Top Navigation Banner
        HBox topNav = new HBox();
        topNav.setPadding(new Insets(15, 30, 15, 30));
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setStyle("-fx-background-color: #0f5132;");

        Text titleText = new Text(APP_TITLE);
        titleText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        titleText.setFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label sessionLabel = new Label("Active Session: " + activeRole);
        sessionLabel.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 13));
        sessionLabel.setTextFill(Color.valueOf("#e8f5e9"));
        sessionLabel.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-padding: 6 12; -fx-background-radius: 6;");

        topNav.getChildren().addAll(titleText, spacer, sessionLabel);
        layout.setTop(topNav);

        // Center Workspace
        VBox workspace = new VBox(25);
        workspace.setPadding(new Insets(40));
        workspace.setAlignment(Pos.TOP_LEFT);

        Text welcomeMsg = new Text("Welcome Back, " + user + "!");
        welcomeMsg.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        welcomeMsg.setFill(Color.valueOf("#212529"));

        Text description = new Text("Here is an overview of your management dashboard tools and profile metrics.");
        description.setFont(Font.font("Segoe UI", 14));
        description.setFill(Color.valueOf("#6c757d"));

        // Metrics Grid
        GridPane metricsGrid = new GridPane();
        metricsGrid.setHgap(20);
        metricsGrid.setVgap(20);

        if ("Admin".equalsIgnoreCase(activeRole)) {
            metricsGrid.add(createMetricCard("System Status", "Operational", "#198754"), 0, 0);
            metricsGrid.add(createMetricCard("Active Users", "14 Staff Online", "#0d6efd"), 1, 0);
        } else if ("Doctor".equalsIgnoreCase(activeRole)) {
            metricsGrid.add(createMetricCard("Today's Appointments", "6 Remaining", "#fd7e14"), 0, 0);
            metricsGrid.add(createMetricCard("Pending Lab Reports", "3 Files", "#dc3545"), 1, 0);
        } else { // Patient
            metricsGrid.add(createMetricCard("Medical Record", "Up to date", "#198754"), 0, 0);
            metricsGrid.add(createMetricCard("Next Checkup", "Tomorrow, 9 AM", "#6f42c1"), 1, 0);
        }

        // Logout Button
        Button logoutBtn = new Button("Secure Log Out");
        logoutBtn.setPrefSize(160, 40);
        logoutBtn.setStyle(
                "-fx-background-color: #dc3545;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );
        logoutBtn.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.show(stage);
        });

        workspace.getChildren().addAll(welcomeMsg, description, metricsGrid, logoutBtn);
        layout.setCenter(workspace);

        Scene currentScene = new Scene(layout, 750, 550);
        stage.setTitle(APP_TITLE + " — WorkStation Panel");
        stage.setScene(currentScene);
        stage.setResizable(true);
        stage.centerOnScreen();
    }

    private VBox createMetricCard(String headline, String counter, String hexBorderColor) {
        VBox metricBox = new VBox(8);
        metricBox.setPrefSize(240, 100);
        metricBox.setPadding(new Insets(16));
        metricBox.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: " + hexBorderColor + ";" +
                        "-fx-border-width: 0 0 0 5;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.03), 10, 0, 0, 4);"
        );

        Label head = new Label(headline.toUpperCase());
        head.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
        head.setTextFill(Color.valueOf("#6c757d"));

        Label count = new Label(counter);
        count.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        count.setTextFill(Color.valueOf("#212529"));

        metricBox.getChildren().addAll(head, count);
        return metricBox;
    }
}
