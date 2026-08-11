package com.acu.hello621;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A reusable analog clock component with bindable properties.
 * Features: hour/minute/second hands, tick marks, start/stop controls,
 * and light/dark theme support.
 */
public class ClockPane extends Pane {
    private IntegerProperty hour = new SimpleIntegerProperty(0);
    private IntegerProperty minute = new SimpleIntegerProperty(0);
    private IntegerProperty second = new SimpleIntegerProperty(0);
    private BooleanProperty running = new SimpleBooleanProperty(false);
    private BooleanProperty darkTheme = new SimpleBooleanProperty(false);
    
    private Timeline timeline;
    
    /**
     * Construct a default clock with the current time
     */
    public ClockPane() {
        setCurrentTime();
        setupTimeline();
        
        // Repaint when size changes
        widthProperty().addListener(e -> paintClock());
        heightProperty().addListener(e -> paintClock());
        
        // Repaint when time properties change
        hour.addListener(e -> paintClock());
        minute.addListener(e -> paintClock());
        second.addListener(e -> paintClock());
        darkTheme.addListener(e -> paintClock());
        
        paintClock();
    }

    /**
     * Construct a clock with specified hour, minute, and second
     */
    public ClockPane(int hour, int minute, int second) {
        this();
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    /**
     * Setup the timeline for automatic updates
     */
    private void setupTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            setCurrentTime();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    // Property getters
    public IntegerProperty hourProperty() { return hour; }
    public IntegerProperty minuteProperty() { return minute; }
    public IntegerProperty secondProperty() { return second; }
    public BooleanProperty runningProperty() { return running; }
    public BooleanProperty darkThemeProperty() { return darkTheme; }
    
    // Getters
    public int getHour() { return hour.get(); }
    public int getMinute() { return minute.get(); }
    public int getSecond() { return second.get(); }
    public boolean isRunning() { return running.get(); }
    public boolean isDarkTheme() { return darkTheme.get(); }

    // Setters
    public void setHour(int hour) { this.hour.set(hour); }
    public void setMinute(int minute) { this.minute.set(minute); }
    public void setSecond(int second) { this.second.set(second); }
    public void setDarkTheme(boolean darkTheme) { this.darkTheme.set(darkTheme); }
    
    /**
     * Start the clock animation
     */
    public void start() {
        if (!running.get()) {
            timeline.play();
            running.set(true);
        }
    }
    
    /**
     * Stop the clock animation
     */
    public void stop() {
        if (running.get()) {
            timeline.stop();
            running.set(false);
        }
    }
    
    /**
     * Set the current time for the clock
     */
    public void setCurrentTime() {
        Calendar calendar = new GregorianCalendar();
        this.hour.set(calendar.get(Calendar.HOUR_OF_DAY));
        this.minute.set(calendar.get(Calendar.MINUTE));
        this.second.set(calendar.get(Calendar.SECOND));
    }
    
    /**
     * Paint the clock with all its components
     */
    private void paintClock() {
        double clockRadius = Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Theme colors
        Color bgColor = darkTheme.get() ? Color.rgb(40, 40, 40) : Color.WHITE;
        Color fgColor = darkTheme.get() ? Color.rgb(200, 200, 200) : Color.BLACK;
        Color hourColor = darkTheme.get() ? Color.LIGHTGREEN : Color.GREEN;
        Color minuteColor = darkTheme.get() ? Color.LIGHTBLUE : Color.BLUE;
        Color secondColor = darkTheme.get() ? Color.LIGHTCORAL : Color.RED;

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(bgColor);
        circle.setStroke(fgColor);
        circle.setStrokeWidth(2);
        
        // Draw tick marks for hours
        for (int i = 0; i < 12; i++) {
            double angle = i * (2 * Math.PI / 12);
            double x1 = centerX + clockRadius * 0.9 * Math.sin(angle);
            double y1 = centerY - clockRadius * 0.9 * Math.cos(angle);
            double x2 = centerX + clockRadius * 0.95 * Math.sin(angle);
            double y2 = centerY - clockRadius * 0.95 * Math.cos(angle);
            Line tick = new Line(x1, y1, x2, y2);
            tick.setStroke(fgColor);
            tick.setStrokeWidth(2);
            getChildren().add(tick);
        }
        
        // Draw numbers
        Text t12 = new Text(centerX - 8, centerY - clockRadius + 18, "12");
        Text t3 = new Text(centerX + clockRadius - 15, centerY + 5, "3");
        Text t6 = new Text(centerX - 5, centerY + clockRadius - 10, "6");
        Text t9 = new Text(centerX - clockRadius + 8, centerY + 5, "9");
        t12.setFill(fgColor);
        t3.setFill(fgColor);
        t6.setFill(fgColor);
        t9.setFill(fgColor);
        
        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength * Math.sin(second.get() * (2 * Math.PI / 60));
        double secondY = centerY - sLength * Math.cos(second.get() * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(secondColor);
        sLine.setStrokeWidth(1);

        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength * Math.sin(minute.get() * (2 * Math.PI / 60));
        double minuteY = centerY - mLength * Math.cos(minute.get() * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(minuteColor);
        mLine.setStrokeWidth(2);
        
        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength * Math.sin((hour.get() % 12 + minute.get() / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength * Math.cos((hour.get() % 12 + minute.get() / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(hourColor);
        hLine.setStrokeWidth(3);
        
        getChildren().clear();
        getChildren().addAll(circle, t12, t3, t6, t9, sLine, mLine, hLine);
    }
}
