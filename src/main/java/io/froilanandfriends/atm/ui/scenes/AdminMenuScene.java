package io.froilanandfriends.atm.ui.scenes;


import io.froilanandfriends.atm.ATM;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class AdminMenuScene extends GridPane {

    private AccountManager accountManager = AccountManager.getAccountManager();
    final Button collectDeposits = new Button("Collect Deposits");
    final Button restockTray = new Button("Restock Withdrawal Tray");
    final Button trayStatus = new Button("Withdrawal Tray Status");
    final Button flagUser = new Button("Flag User");
    final Button unFlagUser = new Button("UnFlag User");
    final Button userLoginMenu = new Button("User Login Menu");
    final Button showTrans = new Button("Show Transactions");
    final Button showFlagged = new Button("Show Flagged Users");
    final Label adminMessage = new Label("ADMIN Menu");

    AtmGuiApplication application;

    public AdminMenuScene (AtmGuiApplication window) {
        application = window;
        init();
    }


    private void init() {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);


        GridPane.setConstraints(adminMessage, 0, 0);

        GridPane.setConstraints(collectDeposits, 0, 1);
        collectDeposits.setOnAction(e -> {
            application.loadCollectDeposits();

        });
        GridPane.setConstraints(restockTray, 0, 2);
        restockTray.setOnAction(e -> {
            application.loadRestockTray();

        });
        GridPane.setConstraints(trayStatus, 0, 3);
        trayStatus.setOnAction(e -> {

            ATM atm = ATM.getATM();
            HashMap<Integer, Integer> tray = atm.getWithdrawlTray();

            int twenties = tray.get(20);
            int tens = tray.get(10);

            ErrorMessages.notNumAlert("Tray Status: Twenties : "+ twenties +" Tens : "+ tens+ " Total Bills : "+(twenties+tens)+"\n MAX CAPACITY: 2,000 BILLS");

        });
        GridPane.setConstraints(flagUser, 0, 4);
        flagUser.setOnAction(e -> {
            application.loadFlagUser();

        });

        GridPane.setConstraints(unFlagUser, 0, 5);
        unFlagUser.setOnAction(e -> {
            application.loadUnFlagUser();


        });
        GridPane.setConstraints(userLoginMenu, 0, 6);
        userLoginMenu.setOnAction(e -> {
            application.loadLogin();

        });

        GridPane.setConstraints(showTrans, 0, 7);
        showTrans.setOnAction(event -> {
            application.loadAllTransactions();
        });

        GridPane.setConstraints(showFlagged, 0, 8);
        showFlagged.setOnAction(event -> {
            application.loadAllFlagged();
        });




        this.getChildren().addAll(adminMessage, collectDeposits, restockTray, trayStatus, flagUser, unFlagUser, userLoginMenu, showTrans, showFlagged);

    }






}
