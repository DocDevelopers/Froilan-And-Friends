package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.ATM;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.User;
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
public class FlagUserScene extends GridPane{
    AtmGuiApplication application;
    final Button flag = new Button("Flag This User");
    final Label label = new Label("Enter the ID of the user you want to flag");
    final TextField setUser = new TextField();


    public FlagUserScene(AtmGuiApplication window){
        application = window;
        init();
    }

    private void init() {

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(20);
        this.setHgap(10);

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(setUser, 0, 2);
        GridPane.setConstraints(flag, 0, 3);
        setUser.setPromptText("User ID#");

        flag.setOnAction(e -> {
            flagUser(setUser);
        });


        this.getChildren().addAll(label, setUser, flag);


    }
    private void flagUser(TextField setUser) {
        UserManager um = UserManager.getUserManager();
        User toFlagUser = um.getUserByID(Integer.parseInt(setUser.getText()));
        um.flagUser(toFlagUser);
        application.loadAdminMenu();
    }
}
