package entities.income;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IncomeCategory {
    public static final Set<String> CATEGORIES;

    static {
        Set<String> categories = new HashSet<>();
        categories.add("Salary");
        categories.add("Wages");
        categories.add("Bonus");
        categories.add("Commission");
        categories.add("Interest");
        categories.add("Dividends");
        categories.add("Rental Income ");
        categories.add("Investment Income");
        categories.add("Gifts");
        categories.add("Savings");
        categories.add("Royalties");
        categories.add("Capital Gains");
        categories.add("Freelance Income");
        categories.add("Pension");
        categories.add("Annuities");
        categories.add("Sale of Assets");
        categories.add("Refunds");
        categories.add("Inheritance");
        categories.add("Alimony");
        categories.add("Other Income");
        CATEGORIES = Collections.unmodifiableSet(categories);
    }
}
