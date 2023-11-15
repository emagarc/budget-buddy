import entities.expenses.Expense;
import entities.expenses.ExpenseCategory;
import exceptions.InvalidExpenseException;
import interfaces.expense.ExpenseAmountValidator;
import interfaces.expense.ExpenseAmountValidatorImpl;
import interfaces.expense.ExpenseCalculator;
import interfaces.expense.ExpenseCalculatorImpl;

import java.util.Scanner;

public class BudgetBuddy {

    public static int counter = 1;
    public static void main(String[] args) throws InvalidExpenseException {

        Scanner scanner = new Scanner(System.in);

        int index = 0;
        Double amount;
        boolean isWrongType = false;
        int expensesToEnter = 0;

        ExpenseAmountValidator expenseAmountValidator = new ExpenseAmountValidatorImpl();
        ExpenseCalculator expenseCalculator = new ExpenseCalculatorImpl();

        do {
            System.out.print("Ingrese la cantidad de gastos a registrar: ");
            if (scanner.hasNextInt()) {
                expensesToEnter = scanner.nextInt();
            } else {
                System.out.println("Dato erroneo");
            }

        } while (isWrongType);

        Expense[] expenses = new Expense[expensesToEnter];

        do {
            Expense expense = new Expense();

            System.out.println("Ingrese el monto del gasto " + (index+1) + ": ");
            amount = scanner.nextDouble();

            if (!expenseAmountValidator.notValidAmount(amount)) {
                System.out.println("El monto es valido");
            } else {
                System.out.println("El monto no es valido");
            }

            scanner.nextLine();

            System.out.println("Ingrese la categoria del gasto: ");
            String categoryInput = scanner.nextLine().toLowerCase().trim();

            ExpenseCategory categoryExpense = ExpenseCategory.CATEGORIES.stream()
                            .filter(cat -> cat.getName().equals(categoryInput))
                                    .findFirst()
                                            .orElse(null);
            if (categoryExpense != null) {
                expense.setCategory(categoryExpense);
            } else {
                System.out.println("Categoría no encontrada. Debe elegir una de las categorías existentes.");
            }

            System.out.println("Ingrese la fecha del gasto: (dd/MM/yyyy)");
            String date = scanner.nextLine();

            expense.setId(counter);
            expense.setAmount(amount);
            //expense.setCategory(category);
            expense.setDate(date);

            expenses[index] = expense;

            counter++;
            index++;
        } while (index < expensesToEnter);

        System.out.println("DETALLE DE GASTOS INGRESADOS");
        for (int i = 0; i < expenses.length; i++) {
            System.out.println(expenses[i]);
        }

        System.out.println("Total de gastos ingresados: " + expenseCalculator.calculateTotalExpense(expenses));

    }
}