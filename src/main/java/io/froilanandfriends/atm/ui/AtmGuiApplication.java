package io.froilanandfriends.atm.ui;

import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.ui.scenes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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


        window.setTitle("International Bank Of Froilan");
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

    public void loadAccountsPage(){ switchStage(new Scene(new UserAccountsScene(this), 600, 600)); }

    public void loadAccount(){
        switchStage(new Scene(new AccountScene(this), 600, 600));
    }

    public void loadDeposit(){
        switchStage(new Scene(new DepositScene(this), 600, 600));
    }

    public void loadLogin(){
        switchStage(new Scene(new LoginScene(this), 600, 600));
    }

    public void loadWithdraw(){
        switchStage(new Scene(new Withdrawal(this), 600, 600));
    }

    public void loadTransfer(){
        switchStage(new Scene(new Transfer(this), 600, 600));
    }

    public void loadTransactions(){
        switchStage(new Scene(new TransactionScene(this), 600, 600));
    }
    public void loadAdminMenu() {
        switchStage(new Scene(new AdminMenuScene(this), 600, 600));
    }
    public void loadCollectDeposits() {
        switchStage(new Scene(new CollectDepositsScene(this), 600, 600));
    }
    public void loadRestockTray() {
        switchStage(new Scene(new RestockTrayScene(this), 600, 600));
    }
    public void loadFlagUser() {
        switchStage(new Scene(new FlagUserScene(this), 600, 600));
    }
    public void loadUnFlagUser() {
        switchStage(new Scene(new UnFlagUserScene(this), 600, 600));
    }

    public void loadAllTransactions(){
        switchStage(new Scene(new ShowAllTransactionsScene(this), 600, 600));
    }

    public void loadAllFlagged(){
        switchStage(new Scene(new ShowAllFlaggedScene(this),600, 600));
    }
    public void closeAccount(){
        removeLastFromStack();
        switchStage(new Scene(new UserAccountsScene(this), 600, 600));

    }

    public void logOut(){
        UserManager.getUserManager().clearUser();
        stack.clear();
        switchStage(new Scene(new LoginScene(this), 600, 600));
    }

    //If page should no longer be available... Remove it from the stack
    private void removeLastFromStack(){
        if(stack.size() > 0 ){
            stack.remove(stack.size()-1);
        }
    }

    public void goBack(){
      loadAccount();
    }

}

