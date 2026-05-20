package com.health.demo4; // Change this to match your project's package structure if needed

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// ==========================================
// 1. MAIN APPLICATION LAUNCHER
// ==========================================
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView();
        loginView.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// ==========================================
// 2. REDESIGNED LOGIN VIEW
// ==========================================
class LoginView {

    private StackPane buildModernLogo(double size) {
        StackPane logoContainer = new StackPane();
        logoContainer.setPrefSize(size, size);
        logoContainer.setMaxSize(size, size);

        Circle outerRing = new Circle(size / 2, size / 2, size / 2);
        outerRing.setFill(Color.TRANSPARENT);
        outerRing.setStroke(Color.valueOf("#0f5132"));
        outerRing.setStrokeWidth(3);

        Circle innerDot = new Circle(size / 2, size / 2, size * 0.25);
        innerDot.setFill(Color.valueOf("#198754"));

        logoContainer.getChildren().addAll(outerRing, innerDot);
        return logoContainer;
    }

    public void show(Stage stage) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f4f7f6;");

        VBox card = new VBox(25);
        card.setMaxWidth(400);
        card.setPadding(new Insets(40));
        card.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(15,81,50,0.08), 30, 0, 0, 10);"
        );

        VBox header = new VBox(12);
        header.setAlignment(Pos.CENTER);

        StackPane logo = buildModernLogo(56);

        Text mainTitle = new Text("Fonkeng Health Hub");
        mainTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        mainTitle.setFill(Color.valueOf("#0f5132"));

        Text subtitle = new Text("Secure Access Portal");
        subtitle.setFont(Font.font("Segoe UI", 13));
        subtitle.setFill(Color.valueOf("#6c757d"));

        header.getChildren().addAll(logo, mainTitle, subtitle);

        VBox form = new VBox(16);
        form.setAlignment(Pos.CENTER_LEFT);

        String defaultInputStyle =
                "-fx-background-color: #f8f9fa;" +
                        "-fx-border-color: #dee2e6;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 14;" +
                        "-fx-font-size: 14;";

        String focusedInputStyle =
                "-fx-background-color: #ffffff;" +
                        "-fx-border-color: #198754;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 14;" +
                        "-fx-font-size: 14;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(25,135,84,0.15), 8, 0, 0, 0);";

        // Username Field
        VBox userBox = new VBox(6);
        Label userLabel = new Label("Username / Email");
        userLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        userLabel.setTextFill(Color.valueOf("#495057"));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setStyle(defaultInputStyle);
        usernameField.focusedProperty().addListener((obs, old, focus) ->
                usernameField.setStyle(focus ? focusedInputStyle : defaultInputStyle));
        userBox.getChildren().addAll(userLabel, usernameField);

        // Password Field
        VBox passBox = new VBox(6);
        Label passLabel = new Label("Password");
        passLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        passLabel.setTextFill(Color.valueOf("#495057"));

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("••••••••••••");
        passwordField.setStyle(defaultInputStyle);
        passwordField.focusedProperty().addListener((obs, old, focus) ->
                passwordField.setStyle(focus ? focusedInputStyle : defaultInputStyle));
        passBox.getChildren().addAll(passLabel, passwordField);

        // Account Type Row
        VBox roleBox = new VBox(8);
        Label roleLabel = new Label("Account Type");
        roleLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 12));
        roleLabel.setTextFill(Color.valueOf("#495057"));

        ToggleGroup roleGroup = new ToggleGroup();
        ToggleButton adminBtn = makeModernToggle("Admin", roleGroup);
        ToggleButton doctorBtn = makeModernToggle("Doctor", roleGroup);
        ToggleButton patientBtn = makeModernToggle("Patient", roleGroup);
        adminBtn.setSelected(true);
        styleModernToggle(adminBtn, true);

        roleGroup.selectedToggleProperty().addListener((obs, old, nw) -> {
            for (Toggle t : roleGroup.getToggles()) {
                styleModernToggle((ToggleButton) t, t.isSelected());
            }
        });

        HBox roleRow = new HBox(8, adminBtn, doctorBtn, patientBtn);
        roleRow.setAlignment(Pos.CENTER);
        HBox.setHgrow(adminBtn, Priority.ALWAYS);
        HBox.setHgrow(doctorBtn, Priority.ALWAYS);
        HBox.setHgrow(patientBtn, Priority.ALWAYS);
        adminBtn.setMaxWidth(Double.MAX_VALUE);
        doctorBtn.setMaxWidth(Double.MAX_VALUE);
        patientBtn.setMaxWidth(Double.MAX_VALUE);
        roleBox.getChildren().addAll(roleLabel, roleRow);

        // Error Label
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.valueOf("#dc3545"));
        errorLabel.setFont(Font.font("Segoe UI", 12));
        errorLabel.setMinHeight(20);

        // Submit Button
        Button loginButton = new Button("Access Dashboard");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setPrefHeight(46);
        loginButton.setStyle(
                "-fx-background-color: #198754;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        );
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
                "-fx-background-color: #0f5132;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        ));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
                "-fx-background-color: #198754;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;"
        ));

        loginButton.setOnAction(e -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            Toggle selected = roleGroup.getSelectedToggle();

            if (username.isEmpty() || password.isEmpty() || selected == null) {
                errorLabel.setTextFill(Color.valueOf("#dc3545"));
                errorLabel.setText("⚠️ Please fill in all credentials.");
                return;
            }

            String role = ((ToggleButton) selected).getText();
            LoginController controller = new LoginController();
            boolean success = controller.login(username, password, role, stage);

            if (!success) {
                errorLabel.setTextFill(Color.valueOf("#dc3545"));
                errorLabel.setText("⚠️ Authentication failed. Verify details.");
            }
        });

        form.getChildren().addAll(userBox, passBox, roleBox, errorLabel, loginButton);

        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        Label footerNote = new Label("End-to-End Encrypted Session");
        footerNote.setFont(Font.font("Segoe UI", 11));
        footerNote.setTextFill(Color.valueOf("#adb5bd"));
        footer.getChildren().add(footerNote);

        card.getChildren().addAll(header, form, footer);
        root.getChildren().add(card);

        Scene scene = new Scene(root, 520, 620);
        stage.setTitle("Fonkeng Health Hub — Authentication");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private ToggleButton makeModernToggle(String text, ToggleGroup group) {
        ToggleButton btn = new ToggleButton(text);
        btn.setToggleGroup(group);
        btn.setPrefHeight(38);
        btn.setFont(Font.font("Segoe UI", FontWeight.MEDIUM, 13));
        return btn;
    }

    private void styleModernToggle(ToggleButton btn, boolean isSelected) {
        if (isSelected) {
            btn.setStyle(
                    "-fx-background-color: #e8f5e9;" +
                            "-fx-text-fill: #0f5132;" +
                            "-fx-border-color: #2e7d32;" +
                            "-fx-border-radius: 8;" +
                            "-fx-background-radius: 8;" +
                            "-fx-cursor: hand;"
            );
        } else {
            btn.setStyle(
                    "-fx-background-color: #f8f9fa;" +
                            "-fx-text-fill: #6c757d;" +
                            "-fx-border-color: #dee2e6;" +
                            "-fx-border-radius: 8;" +
                            "-fx-background-radius: 8;" +
                            "-fx-cursor: hand;"
            );
        }
    }
}

