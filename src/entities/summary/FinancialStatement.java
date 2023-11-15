package entities.summary;

import entities.expenses.ExpenseCategory;
import entities.incomes.Income;
import entities.expenses.Expense;
import entities.transactions.Transaction;
import entities.user.User;
import exceptions.InsufficientDataException;
import exceptions.CategoryNotFoundException;
import exceptions.NoDataException;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FinancialStatement extends FinancialSummary {

    public FinancialStatement(User user) {
        super(user);
    }

    public double getMonthlyAverageExpense() {
        LocalDate now = LocalDate.now();
        return getMonthlyAverageExpense(now.getYear(), now.getMonthValue());
    }

    public double getMonthlyAverageIncome() {
        LocalDate now = LocalDate.now();
        return getMonthlyAverageIncome(now.getYear(), now.getMonthValue());
    }

    public double getMonthlyAverageExpense(int year, int month) {
        List<Expense> expenses = getUser().getExpenses();

        Set<String> uniqueMonths = expenses.stream()
                .map(expense -> LocalDate.parse(expense.getDate()).format(DateTimeFormatter.ofPattern("yyyy-MM")))
                .collect(Collectors.toSet());

        if (uniqueMonths.size() < 2) {
            throw new InsufficientDataException();
        }

        Map<Integer, Double> monthlyExpenses = expenses.stream()
                .filter(expense -> {
                    LocalDate expenseDate = LocalDate.parse(expense.getDate());
                    return expenseDate.getYear() == year && expenseDate.getMonthValue() == month;
                })
                .collect(
                        Collectors.groupingBy(
                                e -> Integer.parseInt(e.getDate().split("-")[1]),
                                Collectors.summingDouble(Expense::getAmount)
                        )
                );


        double totalMonthlyExpense = monthlyExpenses.values().stream().mapToDouble(Double::doubleValue).sum();

        return totalMonthlyExpense / monthlyExpenses.size();
    }


    public double getMonthlyAverageIncome(int year, int month) {
        List<Income> incomes = getUser().getIncomes();

        Set<String> uniqueMonths = incomes.stream()
                .map(income -> LocalDate.parse(income.getDate()).format(DateTimeFormatter.ofPattern("yyyy-MM")))
                .collect(Collectors.toSet());

        if (uniqueMonths.size() < 2) {
            throw new InsufficientDataException("");
        }

        Map<Integer, Double> monthlyIncomes = incomes.stream()
                .filter(income -> {
                    LocalDate incomeDate = LocalDate.parse(income.getDate());
                    return incomeDate.getYear() == year && incomeDate.getMonthValue() == month;
                })
                .collect(
                        Collectors.groupingBy(
                                e -> Integer.parseInt(e.getDate().split("-")[1]),
                                Collectors.summingDouble(Income::getAmount)
                        )
                );

        double totalMonthlyIncome = monthlyIncomes.values().stream().mapToDouble(Double::doubleValue).sum();

        return totalMonthlyIncome / monthlyIncomes.size();
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
            String categoryName  = transaction.getCategory().getName();
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

        } catch (NoDataException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ExpenseCategory getLargestExpenseCategory(int year, int month) throws CategoryNotFoundException {
        List<Expense> expenses = getUser().getExpenses().stream()
                .filter(expense -> {
                   LocalDate expenseDate = LocalDate.parse(expense.getDate());
                   return expenseDate.getYear() == year && expenseDate.getMonthValue() == month;
                })
                .collect(Collectors.toList());

        if (expenses.isEmpty()) {
            throw new CategoryNotFoundException("No expenses available for the specified month.");
        }

        return expenses.stream()
                .collect(Collectors.groupingBy(Expense::getCategory, Collectors.summingDouble(Expense::getAmount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Map<Integer, Double> getYearlySummary() throws NoDataException {
        List<Expense> expenses = getUser().getExpenses();
        List<Income> incomes = getUser().getIncomes();

        if (expenses.isEmpty() && incomes.isEmpty()) {
            throw new NoDataException("No expense or income data available to generate yearly summary.");
        }

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

    public double getAccumulateSavings() throws NoDataException {
        List<Expense> expenses = getUser().getExpenses();
        List<Income> incomes = getUser().getIncomes();

        if (expenses.isEmpty() && incomes.isEmpty()) {
            throw new NoDataException("No expense or income data available to calculate accumulated savings.");
        }

        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();

        return totalIncome - totalExpense;
    }
}

