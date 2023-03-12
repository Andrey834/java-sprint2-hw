public class MonthlyConstructor {
    public String title;
    public boolean isExpense;
    public int quantity;
    public int priceOne;

    public MonthlyConstructor(String title, boolean isExpense, int quantity, int priceOne) {
        this.title = title;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.priceOne = priceOne;
    }
}