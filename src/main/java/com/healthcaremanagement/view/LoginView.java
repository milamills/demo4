package com.healthcaremanagement.view;

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
import com.healthcaremanagement.controller.LoginController;

/**
 * LoginView - User Authentication Interface
 * 
 * Handles the login screen UI and form validation.
 * Displays username, password, and account type (Admin/Doctor/Patient) fields.
 */
public class LoginView {

    private static final String APP_TITLE = "Healthcare Management System";
    private static final String APP_SUBTITLE = "Secure Access Portal";

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

        // Header Section
        VBox header = new VBox(12);
        header.setAlignment(Pos.CENTER);

        StackPane logo = buildModernLogo(56);

        Text mainTitle = new Text(APP_TITLE);
        mainTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        mainTitle.setFill(Color.valueOf("#0f5132"));

        Text subtitle = new Text(APP_SUBTITLE);
        subtitle.setFont(Font.font("Segoe UI", 13));
        subtitle.setFill(Color.valueOf("#6c757d"));

        header.getChildren().addAll(logo, mainTitle, subtitle);

        // Form Section
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

        // Account Type Selection
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

        // Login Button
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

        // Footer
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        Label footerNote = new Label("End-to-End Encrypted Session");
        footerNote.setFont(Font.font("Segoe UI", 11));
        footerNote.setTextFill(Color.valueOf("#adb5bd"));
        footer.getChildren().add(footerNote);

        card.getChildren().addAll(header, form, footer);
        root.getChildren().add(card);

        Scene scene = new Scene(root, 520, 620);
        stage.setTitle(APP_TITLE + " — Authentication");
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
