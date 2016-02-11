package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.ATM;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;



public class RestockTrayScene extends GridPane {
    AtmGuiApplication application;
    final Button button = new Button("Restock Withdrawal Tray");
    final Label label = new Label("Enter the proper amounts being restocked, then click the button to reset the Withdrawal Tray");
    final TextField twenties = new TextField();
    final TextField tens = new TextField();


    public RestockTrayScene(AtmGuiApplication window){
        application = window;
        init();
    }

    private void init() {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(twenties, 0, 1);
        GridPane.setConstraints(tens, 0, 2);
        GridPane.setConstraints(button, 0, 3);
        twenties.setPromptText("Enter twenties");
        tens.setPromptText("Enter tens");


        button.setOnAction(e -> {
            restockTray();
        });


        this.getChildren().addAll(label, button, twenties, tens);


    }
    private void restockTray() {
        int twen = Integer.parseInt(twenties.getText());
        int ten = Integer.parseInt(tens.getText());
        ATM atm = ATM.getATM();
        atm.reloadWithdrawalTray(twen, ten);
        application.loadAdminMenu();
    }
}

