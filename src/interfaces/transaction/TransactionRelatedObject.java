package interfaces.transaction;

import entities.user.User;

public interface TransactionRelatedObject {
    Integer getId();
    Double getAmount();
    TransactionCategory getCategory();
    String getDate();
    User getUser();
}
