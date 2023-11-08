package entities.user;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> userMap;

    public UserManager() {
        userMap = new HashMap<>();
    }

    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
    }

    public User getUserByUsername(String username){
        return userMap.get(username);
    }

    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

}