// ==========================================
// 3. INTERACTIVE DASHBOARD VIEW
// ==========================================
class DashboardView {

    public void show(Stage stage, String user, String activeRole) {
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: #f8f9fa;");

        // --- Top Navigation Banner ---
        HBox topNav = new HBox();
        topNav.setPadding(new Insets(15, 30, 15, 30));
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setStyle("-fx-background-color: #0f5132;");

        Text titleText = new Text("Fonkeng Health Hub");
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

        // --- Center Panel Work Station ---
        VBox workspace = new VBox(25);
        workspace.setPadding(new Insets(40));
        workspace.setAlignment(Pos.TOP_LEFT);

        // Custom Welcome Dynamic Message
        Text welcomeMsg = new Text("Welcome Back, " + user + "!");
        welcomeMsg.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        welcomeMsg.setFill(Color.valueOf("#212529"));

        Text description = new Text("Here is an overview of your management dashboard tools and profile metrics.");
        description.setFont(Font.font("Segoe UI", 14));
        description.setFill(Color.valueOf("#6c757d"));

        // Metric Mockup Cards Layout
        GridPane metricsGrid = new GridPane();
        metricsGrid.setHgap(20);
        metricsGrid.setVgap(20);

        // Generate data based on who logged in
        if ("Admin".equalsIgnoreCase(activeRole)) {
            metricsGrid.add(createMetricCard("System Status", "Operational", "#198754"), 0, 0);
            metricsGrid.add(createMetricCard("Active Users", "14 Staff Online", "#0d6efd"), 1, 0);
        } else if ("Doctor".equalsIgnoreCase(activeRole)) {
            metricsGrid.add(createMetricCard("Today's Appointments", "6 Remaining", "#fd7e14"), 0, 0);
            metricsGrid.add(createMetricCard("Pending Lab Reports", "3 Files", "#dc3545"), 1, 0);
        } else { // Patient
            metricsGrid.add(createMetricCard("Medical Record", "Up to date", "#198754"), 0, 0);
            metricsGrid.add(createMetricCard("Next Checkup", "Tommorrow, 9 AM", "#6f42c1"), 1, 0);
        }

        // --- Bottom Action Controls ---
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
            // Safely route right back to the authentication block
            LoginView loginView = new LoginView();
            loginView.show(stage);
        });

        workspace.getChildren().addAll(welcomeMsg, description, metricsGrid, logoutBtn);
        layout.setCenter(workspace);

        Scene currentScene = new Scene(layout, 750, 550);
        stage.setTitle("Fonkeng Health Hub — WorkStation Panel");
        stage.setScene(currentScene);
        stage.setResizable(true); // Allow stretching out the workspace dashboard window
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

// ==========================================
// 4. CONTROLLER (Transitions Stage on Success)
// ==========================================
class LoginController {
    public boolean login(String username, String password, String role, Stage stage) {
        String currentRole = role.toLowerCase();
        boolean authenticated = false;

        if ("admin".equals(currentRole) && "admin".equals(username) && "admin123".equals(password)) {
            authenticated = true;
        } else if ("doctor".equals(currentRole) && "doctor".equals(username) && "doc123".equals(password)) {
            authenticated = true;
        } else if ("patient".equals(currentRole) && "patient".equals(username) && "patient123".equals(password)) {
            authenticated = true;
        }

        if (authenticated) {
            // Explicitly load the newly defined system workspace dashboard scene frame
            DashboardView dashboard = new DashboardView();
            dashboard.show(stage, username, role);
            return true;
        }

        return false;
    }
}