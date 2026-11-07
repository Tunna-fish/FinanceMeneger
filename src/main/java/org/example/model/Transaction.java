package org.example.model;

public class Transaction {
    private final int id;
    private final double amount;
    private final String category;
    private final TransactionType type;

    public Transaction(int id, double amount, String category, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.type = type;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public TransactionType getType() { return type; }
}

