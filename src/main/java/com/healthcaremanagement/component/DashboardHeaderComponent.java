package com.healthcaremanagement.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import com.healthcaremanagement.util.StyleConstants;

/**
 * DashboardHeaderComponent - Dashboard top navigation header
 * 
 * Creates a styled header bar with title and session information.
 */
public class DashboardHeaderComponent {
    
    /**
     * Creates the dashboard header component
     * 
     * @param activeRole The currently active user role
     * @return HBox containing the styled header
     */
    public static HBox createHeader(String activeRole) {
        HBox topNav = new HBox();
        topNav.setPadding(new Insets(StyleConstants.PADDING_SMALL, StyleConstants.PADDING_LARGE, 
                                     StyleConstants.PADDING_SMALL, StyleConstants.PADDING_LARGE));
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setStyle(StyleConstants.NAV_BAR_STYLE);

        Text titleText = new Text(StyleConstants.APP_TITLE);
        titleText.setFont(Font.font(StyleConstants.FONT_FAMILY, FontWeight.BOLD, StyleConstants.FONT_SIZE_TITLE));
        titleText.setFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label sessionLabel = new Label("Active Session: " + activeRole);
        sessionLabel.setFont(Font.font(StyleConstants.FONT_FAMILY, FontWeight.MEDIUM, StyleConstants.FONT_SIZE_SMALL));
        sessionLabel.setTextFill(Color.valueOf("#e8f5e9"));
        sessionLabel.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-padding: 6 12; -fx-background-radius: 6;");

        topNav.getChildren().addAll(titleText, spacer, sessionLabel);
        return topNav;
    }
}
