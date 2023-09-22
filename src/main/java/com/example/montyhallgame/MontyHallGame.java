package com.example.montyhallgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Random;

public class MontyHallGame extends Application
{
    private static final int NUM_DOORS = 3;
    private Random random;
    private int winningDoor;
    private int selectedDoor;
    private int revealedDoor;
    private Text instructionsText;
    private Text resultText;
    private Button[] doorButtons;

    public void start(Stage stage)
    {
        random = new Random();
        winningDoor = random.nextInt(NUM_DOORS);
        selectedDoor = -1;
        revealedDoor = -1;

        instructionsText = new Text("Choose a door:");
        resultText = new Text();

        Image image1 = new Image("File:closed-door-one.jpg");
        ImageView imageView1 = new ImageView(image1);

        Image image2 = new Image("File:closed-door-two.jpg");
        ImageView imageView2 = new ImageView(image2);

        Image image3 = new Image("File:closed-door-three.jpg");
        ImageView imageView3 = new ImageView(image3);

        doorButtons = new Button[NUM_DOORS];


        Button button1 = new Button("Door 1");
        button1.setOnAction(event -> handleDoorSelection(0));
        doorButtons[0] = button1;
        button1.setGraphic(imageView1);

        Button button2 = new Button("Door 2");
        button2.setOnAction(event -> handleDoorSelection(1));
        doorButtons[1] = button2;
        button2.setGraphic(imageView2);

        Button button3 = new Button("Door 3");
        button3.setOnAction(event -> handleDoorSelection(2));
        doorButtons[2] = button3;
        button3.setGraphic(imageView3);

        //Stage setup and door buttons
        HBox doorButtonBox = new HBox(10);
        doorButtonBox.getChildren().addAll(doorButtons);
        doorButtonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(20);
        root.getChildren().addAll(instructionsText, doorButtonBox, resultText);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 900, 550);

        stage.setScene(scene);
        stage.setTitle("Monty Hall Problem");
        stage.show();
    }

    private void handleDoorSelection(int doorIndex)
    {
        if (selectedDoor == -1)
        {
            // First selection
            selectedDoor = doorIndex;
            revealDoor();
        }
        else
        {
            // Second selection
            boolean win = (doorIndex == winningDoor);
            resultText.setText("You " + (win ? "win!" : "lose.") + " The winning door was Door " + (winningDoor+1) + ".");
            for (Button button : doorButtons)
            {
                button.setDisable(true);
            }
        }
    }

    private void revealDoor()
    {
        // The revealed door must be a losing door that wasn't selected
        do
        {
            revealedDoor = random.nextInt(NUM_DOORS);
        }
        while (revealedDoor == selectedDoor || revealedDoor == winningDoor);
        doorButtons[revealedDoor].setDisable(true);
        instructionsText.setText("Door " + (revealedDoor+1) + " revealed as a losing door. Choose another door or stick with your original choice.");
    }
    public static void main(String[] args) {
        launch(args);
    }
}