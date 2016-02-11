package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.ATM;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.UserManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by Kyle on 2/11/16.
 */
public class CollectDepositsScene extends GridPane{
    AtmGuiApplication application;
    final Button button = new Button("Collect Deposits");
    final Label label = new Label("Click the button to collect the Deposit Tray");


    public CollectDepositsScene(AtmGuiApplication window){
        application = window;
        init();
    }

    private void init() {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(button, 0, 3);

        button.setOnAction(e -> {
            collectDeposits();
        });


        this.getChildren().addAll(label);


    }
    private void collectDeposits() {
        ATM atm = ATM.getATM();
        atm.emptyDepositTray();
        application.loadAdminMenu();
    }
}
