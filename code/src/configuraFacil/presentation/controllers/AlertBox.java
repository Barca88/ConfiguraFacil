package configuraFacil.presentation.controllers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        window.setMinWidth(500);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Sim");
        yesButton.setStyle("-fx-background-color: #195988");
        yesButton.setStyle("-fx-background-radius: 5em");
        Button noButton = new Button("Não");
        noButton.setStyle("-fx-background-color: #195988");
        noButton.setStyle("-fx-background-radius: 5em");

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, Color.web("#376888"));

        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }

    public static void alert(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        window.setMinWidth(500);
        Label label = new Label();
        label.setText(message);


        Button okButton = new Button("OK");
        okButton.setStyle("-fx-background-color: #195988");
        okButton.setStyle("-fx-background-radius: 5em");

        okButton.setOnAction(e -> {window.close();});

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label, okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, Color.web("#376888"));

        window.setScene(scene);
        window.showAndWait();
    }
}
