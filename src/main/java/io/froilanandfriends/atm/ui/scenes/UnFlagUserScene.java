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
public class UnFlagUserScene extends GridPane{
    AtmGuiApplication application;
    final Button flag = new Button("UnFlag This User");
    final Label label = new Label("Enter the username of the user you want to unflag");
    final Button back = new Button("Back");

    final TextField setUser = new TextField();


    public UnFlagUserScene(AtmGuiApplication window){
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
        GridPane.setConstraints(back, 0, 6);

        setUser.setPromptText("User ID#");

        flag.setOnAction(e -> {
            unFlagUser(setUser);
        });
        back.setOnAction(e -> {
            application.loadAdminMenu();
        });


        this.getChildren().addAll(label, setUser, flag, back);


    }
    private void unFlagUser(TextField setUser) {
        UserManager um = UserManager.getUserManager();
        User toUnFlagUser = um.getUser(setUser.getText());


        if(toUnFlagUser != null){
            um.flagUser(toUnFlagUser);
            application.loadAdminMenu();
        }else{
            ErrorMessages.notNumAlert("This user does not exist!");
            return;
        }

        um.unFlagUser(toUnFlagUser);
        application.loadAdminMenu();
    }
}
