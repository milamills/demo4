package com.healthcaremanagement.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.healthcaremanagement.component.DashboardHeaderComponent;
import com.healthcaremanagement.component.MetricCardComponent;
import com.healthcaremanagement.model.MetricCard;
import com.healthcaremanagement.util.DashboardMetricsProvider;
import com.healthcaremanagement.util.StyleConstants;
import java.util.List;

/**
 * DashboardView - User Dashboard Interface
 * 
 * Displays the main dashboard after successful authentication.
 * Shows role-specific metrics and provides logout functionality.
 * 
 * Refactored to use separate components and utility classes for better maintainability.
 */
public class DashboardView {
    
    private static final int WINDOW_WIDTH = 750;
    private static final int WINDOW_HEIGHT = 550;
    private static final int METRICS_GAP = 20;
    private static final int WORKSPACE_PADDING = 40;
    private static final int WORKSPACE_SPACING = 25;

    /**
     * Displays the dashboard view
     * 
     * @param stage Primary stage for scene display
     * @param user Username of logged-in user
     * @param activeRole Role of the logged-in user
     */
    public void show(Stage stage, String user, String activeRole) {
        BorderPane layout = new BorderPane();
        layout.setStyle(StyleConstants.PANEL_BACKGROUND_STYLE);

        // Set header
        layout.setTop(DashboardHeaderComponent.createHeader(activeRole));

        // Set center workspace
        layout.setCenter(createWorkspace(user, activeRole, stage));

        Scene currentScene = new Scene(layout, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setTitle(StyleConstants.APP_TITLE + " — WorkStation Panel");
        stage.setScene(currentScene);
        stage.setResizable(true);
        stage.centerOnScreen();
    }

    /**
     * Creates the main workspace area
     * 
     * @param user Username to display in welcome message
     * @param activeRole User's role
     * @param stage Stage for logout action
     * @return VBox containing the workspace
     */
    private VBox createWorkspace(String user, String activeRole, Stage stage) {
        VBox workspace = new VBox(WORKSPACE_SPACING);
        workspace.setPadding(new Insets(WORKSPACE_PADDING));
        workspace.setAlignment(Pos.TOP_LEFT);

        // Welcome message
        Text welcomeMsg = new Text("Welcome Back, " + user + "!");
        welcomeMsg.setFont(Font.font(StyleConstants.FONT_FAMILY, FontWeight.BOLD, StyleConstants.FONT_SIZE_LARGE));
        welcomeMsg.setFill(Color.valueOf(StyleConstants.COLOR_TEXT_DARK));

        // Description
        Text description = new Text("Here is an overview of your management dashboard tools and profile metrics.");
        description.setFont(Font.font(StyleConstants.FONT_FAMILY, StyleConstants.FONT_SIZE_NORMAL));
        description.setFill(Color.valueOf(StyleConstants.COLOR_TEXT_MUTED));

        // Metrics grid
        GridPane metricsGrid = createMetricsGrid(activeRole);

        // Logout button
        Button logoutBtn = createLogoutButton(stage);

        workspace.getChildren().addAll(welcomeMsg, description, metricsGrid, logoutBtn);
        return workspace;
    }

    /**
     * Creates metrics grid with role-specific cards
     * 
     * @param activeRole User's role
     * @return GridPane containing metric cards
     */
    private GridPane createMetricsGrid(String activeRole) {
        GridPane metricsGrid = new GridPane();
        metricsGrid.setHgap(METRICS_GAP);
        metricsGrid.setVgap(METRICS_GAP);

        // Get metrics for the user's role
        List<MetricCard> metrics = DashboardMetricsProvider.getMetricsForRole(activeRole);
        
        // Add metrics to grid (2 columns)
        int column = 0;
        for (MetricCard metric : metrics) {
            metricsGrid.add(MetricCardComponent.createMetricCard(metric), column, 0);
            column++;
        }

        return metricsGrid;
    }

    /**
     * Creates the logout button
     * 
     * @param stage Stage for logout action
     * @return Button configured for logout
     */
    private Button createLogoutButton(Stage stage) {
        Button logoutBtn = new Button("Secure Log Out");
        logoutBtn.setPrefSize(160, 40);
        logoutBtn.setStyle(StyleConstants.BUTTON_LOGOUT_STYLE);
        logoutBtn.setOnAction(e -> {
            LoginView loginView = new LoginView();
            loginView.show(stage);
        });
        return logoutBtn;
    }
}
