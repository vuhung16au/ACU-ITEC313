The current project is a just Hello world JavaFX application. 

Please turn it into a full-fledged JavaFX application with the following requirements and tasks.

Note: "Part A" is already implemented. You'll help me to implement "Part B" to "Part F" as described below.

# Requirements & Tasks

# Part A — Project Setup (0.25 mark)

Create a Java 17+ project with JavaFX 21+.

Entry point: Main extends Application with start(Stage primaryStage).

Set app title and minimum window size; include a Help → About menu.

-> this step is DONE (impelemnted). We are using 

- Maven for dependency management and building the project.
- JDK 25 for the Java version.
- JavaFX 25 for the GUI components.

Help me implement the following tasks in Part B, C, D, E, and F as described in the requirements.

# Part B — Layout Playground (1.0 mark)
Create a tab/page that demonstrates three different layouts:

A BorderPane page section with:

Top: HBox containing a Label, TextField, and Button (button appends text to a ListView in the center).

Center: ListView<String>.

Right: VBox with ComboBox<String> (e.g., themes), and a CheckBox (toggles wrap or a feature).

A small GridPane area with a 2×2 form (labels + textfields) and a Submit button that prints values to console.

A FlowPane or StackPane mini-area with a few Buttons that auto-wrap when resized.

Evidence: The report must include a screenshot highlighting how each layout arranges nodes.

# Part C — Graphics & Binding (1.25 marks)

Create a tab/page with shapes and properties:

Add a Circle centered in a resizable Pane. Bind its center to the pane’s width/height so it stays centered when resizing.

Add a Slider labeled “Radius” and bind the circle’s radius to the slider (unidirectional binding).

Add a TextField labeled “Caption” and a Label bound bidirectionally (typing updates label and vice versa).

Add controls to rotate the circle (e.g., Slider 0–360 bound to circle.rotateProperty()), and a button “Pulse” that animates scale or rotate (simple Timeline acceptable).

Add style controls (e.g., color picker or combo) that update node style via setStyle(...) (CSS).

Evidence: Show code snippets in the report that demonstrate one uni- and one bidirectional binding.

# Part D — Images & Fonts (0.5 mark)

Load an image using Image and display with ImageView (use setPreserveRatio(true) and a slider bound to fitWidth).

The sample image is located in `samples/icon.png`. 

Handle missing file robustly (try/catch + user feedback Alert).

Demonstrate a custom Font for a header or caption.

# Part E — Reusable Component: ClockPane (1.5 marks)

Please reuse the ClockPane class provided in the samples folder. The ClockPane should be implemented as a reusable component that can be dropped into any container. 

Location: `samples/ClockPane.java`

Minimum features:
Implement a reusable analog clock on a Pane (own class). Minimum features:

Draw hour/minute/second hands and tick marks using shapes.

A Timeline that updates once per second. Add Start and Stop buttons.

Expose bindable properties (e.g., hour, minute, second, and running).

Provide a style toggle (light/dark) that updates colors.

Show how the component can be dropped into any container (encapsulation & reusability).

Tip: Keep drawing logic inside the component; only public setters/getters and JavaFX properties are exposed.

# Part F — Code Quality & UX (0.5 mark)
Apply clean OOP design (small focused classes, no God class).

Use meaningful names, comments/javadoc for public methods.

Graceful error handling (alerts, validation messages).

Responsive/resizable layout (try resizing the window).

# Bonus (up to +0.25 mark; capped at 5.0)
External CSS file with two themes.

Secondary window demonstrating Multiple Stages (e.g., an “About” dialog or a separate preview window).

# Verification

Make sure `mvn clean javafx:run` works and the application runs without errors.

# Notes

The package name is `hello621`. Do not change it. 