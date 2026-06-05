package com.healthcaremanagement.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import com.healthcaremanagement.model.MetricCard;
import com.healthcaremanagement.util.StyleConstants;

/**
 * MetricCardComponent - Reusable metric card UI component
 * 
 * Creates a styled metric card that displays a headline and counter value.
 */
public class MetricCardComponent {
    
    private static final int CARD_WIDTH = 240;
    private static final int CARD_HEIGHT = 100;
    private static final int PADDING = 16;
    private static final int SPACING = 8;
    private static final int BORDER_WIDTH = 5;
    
    /**
     * Creates a metric card component from a MetricCard model
     * 
     * @param metricCard The metric card data model
     * @return VBox containing the styled metric card
     */
    public static VBox createMetricCard(MetricCard metricCard) {
        return createMetricCard(metricCard.getHeadline(), metricCard.getCounter(), metricCard.getBorderColor());
    }
    
    /**
     * Creates a metric card component with individual parameters
     * 
     * @param headline The metric title
     * @param counter The metric value
     * @param hexBorderColor The hex color code for the border
     * @return VBox containing the styled metric card
     */
    public static VBox createMetricCard(String headline, String counter, String hexBorderColor) {
        VBox metricBox = new VBox(SPACING);
        metricBox.setPrefSize(CARD_WIDTH, CARD_HEIGHT);
        metricBox.setPadding(new Insets(PADDING));
        metricBox.setStyle(
                "-fx-background-color: " + StyleConstants.COLOR_BG_WHITE + ";" +
                "-fx-background-radius: " + StyleConstants.RADIUS_MEDIUM + ";" +
                "-fx-border-color: " + hexBorderColor + ";" +
                "-fx-border-width: 0 0 0 " + BORDER_WIDTH + ";" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.03), 10, 0, 0, 4);"
        );

        Label head = new Label(headline.toUpperCase());
        head.setFont(Font.font(StyleConstants.FONT_FAMILY, FontWeight.BOLD, StyleConstants.FONT_SIZE_XSMALL));
        head.setTextFill(Color.valueOf(StyleConstants.COLOR_TEXT_MUTED));

        Label count = new Label(counter);
        count.setFont(Font.font(StyleConstants.FONT_FAMILY, FontWeight.BOLD, 18));
        count.setTextFill(Color.valueOf(StyleConstants.COLOR_TEXT_DARK));

        metricBox.getChildren().addAll(head, count);
        return metricBox;
    }
}
