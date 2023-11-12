package entities.user;
import entities.summary.FinancialSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> userMap;

    public UserManager() {
        userMap = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
    }

    public void removeUser(String userName) {
        userMap.remove(userName);
    }

    public User getUserByUsername(String username){
        return userMap.get(username);
    }

    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    // Auth

    public boolean authenticateUser(String userName, String password) {
        User user = userMap.get(userName);
        return user != null && user.getPassword().equals(password);
    }

    public Map<String, FinancialSummary> getFinancialSummaryForAllUsers() {
        Map<String, FinancialSummary> summaryMap = new HashMap<>();
        for (User user : userMap.values()) {
            FinancialSummary summary = new FinancialSummary(user);
            summaryMap.put(user.getUserName(), summary);
        }
        return summaryMap;
    }

}
