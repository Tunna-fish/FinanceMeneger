package org.example.model;

public enum TransactionType {
    INCOME("Приход"),
    EXPENSE("Расход");

    private final String title;

    TransactionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}