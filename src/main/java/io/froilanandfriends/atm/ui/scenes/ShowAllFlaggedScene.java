package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.*;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Doc on 2/11/16.
 */
public class ShowAllFlaggedScene extends GridPane {
    private static boolean isFirst = true;
    final Text transactions = new Text();
    final Button backButton = new Button("Go Back");
    private AtmGuiApplication application;
    private UserManager userManager = UserManager.getUserManager();

    public ShowAllFlaggedScene(AtmGuiApplication window){
        application = window;
        if(isFirst) {


            try {
                userManager.loadUsers();
            } catch (Exception e) {

            }
            isFirst = false;
        }

        init();
    }

    private void init(){
        populateFlagged();
        this.setConstraints(transactions, 0,1);
        this.setConstraints(backButton, 0,0);

        this.getChildren().addAll(transactions,backButton);

        backButton.setOnAction( event -> {
            application.loadAdminMenu();
        });
    }

    private void populateFlagged(){
        ArrayList<User>  users = userManager.getAllUsers();
        ArrayList<User> flaggedUsers = new ArrayList<>();

        for(User user : users){
            if(user.isFlagged()){
                flaggedUsers.add(user);
            }
        }

        String result = "";
        for(User user : flaggedUsers){
            result += "User - ID:"+user.getUserID()+" Name: "+user.getFirstName()+" "+ user.getLastName()+ " Email: "+ user.getEmail()+"\n";
        }


        transactions.setText(result);
    }
}
