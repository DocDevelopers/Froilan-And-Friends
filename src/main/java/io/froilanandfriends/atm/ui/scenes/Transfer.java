package io.froilanandfriends.atm.ui.scenes;
import io.froilanandfriends.atm.ATM;
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


public class Transfer extends GridPane {
    final Button xferButton = new Button("Confirm Transfer");
    final Button backButton = new Button("Go Back");
    final Label xferAmount = new Label("Transfer Amount");
    final Label accntID = new Label("Account to transfer to: ");
    final TextField setXferAmount = new TextField();
    final TextField setAccntID = new TextField();
    AtmGuiApplication application;


    public Transfer(AtmGuiApplication window) {
        application = window;
        init();
    }


    private void init() {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);

        //Xfer Amount
        GridPane.setConstraints(xferAmount, 0, 0);
        setXferAmount.setPromptText("Enter amount to transfer");
        GridPane.setConstraints(setXferAmount, 1, 0);

        //Account to Xfer to
        GridPane.setConstraints(accntID, 0, 1);
        setAccntID.setPromptText("Account ID#");
        GridPane.setConstraints(setAccntID, 1, 1);


        //xferButton Button
        GridPane.setConstraints(xferButton, 1, 2);
        xferButton.setOnAction(e -> {
            //if (ErrorMessages.isInt(setXferAmount, setAccntID, "transfer")) {
                xferMoney();
            //}
        });


        //Back Button
        GridPane.setConstraints(backButton, 0, 6);
        backButton.setOnAction(e -> {
            onBack();
        });


        this.getChildren().addAll(xferAmount, setXferAmount, accntID, setAccntID, xferButton, backButton);

    }
    private void xferMoney(){
        AccountManager accountManager = AccountManager.getAccountManager();
        long accntNum = Long.parseLong(setAccntID.getText());
        long xferAmount = Integer.parseInt(setXferAmount.getText());

      if ((accountManager.getCurrentAccount().getBalance() < xferAmount)) {
            ErrorMessages.notNumAlert("You do not have sufficient funds to transfer this amount.");
        } else {
          accountManager.transfer(accntNum, xferAmount);
        }
        application.loadAccount();
    }

    private void onBack(){
        application.goBack();
    }


}
