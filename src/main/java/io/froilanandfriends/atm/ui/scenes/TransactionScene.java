package io.froilanandfriends.atm.ui.scenes;

import io.froilanandfriends.atm.Transaction;
import io.froilanandfriends.atm.TransactionManager;
import io.froilanandfriends.atm.ui.AtmGuiApplication;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Doc on 2/11/16.
 */
public class TransactionScene extends GridPane {
    final Text transactions = new Text();
    private AtmGuiApplication application;
    private TransactionManager transactionManager = TransactionManager.getTransactionManager();

    public TransactionScene(AtmGuiApplication window){
        application = window;
        try{
            transactionManager.loadTransactions();
        }catch (Exception e){

        }
    }

    private void init(){
        populateTransactions();
        this.setConstraints(transactions, 0,0);
        this.getChildren().add(transactions);
    }

    private void populateTransactions(){
        ArrayList<Transaction> cTransactions = transactionManager.getCurrentAccountTransactions();
        String result = "";
        for(Transaction transaction : cTransactions){
            result += transaction.toString()+"\n";
        }

        transactions.setText(result);
    }
}
