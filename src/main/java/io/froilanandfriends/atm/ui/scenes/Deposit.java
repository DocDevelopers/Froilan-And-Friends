package io.froilanandfriends.atm.ui.scenes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Deposit extends Application {

    Scene scene;
    Stage window;
    Button depositButton;
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

        //Deposit Amount
        Label depositAmount = new Label("Deposit Amount");
        GridPane.setConstraints(depositAmount, 0, 0);
        TextField setDepositAmount = new TextField();
        setDepositAmount.setPromptText("Enter amount to deposit");
        GridPane.setConstraints(setDepositAmount, 1, 0);

        //Number of Bills
        Label numBills = new Label("How many bills?");
        GridPane.setConstraints(numBills, 0, 1);
        TextField setNumBills = new TextField();
        setNumBills.setPromptText("Enter number of bills");
        GridPane.setConstraints(setNumBills, 1, 1);

        //Deposit Button
        depositButton = new Button("Confirm Deposit");
        GridPane.setConstraints(depositButton, 1, 2);

        depositButton.setOnAction(e -> {
            ErrorMessages.isInt(setDepositAmount, setNumBills, "deposit");
        });

        //Back Button
        backButton = new Button("Go Back");
        GridPane.setConstraints(backButton, 0, 6);
        backButton.setOnAction(e -> {
            //window.setScene();
            System.out.println("Back button Pressed");
        });

        window.setOnCloseRequest(e -> {
            e.consume();                                    //Tells the system to consume the .setOnCloseRequest method, and it will be handled by the closeProgram method.
            ErrorMessages.closeProgram(window);               //Will run closeProgram() if the red 'X' button is clicked.

        });

        layout.getChildren().addAll(depositAmount, setDepositAmount, numBills, setNumBills, depositButton, backButton);
        scene = new Scene(layout, 325, 250);
        window.setScene(scene);
        window.show();

    }


}

