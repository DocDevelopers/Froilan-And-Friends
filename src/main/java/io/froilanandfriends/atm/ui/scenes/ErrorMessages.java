package io.froilanandfriends.atm.ui.scenes;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorMessages{
    static boolean answer;


    public static void notNumAlert(String message) {
        final Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);  //Blocks user from interacting with original window until they deal with this window first.
        window.setTitle("International Bank of Froilan");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closebutton = new Button("Close the Window");

        closebutton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closebutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); //Works with window.initModality, ensures the user closes the alert window before getting back to the original window.
    }

    public static boolean isInt(TextField x, TextField y, String scene) {
        try {
            int xx = Integer.parseInt(x.getText());
            int yy = Integer.parseInt(y.getText());
            System.out.println(xx);
            System.out.println(yy);


            return true;
        } catch (NumberFormatException e) {
            ErrorMessages.notNumAlert("There was an error in the number fields. \n          Please enter your " + scene + ".");
            System.out.println("Error: a number not entered.");
            return false;
        }
    }

    public static boolean isInt(TextField x, String scene) {
        try {
            int xx = Integer.parseInt(x.getText());
            System.out.println(xx);

            return true;
        } catch (NumberFormatException e) {
            ErrorMessages.notNumAlert("There was an error in the number fields. \n          Please enter your " + scene + ".");
            System.out.println("Error: a number not entered.");
            return false;
        }
    }

    public static boolean display(String message) {
        final Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);  //Blocks user from interacting with original window until they deal with this window first.
        window.setTitle("International Bank of Froilan");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button ("Yes");
        Button noButton = new Button ("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); //Works with window.initModality, ensures the user closes the alert window before getting back to the original window.

        return answer;

    }
    public static void closeProgram(Stage window) {
        Boolean answer = display( "Are you sure you want to exit?");
        if(answer) {
            window.close();
        }
    }

}