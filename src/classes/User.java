package classes;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public User(String name, String lastName, String email, String password, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
