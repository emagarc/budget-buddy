package entities.transactions;

import entities.user.User;
import interfaces.transaction.TransactionCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager<T extends Transaction> {
    private final List<T> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
    }

    protected T createTransaction(Double amount, TransactionCategory category, String date, User user) {
        throw new UnsupportedOperationException("Subclasses must implement createTransaction");
    }

    public T getTransactionById(Integer transactionId) {
        return transactions.stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst()
                .orElse(null);
    }

    protected List<T> filterTransactionsByUser(User user) {
        return transactions.stream()
                .filter(transaction -> transaction.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public T removeTransaction(Integer transactionId) {
        T removedTransaction = transactions.stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst()
                .orElse(null);

        transactions.removeIf(transaction -> transaction.getId().equals(transactionId));

        return removedTransaction;
    }

    public List<T> getUserTransactions(User user) {
        return filterTransactionsByUser(user);
    }

    public List<T> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

}
