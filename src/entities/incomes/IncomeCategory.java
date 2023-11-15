package entities.incomes;
import interfaces.transaction.TransactionCategory;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IncomeCategory implements TransactionCategory {
    public static final Set<IncomeCategory> CATEGORIES;

    static {
        Set<IncomeCategory> categories = new HashSet<>();
        categories.add(new IncomeCategory("Salary"));
        categories.add(new IncomeCategory("Wages"));
        categories.add(new IncomeCategory("Bonus"));
        categories.add(new IncomeCategory("Commission"));
        categories.add(new IncomeCategory("Interest"));
        categories.add(new IncomeCategory("Dividends"));
        categories.add(new IncomeCategory("Rental Income"));
        categories.add(new IncomeCategory("Gifts"));
        categories.add(new IncomeCategory("Savings"));
        categories.add(new IncomeCategory("Capital Gains"));
        categories.add(new IncomeCategory("Freelance Income"));
        categories.add(new IncomeCategory("Pension"));
        categories.add(new IncomeCategory("Annuities"));
        categories.add(new IncomeCategory("Sale of Assets"));
        categories.add(new IncomeCategory("Refunds"));
        categories.add(new IncomeCategory("Inheritance"));
        categories.add(new IncomeCategory("Alimony"));
        categories.add(new IncomeCategory("Other Income"));
        CATEGORIES = Collections.unmodifiableSet(categories);
    }

    private String name;

    public IncomeCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomeCategory that = (IncomeCategory) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
