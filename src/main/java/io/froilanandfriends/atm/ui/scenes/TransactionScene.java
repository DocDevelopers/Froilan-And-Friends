package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.Transaction;
import io.froilanandfriends.atm.TransactionManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Doc on 2/11/16.
 */
public class TransactionScene extends GridPane {
    private static boolean isFirst = true;
    final Text transactions = new Text();
    final Button backButton = new Button("Go Back");
    private AtmGuiApplication application;
    private TransactionManager transactionManager = TransactionManager.getTransactionManager();

    public TransactionScene(AtmGuiApplication window){
        application = window;
        if(isFirst) {


            try {
                transactionManager.loadTransactions();
            } catch (Exception e) {

            }
            isFirst = false;
        }

        init();
    }

    private void init(){
        populateTransactions();
        this.setConstraints(transactions, 0,1);
        this.setConstraints(backButton, 0,0);

        this.getChildren().addAll(transactions,backButton);

        backButton.setOnAction( event -> {
            application.goBack();
        });
    }

    private void populateTransactions(){
        ArrayList<Transaction> cTransactions = transactionManager.getCurrentAccountTransactions();
        String result = "";
        for(Transaction transaction : cTransactions){
            result += "Transaction - ID:"+transaction.getId()+" Type: "+transaction.getTransactionType()+ " Amount: "+ transaction.getAmount() + " Date: "+ transaction.getDate()+"\n";
        }

        System.out.print(cTransactions);
        transactions.setText(result);
    }
}
