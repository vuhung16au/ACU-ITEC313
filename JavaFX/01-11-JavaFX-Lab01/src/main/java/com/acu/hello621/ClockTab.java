package com.acu.hello621;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Part E - ClockPane Integration Tab
 * Demonstrates the reusable ClockPane component with controls
 */
public class ClockTab extends Tab {
    
    private ClockPane clockPane;
    
    public ClockTab() {
        setText("Clock Component");
        setClosable(false);
        setContent(createContent());
    }
    
    private BorderPane createContent() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        
        // Header
        Label headerLabel = new Label("Reusable ClockPane Component");
        headerLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));
        headerLabel.setStyle("-fx-text-fill: #2c3e50;");
        
        VBox topBox = new VBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(
            headerLabel,
            new Label("Demonstrates encapsulation, bindable properties, and reusability"),
            new Separator()
        );
        root.setTop(topBox);
        
        // Center: ClockPane
        clockPane = new ClockPane();
        clockPane.setPrefSize(400, 400);
        clockPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-background-color: white;");
        
        StackPane centerPane = new StackPane(clockPane);
        centerPane.setPadding(new Insets(20));
        root.setCenter(centerPane);
        
        // Right: Control panel
        VBox controlPanel = createControlPanel();
        root.setRight(controlPanel);
        
        return root;
    }
    
    /**
     * Create control panel for the clock
     */
    private VBox createControlPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(10));
        panel.setPrefWidth(250);
        panel.setStyle("-fx-background-color: #f8f9fa; -fx-border-color: lightgray; -fx-border-width: 1;");
        
        Label titleLabel = new Label("Clock Controls");
        titleLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 16));
        
        // Start/Stop buttons
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        stopButton.setDisable(true);
        
        startButton.setOnAction(e -> {
            clockPane.start();
            startButton.setDisable(true);
            stopButton.setDisable(false);
        });
        
        stopButton.setOnAction(e -> {
            clockPane.stop();
            startButton.setDisable(false);
            stopButton.setDisable(true);
        });
        
        HBox buttonBox = new HBox(10, startButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        // Theme toggle
        Label themeLabel = new Label("Theme:");
        CheckBox darkThemeCheckBox = new CheckBox("Dark Theme");
        
        // Bind checkbox to clock's dark theme property
        darkThemeCheckBox.selectedProperty().bindBidirectional(clockPane.darkThemeProperty());
        
        // Time display labels (bound to clock properties)
        Label timeDisplayLabel = new Label("Current Time:");
        Label hourLabel = new Label();
        Label minuteLabel = new Label();
        Label secondLabel = new Label();
        
        // Bind labels to clock properties
        hourLabel.textProperty().bind(clockPane.hourProperty().asString("Hour: %02d"));
        minuteLabel.textProperty().bind(clockPane.minuteProperty().asString("Minute: %02d"));
        secondLabel.textProperty().bind(clockPane.secondProperty().asString("Second: %02d"));
        
        VBox timeBox = new VBox(5, timeDisplayLabel, hourLabel, minuteLabel, secondLabel);
        timeBox.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1;");
        
        // Manual time setting
        Label setTimeLabel = new Label("Set Time Manually:");
        
        HBox hourBox = new HBox(5);
        hourBox.setAlignment(Pos.CENTER_LEFT);
        Label hourInputLabel = new Label("Hour:");
        Spinner<Integer> hourSpinner = new Spinner<>(0, 23, clockPane.getHour());
        hourSpinner.setPrefWidth(80);
        hourBox.getChildren().addAll(hourInputLabel, hourSpinner);
        
        HBox minuteBox = new HBox(5);
        minuteBox.setAlignment(Pos.CENTER_LEFT);
        Label minuteInputLabel = new Label("Minute:");
        Spinner<Integer> minuteSpinner = new Spinner<>(0, 59, clockPane.getMinute());
        minuteSpinner.setPrefWidth(80);
        minuteBox.getChildren().addAll(minuteInputLabel, minuteSpinner);
        
        HBox secondBox = new HBox(5);
        secondBox.setAlignment(Pos.CENTER_LEFT);
        Label secondInputLabel = new Label("Second:");
        Spinner<Integer> secondSpinner = new Spinner<>(0, 59, clockPane.getSecond());
        secondSpinner.setPrefWidth(80);
        secondBox.getChildren().addAll(secondInputLabel, secondSpinner);
        
        Button setTimeButton = new Button("Set Time");
        setTimeButton.setOnAction(e -> {
            clockPane.setHour(hourSpinner.getValue());
            clockPane.setMinute(minuteSpinner.getValue());
            clockPane.setSecond(secondSpinner.getValue());
        });
        
        // Reset to current time button
        Button resetButton = new Button("Reset to Current Time");
        resetButton.setOnAction(e -> {
            clockPane.setCurrentTime();
            hourSpinner.getValueFactory().setValue(clockPane.getHour());
            minuteSpinner.getValueFactory().setValue(clockPane.getMinute());
            secondSpinner.getValueFactory().setValue(clockPane.getSecond());
        });
        
        panel.getChildren().addAll(
            titleLabel,
            new Separator(),
            buttonBox,
            new Separator(),
            themeLabel,
            darkThemeCheckBox,
            new Separator(),
            timeBox,
            new Separator(),
            setTimeLabel,
            hourBox,
            minuteBox,
            secondBox,
            setTimeButton,
            resetButton
        );
        
        return panel;
    }
}
