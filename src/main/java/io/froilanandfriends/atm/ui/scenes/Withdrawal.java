package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.ATM;
import io.froilanandfriends.atm.Account;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Withdrawal extends GridPane {

    final Button withdrawalButton = new Button("Confirm Withdrawal");
    final Button backButton = new Button("Go Back");
    final Label withdrawalAmount = new Label("Withdrawal Amount");
    final TextField setWithdrawalAmount = new TextField();
    AtmGuiApplication application;


    public Withdrawal(AtmGuiApplication window){
        application = window;
        init();
    }

    private void init() {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);

        //Withdrawal Amount
        this.setConstraints(withdrawalAmount, 0, 0);
        setWithdrawalAmount.setPromptText("Enter amount to withdraw");
        this.setConstraints(setWithdrawalAmount, 1, 0);


        //Withdrawal Button
        this.setConstraints(withdrawalButton, 1, 1);

        withdrawalButton.setOnAction(e -> {
            if(ErrorMessages.isInt(setWithdrawalAmount, "withdrawal")) {
                withdrawMoney();
            }
        });

        //Back Button
        this.setConstraints(backButton, 0, 7);
        backButton.setOnAction(e -> {
            onBack();
        });

        this.getChildren().addAll(withdrawalAmount, setWithdrawalAmount, withdrawalButton, backButton);

    }

    private void withdrawMoney(){
        AccountManager accountManager = AccountManager.getAccountManager();
        ATM atm = ATM.getATM();
        int amount = Integer.parseInt(setWithdrawalAmount.getText());

        if (!(atm.getATMBalance() <= amount) && !(accountManager.getCurrentAccount().getBalance() <= amount)) {
            atm.withdraw(amount);
            accountManager.withdrawl(amount);
        } else if ((accountManager.getCurrentAccount().getBalance() < amount) &&  !(atm.getATMBalance() < amount)) {
            ErrorMessages.notNumAlert("You do not have sufficient funds to withdraw this amount.");
        } else if (!(accountManager.getCurrentAccount().getBalance() < amount) &&  (atm.getATMBalance() < amount)) {
            ErrorMessages.notNumAlert("The atm does not have sufficient funds to withdraw this amount.");
        } else {
            ErrorMessages.notNumAlert("This ATM is out of service. Please try again later.");
        }
        application.loadAccount();

    }

    private void onBack(){
        application.goBack();
    }


}

