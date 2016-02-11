package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.Authenticator;
import io.froilanandfriends.atm.User;
import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by Doc on 2/10/16.
 */
public class LoginScene extends GridPane{
    private AtmGuiApplication mApplication;
    final TextField username = new TextField();
    final TextField pin = new TextField();
    final Button signIn = new Button("Sign In");
    final Button createAccount = new Button("Create Account");
    final Text label1 = new Text("Welcome to the international bank of froilan");
    final Text label2 = new Text("or");
    final Text errorText = new Text();

    final EventHandler<ActionEvent> createAccountEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            loadCreateAccount();
        }
    };

    final EventHandler<ActionEvent> loginEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            login();
        }
    };

    public LoginScene(AtmGuiApplication window){
        mApplication = window;
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);

        init();
    }


    public void init(){
        ObservableList list = this.getChildren();

        this.setConstraints(label1, 0, 0);
        username.setPromptText("username");
        this.setConstraints(username, 0, 1);

        pin.setPromptText("PIN");
        this.setConstraints(pin, 1, 1);

        this.setConstraints(signIn, 2, 1);

        this.setConstraints(label2, 0, 2);
        this.setConstraints(createAccount, 1, 2);
        createAccount.setOnAction(createAccountEvent);

        this.setConstraints(errorText, 0, 3);

        list.add(label1);
        list.add(label2);
        list.add(errorText);
        list.add(username);
        list.add(pin);
        list.add(signIn);
        signIn.setOnAction(loginEvent);
        list.add(createAccount);

    }

    private void login(){
        int numberPin = 0;
        //Make sure fields are not empty
        if(username.getText().isEmpty() || pin.getText().isEmpty()){
            errorText.setText("Please fill in both fields!");
            return;
        }

        //Make sure pin is an int
        try{
            numberPin = Integer.parseInt(pin.getText());
        }catch (NumberFormatException e){
            errorText.setText("Please enter a valid pin! Valid PINS are from 4-7 digits");
            return;
        }

        if(numberPin<1000||numberPin>9999999){
            errorText.setText("Valid PINS are from 4-7 digits");
            return;
        }

        Authenticator auth = Authenticator.getAuthenticator();
        boolean authenticated = auth.authenticate(username.getText().toString(), numberPin);

        if(authenticated){

            UserManager um = UserManager.getUserManager();
            um.setCurrentUser(um.getUser(username.getText()));
            loadUserAccounts();
        }

        else{
            errorText.setText("Login failed.");
            return;
        }
    }


    //Add user to system
    private void loadCreateAccount(){
        mApplication.loadCreateAccount();
    }

    //Show them their accounts
    private void loadUserAccounts(){
        mApplication.loadAccountsPage();
    }

}
