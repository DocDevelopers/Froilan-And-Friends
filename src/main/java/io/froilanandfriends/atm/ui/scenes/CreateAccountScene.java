package io.froilanandfriends.atm.ui.scenes;

/**
 * Created by Doc on 2/10/16.
 */
import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Doc on 2/10/16.
 */
public class CreateAccountScene extends GridPane {

    private AtmGuiApplication application;
    //Defining the First Name text field
    final TextField firstName = new TextField();
    final TextField lastName = new TextField();
    final TextField email = new TextField();
    final TextField username = new TextField();
    final TextField pin = new TextField();
    final TextField securityQuestion = new TextField();
    final TextField securityAnswer = new TextField();
    final Button createButton = new Button();
    final Text label = new Text();
    final Text errorText = new Text();

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    final EventHandler<ActionEvent> createEvent = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            createAccount();
        }
    };

    public CreateAccountScene(AtmGuiApplication atmGuiApplication){
        application = atmGuiApplication;
        init();
    }

    public void createAccount(){
        if(validateInput()){
            UserManager um = UserManager.getUserManager();
            try {
                um.addUser(username.getText(), firstName.getText(), lastName.getText(), email.getText(), Integer.parseInt(pin.getText()), securityQuestion.getText(), securityAnswer.getText());
            }catch (Exception e){
                errorText.setText("Something went worng! Please call (267)-346-2003. error: DB ERROR");
                return;
            }

            um.setCurrentUser(um.getUser(username.getText()));
            application.loadAccountsPage();
        }


    }

    private boolean validateInput(){
        int numberPin = 0;
        if(username.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty() || securityQuestion.getText().isEmpty() || securityAnswer.getText().isEmpty()){
            errorText.setText("Please fill in all fields");
            return false;
        }

        //Make sure pin is an int
        try{
            numberPin = Integer.parseInt(pin.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please enter a valid pin! Valid PINS are from 4-7 digits");
            return false;
        }

        if(numberPin<1000||numberPin>9999999){
            errorText.setText("Valid PINS are from 4-7 digits");
            return false;
        }

        if(!validate(email.getText())){
            errorText.setText("Please enter a valid email");
            return false;
        }
        return true;
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }



    public void init(){
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);
        //SET LABELS
        label.setText("Fill out the form below to create an account");

        username.setPromptText("Enter your username");
        pin.setPromptText("Enter your pin");
        this.setConstraints(pin,0,4);
        this.setConstraints(label, 0, 0);
        firstName.setPromptText("Enter your first name");
        this.setConstraints(firstName, 0, 1);
        lastName.setPromptText("Enter your last name");
        this.setConstraints(lastName, 1, 1);
        email.setPromptText("Enter your email");
        this.setConstraints(email, 0, 2);
        username.setPromptText("Enter a username");
        this.setConstraints(username, 1, 2);
        securityQuestion.setPromptText("Enter your security question");
        this.setConstraints(securityQuestion, 0,3);
        securityAnswer.setPromptText("Enter your security answer");
        this.setConstraints(securityAnswer, 1,3);
        createButton.setText("Create Account");
        createButton.setOnAction(createEvent);
        this.setConstraints(createButton, 0,5);
        this.setConstraints(errorText, 0,6);

        ObservableList list = this.getChildren();
        list.add(label);
        list.add(firstName);
        list.add(lastName);
        list.add(email);
        list.add(username);
        list.add(securityQuestion);
        list.add(securityAnswer);
        list.add(createButton);
        list.add(pin);
        list.add(errorText);

    }

}