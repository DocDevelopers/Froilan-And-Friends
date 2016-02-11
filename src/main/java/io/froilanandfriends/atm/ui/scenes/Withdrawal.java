package io.froilanandfriends.atm.ui.scenes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Withdrawal extends Application {

    Stage window;
    Button withdrawalButton;
    Button backButton;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("International Bank of Froilan");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(20);
        layout.setHgap(10);

        //Withdrawal Amount
        Label withdrawalAmount = new Label("Withdrawal Amount");
        GridPane.setConstraints(withdrawalAmount, 0, 0);
        TextField setWithdrawalAmount = new TextField();
        setWithdrawalAmount.setPromptText("Enter amount to withdraw");
        GridPane.setConstraints(setWithdrawalAmount, 1, 0);


        //Withdrawal Button
        withdrawalButton = new Button("Confirm Withdrawal");
        GridPane.setConstraints(withdrawalButton, 1, 1);

        //Back Button
        backButton = new Button("Go Back");
        GridPane.setConstraints(backButton, 0, 7);
        backButton.setOnAction(e -> {
            //window.setScene();
            System.out.println("Back button Pressed");
        });

        withdrawalButton.setOnAction(e -> {
            ErrorMessages.isInt(setWithdrawalAmount, "withdrawal");
        });

        window.setOnCloseRequest(e -> {
            e.consume();                                    //Tells the system to consume the .setOnCloseRequest method, and it will be handled by the closeProgram method.
            ErrorMessages.closeProgram(window);               //Will run closeProgram() if the red 'X' button is clicked.
        });


        layout.getChildren().addAll(withdrawalAmount, setWithdrawalAmount, withdrawalButton, backButton);
        Scene scene = new Scene(layout, 325, 250);
        window.setScene(scene);
        window.show();

    }

}

