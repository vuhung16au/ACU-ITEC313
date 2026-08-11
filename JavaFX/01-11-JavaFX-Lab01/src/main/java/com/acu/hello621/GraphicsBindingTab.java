package com.acu.hello621;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Part C - Graphics & Binding Tab
 * Demonstrates property bindings, animations, and dynamic styling
 */
public class GraphicsBindingTab extends Tab {
    
    private Circle circle;
    private Label captionLabel;
    private StringProperty captionProperty = new SimpleStringProperty("Hello JavaFX!");
    
    public GraphicsBindingTab() {
        setText("Graphics & Binding");
        setClosable(false);
        setContent(createContent());
    }
    
    private BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Center: Pane with circle
        Pane circlePane = createCirclePane();
        root.setCenter(circlePane);
        
        // Right: Control panel
        VBox controlPanel = createControlPanel();
        root.setRight(controlPanel);
        
        return root;
    }
    
    /**
     * Create a resizable pane with a centered circle
     */
    private Pane createCirclePane() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: gray; -fx-border-width: 1;");
        pane.setMinSize(400, 400);
        
        // Create circle
        circle = new Circle(50);
        circle.setFill(Color.DODGERBLUE);
        circle.setStroke(Color.DARKBLUE);
        circle.setStrokeWidth(2);
        
        // Bind circle center to pane center (unidirectional binding)
        // EVIDENCE OF UNIDIRECTIONAL BINDING:
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        
        pane.getChildren().add(circle);
        
        // Add caption label
        captionLabel = new Label();
        captionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        // EVIDENCE OF BIDIRECTIONAL BINDING:
        captionLabel.textProperty().bindBidirectional(captionProperty);
        
        // Position label below circle
        captionLabel.layoutXProperty().bind(
            pane.widthProperty().divide(2).subtract(captionLabel.widthProperty().divide(2))
        );
        captionLabel.layoutYProperty().bind(
            pane.heightProperty().divide(2).add(circle.radiusProperty()).add(20)
        );
        
        pane.getChildren().add(captionLabel);
        
        return pane;
    }
    
    /**
     * Create control panel with sliders, text field, and buttons
     */
    private VBox createControlPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(10));
        panel.setPrefWidth(250);
        panel.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-width: 1;");
        
        // Radius slider (unidirectional binding)
        Label radiusLabel = new Label("Radius: 50");
        Slider radiusSlider = new Slider(10, 150, 50);
        radiusSlider.setShowTickLabels(true);
        radiusSlider.setShowTickMarks(true);
        
        // EVIDENCE OF UNIDIRECTIONAL BINDING:
        circle.radiusProperty().bind(radiusSlider.valueProperty());
        radiusSlider.valueProperty().addListener((obs, oldVal, newVal) -> 
            radiusLabel.setText(String.format("Radius: %.0f", newVal))
        );
        
        // Rotation slider
        Label rotateLabel = new Label("Rotation: 0°");
        Slider rotateSlider = new Slider(0, 360, 0);
        rotateSlider.setShowTickLabels(true);
        rotateSlider.setShowTickMarks(true);
        rotateSlider.setMajorTickUnit(90);
        
        circle.rotateProperty().bind(rotateSlider.valueProperty());
        rotateSlider.valueProperty().addListener((obs, oldVal, newVal) -> 
            rotateLabel.setText(String.format("Rotation: %.0f°", newVal))
        );
        
        // Caption text field (bidirectional binding)
        Label captionTextLabel = new Label("Caption:");
        TextField captionTextField = new TextField();
        
        // EVIDENCE OF BIDIRECTIONAL BINDING:
        captionTextField.textProperty().bindBidirectional(captionProperty);
        
        // Color picker
        Label colorLabel = new Label("Circle Color:");
        ColorPicker colorPicker = new ColorPicker(Color.DODGERBLUE);
        colorPicker.setOnAction(e -> {
            Color color = colorPicker.getValue();
            circle.setFill(color);
            // Apply CSS style for dynamic styling demonstration
            circle.setStyle(String.format("-fx-fill: rgba(%d, %d, %d, %.2f);",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255),
                color.getOpacity()
            ));
        });
        
        // Pulse animation button
        Button pulseButton = new Button("Pulse Animation");
        pulseButton.setOnAction(e -> playPulseAnimation());
        
        // Style combo box
        Label styleLabel = new Label("Circle Style:");
        ComboBox<String> styleCombo = new ComboBox<>();
        styleCombo.getItems().addAll("Solid", "Gradient", "Glow");
        styleCombo.setValue("Solid");
        styleCombo.setOnAction(e -> applyStyle(styleCombo.getValue()));
        
        panel.getChildren().addAll(
            radiusLabel, radiusSlider,
            new Separator(),
            rotateLabel, rotateSlider,
            new Separator(),
            captionTextLabel, captionTextField,
            new Separator(),
            colorLabel, colorPicker,
            new Separator(),
            pulseButton,
            new Separator(),
            styleLabel, styleCombo
        );
        
        return panel;
    }
    
    /**
     * Play a pulse animation on the circle
     */
    private void playPulseAnimation() {
        double originalRadius = circle.getRadius();
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, e -> circle.setScaleX(1.0)),
            new KeyFrame(Duration.ZERO, e -> circle.setScaleY(1.0)),
            new KeyFrame(Duration.millis(300), e -> circle.setScaleX(1.5)),
            new KeyFrame(Duration.millis(300), e -> circle.setScaleY(1.5)),
            new KeyFrame(Duration.millis(600), e -> circle.setScaleX(1.0)),
            new KeyFrame(Duration.millis(600), e -> circle.setScaleY(1.0))
        );
        timeline.setCycleCount(3);
        timeline.play();
    }
    
    /**
     * Apply different CSS styles to the circle
     */
    private void applyStyle(String style) {
        switch (style) {
            case "Solid":
                circle.setStyle("-fx-fill: dodgerblue; -fx-stroke: darkblue; -fx-stroke-width: 2;");
                break;
            case "Gradient":
                circle.setStyle("-fx-fill: linear-gradient(to bottom, lightblue, darkblue); -fx-stroke: navy; -fx-stroke-width: 3;");
                break;
            case "Glow":
                circle.setStyle("-fx-fill: cyan; -fx-effect: dropshadow(gaussian, cyan, 20, 0.7, 0, 0); -fx-stroke: white; -fx-stroke-width: 2;");
                break;
        }
    }
}
