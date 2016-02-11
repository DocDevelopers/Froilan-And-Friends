package io.froilanandfriends.atm.ui.scenes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Transfer extends Application{
    Stage window;
    Button xferButton;
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
        Label xferAmount = new Label("Transfer Amount");
        GridPane.setConstraints(xferAmount, 0, 0);
        TextField setXferAmount = new TextField();
        setXferAmount.setPromptText("Enter amount to transfer");
        GridPane.setConstraints(setXferAmount, 1, 0);

        //Withdrawal Amount
        Label accntID = new Label("Account to transfer to: ");
        GridPane.setConstraints(xferAmount, 0, 1);
        TextField setAccntID = new TextField();
        setAccntID.setPromptText("Account ID#");
        GridPane.setConstraints(setAccntID, 1, 1);


        //xferButton Button
        xferButton = new Button("Confirm Transfer");
        GridPane.setConstraints(xferButton, 1, 2);
        xferButton.setOnAction(e -> {
            ErrorMessages.isInt(setXferAmount, setAccntID, "transfer");
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



        layout.getChildren().addAll(xferAmount, setXferAmount, accntID, setAccntID,  xferButton, backButton);
        Scene scene = new Scene(layout, 350, 250);
        window.setScene(scene);
        window.show();

    }


}
