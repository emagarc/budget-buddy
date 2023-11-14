package entities.transaction;

import entities.user.User;
import interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager<T extends Transaction> {
    private List<T> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(User user, Double amount, TransactionCategory category, String date) {
        T transaction = createTransaction(amount, category, date, user);
        user.getTransactions().add(transaction);
        transactions.add(transaction);
    }

    protected List<T> filterTransactionsByUser(User user) {
        return transactions.stream()
                .filter(transaction -> transaction.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public List<T> getUserTransactions(User user) {
        return filterTransactionsByUser(user);
    }

    public T getTransactionById(Integer transactionId) {
        return transactions.stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    public List<T> getAllTransactions() {
        return transactions;
    }


    protected T createTransaction(Double amount, TransactionCategory category, String date, User user) {
        return null;
    }



}
