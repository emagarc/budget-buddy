package main.entities.summary;

import main.entities.expenses.ExpenseCategory;
import main.entities.incomes.Income;
import main.entities.expenses.Expense;
import main.entities.transactions.Transaction;
import main.entities.user.User;
import main.exceptions.InsufficientDataException;
import main.exceptions.NoDataException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinancialStatement extends FinancialSummary {

    public FinancialStatement(User user) {
        super(user);
    }

    private void validateData(List<List<?>> dataList) {
        for (List<?> data : dataList) {
            if (data.isEmpty()) {
                throw new NoDataException("No data available for the specified period.");
            }
        }
    }

    private double calculateAverage(Map<Integer, Double> monthlyData) {
        double totalMonthlyData = monthlyData.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalMonthlyData / monthlyData.size();
    }


    public double getMonthlyAverageExpense(int year, int month) {
        List<Expense> expenses = getUser().getExpenses();
        validateData(List.of(expenses));

        Map<Integer, Double> monthlyExpenses = expenses.stream()
                .filter(expense -> LocalDate.parse(expense.getDate()).getYear() == year &&
                        LocalDate.parse(expense.getDate()).getMonthValue() == month)
                .collect(Collectors.groupingBy(
                        e -> LocalDate.parse(e.getDate()).getDayOfMonth(),
                        Collectors.summingDouble(Expense::getAmount)
                ));

        return calculateAverage(monthlyExpenses);
    }


    public double getMonthlyAverageIncome(int year, int month) {
        List<Income> incomes = getUser().getIncomes();
        validateData(List.of(incomes));

        Map<Integer, Double> monthlyIncomes = incomes.stream()
                .filter(income -> LocalDate.parse(income.getDate()).getYear() == year &&
                        LocalDate.parse(income.getDate()).getMonthValue() == month)
                .collect(Collectors.groupingBy(
                        e -> LocalDate.parse(e.getDate()).getDayOfMonth(),
                        Collectors.summingDouble(Income::getAmount)
                ));

        return calculateAverage(monthlyIncomes);
    }

    public double calculateExpensePercentageToIncome() {
        try {
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

            return calculateExpensePercentageToIncome(currentYear, currentMonth);
        } catch (InsufficientDataException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public double calculateExpensePercentageToIncome(int year, int month) {
        try {
            double monthlyIncome = getMonthlyAverageIncome(year, month);
            double monthlyExpenses = getMonthlyAverageExpense(year, month);

            if (Double.isNaN(monthlyIncome) || Double.isNaN(monthlyExpenses) || monthlyIncome == 0.0) {
                throw new InsufficientDataException();
            }

            return (monthlyExpenses / monthlyIncome) * 100.0;
        } catch (InsufficientDataException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Map<String, Map<String, Double>> getDetailedCategorySummary() {
        try {
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

            return getDetailedCategorySummary(currentYear, currentMonth);
        } catch (InsufficientDataException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Map<String, Map<String, Double>> getDetailedCategorySummary(int year, int month) {
        Map<String, Map<String, Double>> categorySummary = new HashMap<>();

        List<Transaction> transactions = getTransactionsByMonth(year, month);

        for (Transaction transaction : transactions) {
            String categoryType = (transaction instanceof Income) ? "Income" : "Expense";
            String categoryName = transaction.getCategory().getName();
            double amount = transaction.getAmount();

            categorySummary.computeIfAbsent(categoryType, k -> new HashMap<>())
                    .merge(categoryName, amount, Double::sum);
        }

        if (categorySummary.isEmpty()) {
            throw new NoDataException();
        }

        return categorySummary;
    }

    public Map<ExpenseCategory, Double> getCategoryExpensePercentage() {
        try {
            List<Expense> expenses = getUser().getExpenses();
            validateData(List.of(expenses));

            double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();

            Map<ExpenseCategory, Double> categoryPercentageMap = new HashMap<>();
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

        } catch (NoDataException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}

