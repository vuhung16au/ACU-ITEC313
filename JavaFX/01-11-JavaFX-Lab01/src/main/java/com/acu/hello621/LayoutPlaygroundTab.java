package com.acu.hello621;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Part B - Layout Playground Tab
 * Demonstrates BorderPane, GridPane, and FlowPane layouts
 */
public class LayoutPlaygroundTab extends Tab {
    
    public LayoutPlaygroundTab() {
        setText("Layout Playground");
        setClosable(false);
        setContent(createContent());
    }
    
    private VBox createContent() {
        VBox container = new VBox(10);
        container.setPadding(new Insets(10));
        
        // Add section labels and layouts
        container.getChildren().addAll(
            new Label("BorderPane Example:"),
            createBorderPaneSection(),
            new Separator(),
            new Label("GridPane Example:"),
            createGridPaneSection(),
            new Separator(),
            new Label("FlowPane Example:"),
            createFlowPaneSection()
        );
        
        return container;
    }
    
    /**
     * BorderPane section with Top (HBox), Center (ListView), and Right (VBox)
     */
    private BorderPane createBorderPaneSection() {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-color: lightblue; -fx-border-width: 2; -fx-padding: 5;");
        borderPane.setPrefHeight(250);
        
        // Top: HBox with Label, TextField, and Button
        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(5));
        topBox.setAlignment(Pos.CENTER_LEFT);
        Label label = new Label("Item:");
        TextField textField = new TextField();
        textField.setPromptText("Enter text here");
        Button addButton = new Button("Add");
        
        // Center: ListView
        ListView<String> listView = new ListView<>();
        
        addButton.setOnAction(e -> {
            String text = textField.getText().trim();
            if (!text.isEmpty()) {
                listView.getItems().add(text);
                textField.clear();
            } else {
                showAlert("Input Required", "Please enter some text before adding.");
            }
        });
        
        topBox.getChildren().addAll(label, textField, addButton);
        borderPane.setTop(topBox);
        borderPane.setCenter(listView);
        
        // Right: VBox with ComboBox and CheckBox
        VBox rightBox = new VBox(10);
        rightBox.setPadding(new Insets(5));
        rightBox.setAlignment(Pos.TOP_CENTER);
        
        Label themeLabel = new Label("Theme:");
        ComboBox<String> themeCombo = new ComboBox<>();
        themeCombo.getItems().addAll("Light", "Dark", "Blue", "Green");
        themeCombo.setValue("Light");
        
        CheckBox wrapCheckBox = new CheckBox("Wrap Text");
        wrapCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Wrap text: " + newVal);
        });
        
        rightBox.getChildren().addAll(themeLabel, themeCombo, wrapCheckBox);
        borderPane.setRight(rightBox);
        
        return borderPane;
    }
    
    /**
     * GridPane section with 2x2 form
     */
    private GridPane createGridPaneSection() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5));
        gridPane.setStyle("-fx-border-color: lightgreen; -fx-border-width: 2; -fx-padding: 10;");
        
        // Row 0
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");
        
        // Row 1
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");
        
        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            System.out.println("Form Submitted:");
            System.out.println("  Name: " + name);
            System.out.println("  Email: " + email);
            
            if (!name.isEmpty() && !email.isEmpty()) {
                showAlert("Form Submitted", "Name: " + name + "\nEmail: " + email);
            } else {
                showAlert("Validation Error", "Please fill in all fields.");
            }
        });
        
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(submitButton, 1, 2);
        
        return gridPane;
    }
    
    /**
     * FlowPane section with buttons that auto-wrap
     */
    private FlowPane createFlowPaneSection() {
        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.setPadding(new Insets(10));
        flowPane.setStyle("-fx-border-color: lightcoral; -fx-border-width: 2; -fx-padding: 10;");
        flowPane.setPrefWrapLength(400); // Wrap at 400px
        
        // Add multiple buttons
        for (int i = 1; i <= 10; i++) {
            Button btn = new Button("Button " + i);
            btn.setOnAction(e -> System.out.println(((Button)e.getSource()).getText() + " clicked"));
            flowPane.getChildren().add(btn);
        }
        
        return flowPane;
    }
    
    /**
     * Helper method to show alerts with error handling
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
