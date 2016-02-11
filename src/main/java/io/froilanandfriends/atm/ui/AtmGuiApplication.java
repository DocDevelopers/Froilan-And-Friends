package io.froilanandfriends.atm.ui;

import io.froilanandfriends.atm.ui.scenes.AccountScene;
import io.froilanandfriends.atm.ui.scenes.CreateAccountScene;
import io.froilanandfriends.atm.ui.scenes.LoginScene;
import io.froilanandfriends.atm.ui.scenes.UserAccountsScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Doc on 2/10/16.
 */
public class AtmGuiApplication extends Application{
    private Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("International Bank Of Frolian");
        switchStage(new Scene(new LoginScene(this), 600, 600));

        window.show();
        window.setAlwaysOnTop(true);

    }

    private void switchStage(Scene newScene){

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

    }

    public void loadWithdraw(){

    }

    public void loadTransfer(){

    }

    public void loadTransactions(){

    }

    public void closeAccount(){

    }

}

