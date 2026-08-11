package com.acu.hello621;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.InputStream;

/**
 * Part D - Images & Fonts Tab
 * Demonstrates image loading with error handling and custom fonts
 */
public class ImagesFontsTab extends Tab {
    
    private ImageView imageView;
    
    public ImagesFontsTab() {
        setText("Images & Fonts");
        setClosable(false);
        setContent(createContent());
    }
    
    private VBox createContent() {
        VBox container = new VBox(20);
        container.setPadding(new Insets(20));
        container.setAlignment(Pos.TOP_CENTER);
        
        // Header with custom font
        Label headerLabel = new Label("Images & Custom Fonts Demo");
        headerLabel.setFont(Font.font("Serif", FontWeight.BOLD, 28));
        headerLabel.setStyle("-fx-text-fill: #2c3e50;");
        
        // Subtitle with different custom font
        Label subtitleLabel = new Label("Demonstrating JavaFX Image and Font Capabilities");
        subtitleLabel.setFont(Font.font("SansSerif", FontWeight.NORMAL, 14));
        subtitleLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-style: italic;");
        
        // Image display area
        VBox imageBox = createImageSection();
        
        container.getChildren().addAll(headerLabel, subtitleLabel, new Separator(), imageBox);
        
        return container;
    }
    
    /**
     * Create image section with controls
     */
    private VBox createImageSection() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #ecf0f1; -fx-padding: 15; -fx-border-color: #bdc3c7; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5;");
        
        // ImageView
        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(300);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 2);");
        
        // Try to load a default image, handle missing file gracefully
        loadImage("/icon.png");
        
        // Width slider
        Label widthLabel = new Label("Image Width: 300");
        Slider widthSlider = new Slider(100, 500, 300);
        widthSlider.setShowTickMarks(true);
        widthSlider.setShowTickLabels(true);
        widthSlider.setMajorTickUnit(100);
        
        // Bind slider to image width
        imageView.fitWidthProperty().bind(widthSlider.valueProperty());
        widthSlider.valueProperty().addListener((obs, oldVal, newVal) -> 
            widthLabel.setText(String.format("Image Width: %.0f", newVal))
        );
        
        // Load image button
        Button loadButton = new Button("Load Sample Image");
        loadButton.setFont(Font.font("SansSerif", FontWeight.BOLD, 12));
        loadButton.setOnAction(e -> loadImage("/icon.png"));
        
        // Load custom image button
        Button customLoadButton = new Button("Try Load Custom Image");
        customLoadButton.setFont(Font.font("SansSerif", FontWeight.NORMAL, 12));
        customLoadButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("/icon.png");
            dialog.setTitle("Load Image");
            dialog.setHeaderText("Enter image path");
            dialog.setContentText("Path:");
            
            dialog.showAndWait().ifPresent(path -> loadImage(path));
        });
        
        HBox buttonBox = new HBox(10, loadButton, customLoadButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        box.getChildren().addAll(
            imageView,
            widthLabel,
            widthSlider,
            buttonBox
        );
        
        return box;
    }
    
    /**
     * Load an image with robust error handling
     */
    private void loadImage(String path) {
        try {
            // Try to load from resources
            InputStream stream = getClass().getResourceAsStream(path);
            
            if (stream != null) {
                Image image = new Image(stream);
                
                if (image.isError()) {
                    throw new Exception("Image loading failed");
                }
                
                imageView.setImage(image);
                showInfo("Image Loaded", "Successfully loaded image: " + path);
            } else {
                // If not found in resources, create a placeholder
                createPlaceholderImage();
                showWarning("Image Not Found", 
                    "Could not find image at: " + path + "\n\nShowing placeholder instead.\n\n" +
                    "To add your own image:\n" +
                    "1. Place an image file (e.g., icon.png) in src/main/resources/\n" +
                    "2. Click 'Load Sample Image' button");
            }
            
        } catch (Exception e) {
            createPlaceholderImage();
            showError("Image Loading Error", 
                "Failed to load image: " + path + "\n\n" +
                "Error: " + e.getMessage() + "\n\n" +
                "Showing placeholder instead.");
        }
    }
    
    /**
     * Create a placeholder when image is not available
     */
    private void createPlaceholderImage() {
        // Create a simple placeholder image (colored rectangle)
        javafx.scene.canvas.Canvas canvas = new javafx.scene.canvas.Canvas(300, 300);
        javafx.scene.canvas.GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Draw gradient background
        javafx.scene.paint.LinearGradient gradient = new javafx.scene.paint.LinearGradient(
            0, 0, 1, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
            new javafx.scene.paint.Stop(0, javafx.scene.paint.Color.LIGHTBLUE),
            new javafx.scene.paint.Stop(1, javafx.scene.paint.Color.DODGERBLUE)
        );
        gc.setFill(gradient);
        gc.fillRect(0, 0, 300, 300);
        
        // Draw text
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setFont(Font.font("SansSerif", FontWeight.BOLD, 24));
        gc.fillText("Image", 100, 140);
        gc.fillText("Placeholder", 70, 170);
        
        // Convert canvas to image
        javafx.scene.image.WritableImage writableImage = new javafx.scene.image.WritableImage(300, 300);
        canvas.snapshot(null, writableImage);
        imageView.setImage(writableImage);
    }
    
    /**
     * Show information alert
     */
    private void showInfo(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * Show warning alert
     */
    private void showWarning(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * Show error alert with robust error handling
     */
    private void showError(String title, String content) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText("Error Loading Image");
            alert.setContentText(content);
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("Error showing alert: " + e.getMessage());
        }
    }
}
