package org.example.service;

import org.example.model.Transaction;
import org.example.model.TransactionType;
import org.example.repository.DataBaseManager;

import java.util.List;

public class FinanceService {
    private final DataBaseManager dbManager = new DataBaseManager();

    public void addTransaction(double amount, String category, TransactionType type) {
        dbManager.addTransaction(amount, category, type.name());
    }

    public List<Transaction> getTransactions() {
        return dbManager.getAllTransactions();
    }

    public void clearAll() {
        dbManager.clearAllTransactions();
    }

    public double getBalance() {
        List<Transaction> all = getTransactions();
        double income = all.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount).sum();

        double expense = all.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount).sum();

        return income - expense;
    }
}
