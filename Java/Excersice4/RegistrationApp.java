package Assigments.Excersice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Student Name
        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        // Course Code
        Label courseLabel = new Label("Course Code:");
        ComboBox<String> courseComboBox = new ComboBox<>();
        courseComboBox.getItems().addAll("CMP 269", "CMP 338", "CMP 426", "MAT 175");
        courseComboBox.setValue("CMP 269"); // default selection
        grid.add(courseLabel, 0, 1);
        grid.add(courseComboBox, 1, 1);

        // Register Button
        Button registerButton = new Button("Register");
        grid.add(registerButton, 1, 2);

        // Status Label
        Label statusLabel = new Label("");
        grid.add(statusLabel, 0, 3, 2, 1); // spans 2 columns

        // Button action using a Lambda expression
        registerButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String course = courseComboBox.getValue();

            if (name.isEmpty() || course == null) {
                statusLabel.setText("Please fill in all fields.");
            } else {
                statusLabel.setText("Registration Successful for " + name + " in " + course + "!");
            }
        });

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Lehman Course Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(RegistrationApp.class, args);
    }
}