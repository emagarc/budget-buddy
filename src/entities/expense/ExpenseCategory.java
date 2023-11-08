package entities.expense;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ExpenseCategory {
    public static final Set<String> CATEGORIES;

    static {
        Set<String> categories = new HashSet<>();
        categories.add("Groceries");
        categories.add("Rent");
        categories.add("Utilities");
        categories.add("Transportation");
        categories.add("Dining Out");
        categories.add("Entertainment");
        categories.add("Clothing");
        categories.add("Healthcare");
        categories.add("Education");
        categories.add("Travel");
        categories.add("Insurance");
        categories.add("Savings");
        categories.add("Gifts and Donations");
        categories.add("Personal Care");
        categories.add("Home Improvement");
        categories.add("Electronics");
        categories.add("Hobbies");
        categories.add("Pet Care");
        categories.add("Taxes");
        categories.add("Miscellaneous");
        CATEGORIES = Collections.unmodifiableSet(categories);
    }
}
