package classes;
import java.util.List;

public class Report {
    private User user;
    private List<Expense> expenses;
    private String reportTitle;
    private String reportDescription;

    public Report(User user, List<Expense> expenses, String reportTitle, String reportDescription) {
        this.user = user;
        this.expenses = expenses;
        this.reportTitle = reportTitle;
        this.reportDescription = reportDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }
}
