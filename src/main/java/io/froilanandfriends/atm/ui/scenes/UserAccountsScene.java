package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.Account;
import io.froilanandfriends.atm.AccountManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Doc on 2/11/16.
 */
public class UserAccountsScene extends GridPane {
    private AtmGuiApplication application;
    private AccountManager accountManager = AccountManager.getAccountManager();
    final ComboBox comboBox = new ComboBox();
    final Button createAccount = new Button("Create Bank Account");
    final Button viewAccount = new Button("View Account");
    final Button logout = new Button("Logout");

    final Text label = new Text();

    final Text errorText = new Text();

    EventHandler<ActionEvent> createAccountEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            createAccount();
        }
    };

    EventHandler<ActionEvent> viewAccountEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            viewAccount();
        }
    };

    final ToggleGroup toggleGroup = new ToggleGroup();
    //A checkbox with a string caption
    final RadioButton checkingBox = new RadioButton("Checking");
    final RadioButton savingsBox = new RadioButton("Savings");
    final RadioButton businessBox = new RadioButton("Business");
    private static boolean firstStart = true;
    public UserAccountsScene(AtmGuiApplication window){
        application = window;
        if(firstStart) {


            try {

                accountManager.loadAccounts();
            } catch (Exception e) {

            }
            firstStart = false;
        }
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(5);
        this.setHgap(5);
        init();
    }

    public void init(){

        logout.setOnAction(event -> {
            application.logOut();
        });

        this.setConstraints(logout, 1, 3);
        checkingBox.setToggleGroup(toggleGroup);
        savingsBox.setToggleGroup(toggleGroup);
        businessBox.setToggleGroup(toggleGroup);

        ObservableList list = this.getChildren();

        list.add(logout);
        if(getUsersAccounts() != null && getUsersAccounts().size() > 0){
            label.setText("Please select your account from the drop-down or create a new one");
            populateComboBox();
            this.setConstraints(comboBox, 0, 1);
            list.add(comboBox);

            this.setConstraints(viewAccount, 1, 1);

            list.add(viewAccount);

            viewAccount.setOnAction(viewAccountEvent);
        }
        else
            label.setText("It seems like you don't have any accounts. Please create one below");

        this.setConstraints(label, 0,0);

        HBox box = new HBox(checkingBox,savingsBox, businessBox);
        this.setConstraints(box, 0, 2);
        list.add(box);
        this.setConstraints(createAccount, 0, 3);
        this.setConstraints(errorText, 0,5);

        list.add(createAccount);
        list.add(label);
        list.add(errorText);

        createAccount.setOnAction(createAccountEvent);





    }

    private void createAccount(){
        if(getSelectedAccountType() == null)
            return;
        else {
            accountManager.createAccount(getSelectedAccountType());
            //Switch account to last one created.
            accountManager.switchAccount(accountManager.getAllAccounts().get(accountManager.getAllAccounts().size() - 1).getId());

            application.loadAccount();
        }
    }

    private void viewAccount(){
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();

        //Get the id of the selected account and switch to that account
        if(selectedIndex == -1){
            errorText.setText("Please select an account.");
            return;
        }


        accountManager.switchAccount(getUsersAccounts().get(selectedIndex).getId());

        application.loadAccount();
    }

    private Account.AccountType getSelectedAccountType(){
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
        String selectedName;
        try {
             selectedName = selected.getText();
        }catch (NullPointerException e){
            errorText.setText("Please select an account type");
            return null;
        }

        switch (selectedName){
            case "Checking":
                return Account.AccountType.CHECKING;
            case "Savings":
                return Account.AccountType.SAVINGS;
            case "Business":
                return Account.AccountType.BUSINESS;
            default:
                errorText.setText("Please select an account type");
                break;


        }

        return null;
    }



    private void populateComboBox(){
        ObservableList list = comboBox.getItems();
        ArrayList<Account> accounts = getUsersAccounts();

        for(Account account : accounts){
            String accountName = account.getAccountType() + " Account #: "+ account.getId();
            list.add(accountName);

        }
    }
    public ArrayList<Account> getUsersAccounts(){
        return accountManager.getCurrentUsersAccounts();
    }
}
