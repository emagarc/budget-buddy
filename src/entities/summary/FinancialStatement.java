package entities.summary;

import entities.expense.ExpenseCategory;
import entities.income.Income;
import entities.expense.Expense;
import entities.user.User;

import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinancialStatement extends FinancialSummary {

    public FinancialStatement(User user) {
        super(user);
    }

    public double getMonthlyAverageExpense() {
        List<Expense> expenses = getUser().getExpenses();
        Map<Integer, Double> monthlyExpenses = expenses.stream()
                .collect(
                        Collectors.groupingBy(
                                e -> Integer.parseInt(e.getDate().split("-")[1]),
                                Collectors.summingDouble(Expense::getAmount)
                        )
                );

        double totalMonthlyExpense = monthlyExpenses.values().stream().mapToDouble(Double::doubleValue).sum();

        return totalMonthlyExpense / monthlyExpenses.size();
    }

    public Map<ExpenseCategory, Double> getCategoryExpensePercentage() {
        List<Expense> expenses = getUser().getExpenses();
        Map<ExpenseCategory, Double> categoryPercentageMap = new HashMap<>();


        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();

        for (ExpenseCategory category : ExpenseCategory.CATEGORIES) {
            double categoryExpense = expenses.stream()
                    .filter(expense -> expense.getCategory().equals(category))
                    .mapToDouble(Expense::getAmount)
                    .sum();
            if (totalExpense > 0) {
                double percentage = (categoryExpense / totalExpense) * 100.0;
                categoryPercentageMap.put(category, percentage);
            } else {
                categoryPercentageMap.put(category, 0.0);
            }
        }

        return categoryPercentageMap;
    }

    public Map<Integer, Double> getYearlySummary() {
        List<Expense> expenses = getUser().getExpenses();
        List<Income> incomes = getUser().getIncomes();

        Map<Integer, Double> yearlyExpenses = expenses.stream()
                .collect(Collectors.groupingBy(
                        e -> LocalDate.parse(e.getDate()).getYear(),
                        Collectors.summingDouble(Expense::getAmount)
                ));

        Map<Integer, Double> yearlyIncomes = incomes.stream()
                .collect(Collectors.groupingBy(
                        i -> LocalDate.parse(i.getDate()).getYear(),
                        Collectors.summingDouble(Income::getAmount)
                ));

        Map<Integer, Double> yearlySummary = new HashMap<>();
        for (int year = Year.now().getValue(); year >= Year.now().getValue() - 4; year--) {
            double totalIncome = yearlyIncomes.getOrDefault(year, 0.0);
            double totalExpense = yearlyExpenses.getOrDefault(year, 0.0);
            yearlySummary.put(year, totalIncome - totalExpense);
        }

        return yearlySummary;
    }

    public double getAccumulateSavings() {
        List<Expense> expenses = getUser().getExpenses();
        List<Income> incomes = getUser().getIncomes();

        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();

        return totalIncome - totalExpense;
    }
}

