public class MonthlyConstructor {
    public String month;
    public String title;
    public boolean isExpense;
    public int quantity;
    public int priceOne;

    public MonthlyConstructor(String month, String title, boolean isExpense, int quantity, int priceOne) {
        this.month = month;
        this.title = title;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.priceOne = priceOne;
    }
}