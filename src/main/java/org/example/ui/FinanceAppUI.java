package org.example.ui;

import org.example.model.TransactionType;
import org.example.service.FinanceService;

import javax.swing.*;
import java.awt.*;

public class FinanceAppUI extends JFrame {
    private final FinanceService service = new FinanceService();

    private final JTextField amountField;
    private final JTextField categoryField;
    private final JComboBox<TransactionType> typeBox;
    private final JLabel balanceLabel;
    private final JTextArea incomeReportArea;
    private final JTextArea expenseReportArea;

    public FinanceAppUI() {
        setTitle("Personal Finance Meneger");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Сумма:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Категория:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Тип операции:"));
        typeBox = new JComboBox<>(TransactionType.values());
        inputPanel.add(typeBox);

        JButton addButton = new JButton("Добавить транзакцию");
        inputPanel.add(addButton);

        JButton deleteButton = new JButton("Очистить поле учета");
        inputPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        balanceLabel = new JLabel("Текущий баланс: 0.0 руб.");
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(balanceLabel, BorderLayout.NORTH);

        JPanel reportsGridPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        incomeReportArea = new JTextArea();
        incomeReportArea.setEditable(false);
        incomeReportArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JPanel incomePanel = new JPanel(new BorderLayout());
        JLabel incomeHeader = new JLabel("ПРИХОДЫ ПО КАТЕГОРИЯМ");
        incomeHeader.setFont(new Font("Arial", Font.BOLD, 12));
        incomeHeader.setHorizontalAlignment(SwingConstants.CENTER);
        incomePanel.add(incomeHeader, BorderLayout.NORTH);
        incomePanel.add(new JScrollPane(incomeReportArea), BorderLayout.CENTER);

        expenseReportArea = new JTextArea();
        expenseReportArea.setEditable(false);
        expenseReportArea.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JPanel expensePanel = new JPanel(new BorderLayout());
        JLabel expenseHeader = new JLabel("РАСХОДЫ ПО КАТЕГОРИЯМ");
        expenseHeader.setFont(new Font("Arial", Font.BOLD, 12));
        expenseHeader.setHorizontalAlignment(SwingConstants.CENTER);
        expensePanel.add(expenseHeader, BorderLayout.NORTH);
        expensePanel.add(new JScrollPane(expenseReportArea), BorderLayout.CENTER);

        reportsGridPanel.add(incomePanel);
        reportsGridPanel.add(expensePanel);

        centerPanel.add(reportsGridPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        deleteButton.addActionListener(_ -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Вы уверены, что хотите полностью очистить историю транзакций?",
                    "Подтверждение удаления",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                service.clearAll();
                updateUI();

                amountField.setText("");
                categoryField.setText("");
            }
        });

        updateUI();
    }

    private void updateUI() {
        balanceLabel.setText("Текущий баланс: " + service.getBalance() + " руб.");

        StringBuilder reportExpense = new StringBuilder();
        service.getExpensesByCategory().forEach((category, sum) -> reportExpense.append(String.format("%-15s: %.2f руб.\n", category, sum)));

        StringBuilder reportIncome = new StringBuilder();
        service.getIncomesByCategory().forEach((category, sum) -> reportIncome.append(String.format("%-15s: %.2f руб.\n", category, sum)));

        incomeReportArea.setText(reportIncome.toString());
        expenseReportArea.setText(reportExpense.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinanceAppUI().setVisible(true));
    }
}
