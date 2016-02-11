package io.froilanandfriends.atm.ui;

import io.froilanandfriends.atm.ui.scenes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Doc on 2/10/16.
 */
public class AtmGuiApplication extends Application{
    private Stage window;
    private ArrayList<Scene> stack = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        window.setOnCloseRequest(e -> {
            e.consume();                                    //Tells the system to consume the .setOnCloseRequest method, and it will be handled by the closeProgram method.
            ErrorMessages.closeProgram(window);               //Will run closeProgram() if the red 'X' button is clicked.

        });

        window.setTitle("International Bank Of Frolian");
        switchStage(new Scene(new LoginScene(this), 600, 600));

        window.show();

    }

    private void switchStage(Scene newScene){
        stack.add(newScene);
        window.setScene(newScene);
    }

    public void loadCreateAccount(){
        switchStage(new Scene(new CreateAccountScene(this), 600, 600));
    }

    public void loadAccountsPage(){
        switchStage(new Scene(new UserAccountsScene(this), 600, 600));
    }
    public void loadAccount(){
        switchStage(new Scene(new AccountScene(this), 600, 600));
    }

    public void loadDeposit(){
        switchStage(new Scene(new DepositScene(this), 600, 600));
    }

    public void loadWithdraw(){

    }

    public void loadTransfer(){

    }

    public void loadTransactions(){

    }

    public void closeAccount(){

    }

    public void goBack(){
        if(stack.size() > 1){
            //Show the page before the one we are on
            Scene onTop = stack.get(stack.size()-2);
            //Remove the top page
            stack.remove(stack.size()-1);
            window.setScene(onTop);
        }
    }

}

