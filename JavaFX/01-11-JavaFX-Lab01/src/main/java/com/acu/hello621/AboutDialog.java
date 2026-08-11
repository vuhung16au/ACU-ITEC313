package com.acu.hello621;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Bonus - About Dialog
 * Demonstrates secondary window (Multiple Stages)
 */
public class AboutDialog {
    
    /**
     * Show the About dialog
     */
    public static void show(Stage owner) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);
        dialog.setTitle("About");
        dialog.setResizable(false);
        
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: white;");
        
        // Title
        Label titleLabel = new Label("JavaFX Application Demo");
        titleLabel.setFont(Font.font("SansSerif", FontWeight.BOLD, 20));
        
        // Version info
        Label versionLabel = new Label("Version 1.0");
        versionLabel.setFont(Font.font("SansSerif", FontWeight.NORMAL, 14));
        
        // Description
        Label descLabel = new Label(
            "A comprehensive JavaFX application demonstrating:\n\n" +
            "• Multiple Layout Managers\n" +
            "• Property Bindings & Animations\n" +
            "• Image & Font Handling\n" +
            "• Reusable Components\n" +
            "• Custom CSS Themes\n" +
            "• Clean OOP Design"
        );
        descLabel.setStyle("-fx-text-alignment: center;");
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(300);
        
        // JavaFX info
        Label javafxLabel = new Label("Built with JavaFX " + SystemInfo.javafxVersion());
        javafxLabel.setFont(Font.font("SansSerif", FontWeight.NORMAL, 12));
        javafxLabel.setStyle("-fx-text-fill: gray;");
        
        Label javaLabel = new Label("Running on Java " + SystemInfo.javaVersion());
        javaLabel.setFont(Font.font("SansSerif", FontWeight.NORMAL, 12));
        javaLabel.setStyle("-fx-text-fill: gray;");
        
        // Close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialog.close());
        closeButton.setPrefWidth(100);
        
        content.getChildren().addAll(
            titleLabel,
            versionLabel,
            new Label(), // Spacer
            descLabel,
            new Label(), // Spacer
            javafxLabel,
            javaLabel,
            new Label(), // Spacer
            closeButton
        );
        
        Scene scene = new Scene(content, 400, 400);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
