package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.Account;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.UserMenu;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by Doc on 2/11/16.
 */
public class AccountScene extends GridPane{
    private AtmGuiApplication application;
    private AccountManager accountManager = AccountManager.getAccountManager();
    private Account currentAccount;
    final Text accountHeader = new Text();
    final Text balanceHeader = new Text();
    final Button depositButton = new Button("Deposit");
    final Button withdrawButton = new Button("Withdraw");
    final Button transferButton = new Button("Transfer");
    final Button viewTransButton = new Button("View Transactions");
    final Button closeAccountButton = new Button("Close Account");
    final Button backButton = new Button("Go Back");
    VBox actionsContainer;

    final EventHandler<ActionEvent> depositAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            deposit();
        }
    };

    final EventHandler<ActionEvent> withdrawAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            withdraw();
        }
    };

    final EventHandler<ActionEvent> transferAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            transfer();
        }
    };


    final EventHandler<ActionEvent> closeAccountAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            closeAccount();
        }
    };


    final EventHandler<ActionEvent> viewTransAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            viewTransactions();
        }
    };

    public AccountScene(AtmGuiApplication window){
        application = window;
        currentAccount = accountManager.getCurrentAccount();
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);
        init();
    }

    private void init(){

        backButton.setOnAction( event -> {
            application.goBack();
        });

        ObservableList list = this.getChildren();
        setAccountHeaders();

        depositButton.setOnAction(depositAction);
        withdrawButton.setOnAction(withdrawAction);
        transferButton.setOnAction(transferAction);
        viewTransButton.setOnAction(viewTransAction);
        closeAccountButton.setOnAction(closeAccountAction);

        actionsContainer = new VBox(5.0, depositButton,withdrawButton,transferButton,viewTransButton,closeAccountButton);
        this.setConstraints(accountHeader, 0,0);
        this.setConstraints(balanceHeader, 0,1);
        this.setConstraints(actionsContainer, 0,2);

        list.add(accountHeader);
        list.add(balanceHeader);
        list.add(actionsContainer);

        list.add(backButton);
        this.setConstraints(backButton, 0,3);



    }

    private void setAccountHeaders(){
        String accountString = currentAccount.getAccountType() + " Account #: "+ currentAccount.getId();
        String balanceString =  " Balance: "+currentAccount.getBalance();
        accountHeader.setText(accountString);
        balanceHeader.setText(balanceString);
    }

    private void deposit(){
        application.loadDeposit();

    }

    private void withdraw(){
        application.loadWithdraw();
    }

    private void transfer(){
        application.loadTransfer();
    }

    private void viewTransactions(){
        application.loadTransactions();
    }

    private void closeAccount(){
        AccountManager am = AccountManager.getAccountManager();
        Account currentAccount = am.getCurrentAccount();
        UserManager un = UserManager.getUserManager();
        if(currentAccount.getBalance()!=0){
             boolean close =ErrorMessages.display("Closing your account will withdraw all available funds in the form of a mailed check.  Current Balance: "+currentAccount.getBalance()+" Still want to close?");
            if(!close)
                return;
        }

        boolean confirm =ErrorMessages.display("Are you sure?");

        if(confirm){
            am.deleteAccount(currentAccount.getId());

            if(currentAccount.getBalance() != 0)
                ErrorMessages.notNumAlert("You will receive a check in the mail for "+currentAccount.getBalance()+" in the next 7 business days.");
            application.closeAccount();

        }



    }
}
