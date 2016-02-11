package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.ATM;
import io.froilanandfriends.atm.Account;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.TransactionManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Doc on 2/11/16.
 */
public class DepositScene extends GridPane {
    final Label depositAmount = new Label("Deposit Amount");
    final TextField setDepositAmount = new TextField();
    final Button depositButton = new Button("Confirm Deposit");
    final Button backButton = new Button("Go Back");
    AtmGuiApplication application;

    public DepositScene(AtmGuiApplication window){
        application = window;
        init();
    }

    private void init(){
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);

        //Deposit Amount

        this.setConstraints(depositAmount, 0, 0);
        setDepositAmount.setPromptText("Enter amount to deposit");
        this.setConstraints(setDepositAmount, 1, 0);



        //Deposit Button

        this.setConstraints(depositButton, 1, 1);

        depositButton.setOnAction(e -> {
            if(ErrorMessages.isInt(setDepositAmount, "deposit")){
                depositMoney();
            }
        });

        backButton.setOnAction(e -> {
            onBack();
        });

        this.setConstraints(backButton, 0, 6);

        this.getChildren().addAll(depositAmount, setDepositAmount, depositButton, backButton);
    }

    private void depositMoney(){
        AccountManager accountManager = AccountManager.getAccountManager();

        accountManager.deposit(Integer.parseInt(setDepositAmount.getText()));

        ATM atm =ATM.getATM();

        atm.deposit(Integer.parseInt(setDepositAmount.getText()));

        application.loadAccount();
    }

    private void onBack(){
        application.goBack();
    }




}
