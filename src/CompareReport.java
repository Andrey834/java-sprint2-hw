public class CompareReport {
    public MonthlyConstructor monthlyConstructor;
    public YearlyConstructor yearlyConstructor;

    public CompareReport(MonthlyConstructor monthlyConstructor, YearlyConstructor yearlyConstructor) {
        this.monthlyConstructor = monthlyConstructor;
        this.yearlyConstructor = yearlyConstructor;
    }

    public boolean checkCompare() {
        return false;
    }


}

