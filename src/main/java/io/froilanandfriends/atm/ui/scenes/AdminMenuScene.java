package io.froilanandfriends.atm.ui.scenes;


import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AdminMenuScene extends GridPane {

    private AccountManager accountManager = AccountManager.getAccountManager();
    final Button collectDeposits = new Button("Collect Deposits");
    final Button restockTray = new Button("Restock Withdrawal Tray");
    final Button trayStatus = new Button("Withdrawal Tray Status");
    final Button flagUser = new Button("Flag User");
    final Button unFlagUser = new Button("UnFlag User");
    final Button userLoginMenu = new Button("User Login Menu");
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





        this.getChildren().addAll(adminMessage, collectDeposits, restockTray, trayStatus, flagUser, unFlagUser, userLoginMenu);

    }






}
