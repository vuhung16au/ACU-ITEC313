package com.acu.hello621;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JavaFX Application - Main Entry Point
 * Part A: Project Setup with Menu Bar and Tabbed Interface
 * Demonstrates Parts B-F: Layouts, Bindings, Images, Fonts, and Reusable Components
 */
public class App extends Application {
    
    private Scene scene;
    private BorderPane root;
    private String currentTheme = "light";

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Application Demo - Parts B to F");
        stage.setMinWidth(900);
        stage.setMinHeight(700);
        
        // Create root layout
        root = new BorderPane();
        
        // Create menu bar (Part A requirement)
        MenuBar menuBar = createMenuBar(stage);
        root.setTop(menuBar);
        
        // Create tab pane with all parts
        TabPane tabPane = createTabPane();
        root.setCenter(tabPane);
        
        // Create scene with CSS
        scene = new Scene(root, 1200, 800);
        
        // Load external CSS (Bonus)
        String css = getClass().getResource("/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Create menu bar with Help -> About menu (Part A)
     */
    private MenuBar createMenuBar(Stage stage) {
        MenuBar menuBar = new MenuBar();
        
        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitItem);
        
        // View menu with theme options (Bonus)
        Menu viewMenu = new Menu("View");
        MenuItem lightThemeItem = new MenuItem("Light Theme");
        MenuItem darkThemeItem = new MenuItem("Dark Theme");
        MenuItem blueThemeItem = new MenuItem("Blue Theme");
        
        lightThemeItem.setOnAction(e -> applyTheme("light"));
        darkThemeItem.setOnAction(e -> applyTheme("dark"));
        blueThemeItem.setOnAction(e -> applyTheme("blue"));
        
        viewMenu.getItems().addAll(lightThemeItem, darkThemeItem, blueThemeItem);
        
        // Help menu (Part A requirement)
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> AboutDialog.show(stage));
        helpMenu.getItems().add(aboutItem);
        
        menuBar.getMenus().addAll(fileMenu, viewMenu, helpMenu);
        
        return menuBar;
    }
    
    /**
     * Create tab pane with all demonstration tabs
     */
    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();
        
        // Add all tabs for Parts B-E
        tabPane.getTabs().addAll(
            new LayoutPlaygroundTab(),     // Part B
            new GraphicsBindingTab(),      // Part C
            new ImagesFontsTab(),          // Part D
            new ClockTab()                 // Part E
        );
        
        return tabPane;
    }
    
    /**
     * Apply CSS theme (Bonus feature)
     */
    private void applyTheme(String theme) {
        root.getStyleClass().removeAll("light-theme", "dark-theme", "blue-theme");
        
        switch (theme) {
            case "dark":
                root.getStyleClass().add("dark-theme");
                break;
            case "blue":
                root.getStyleClass().add("blue-theme");
                break;
            default:
                // light theme is default
                break;
        }
        
        currentTheme = theme;
    }

    public static void main(String[] args) {
        launch();
    }
}