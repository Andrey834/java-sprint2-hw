import java.util.ArrayList;
import java.util.HashMap;

public class CompareReport {
    public void compare(HashMap<String, ArrayList<YearlyConstructor>> dbYear, HashMap<String, ArrayList<MonthlyConstructor>> dbMonths) {
        HashMap<String, Integer> sumProfitMonths = new HashMap<>(12);
        HashMap<String, Integer> sumProfitYear = new HashMap<>(12);
        ArrayList<String> difMonth = new ArrayList<>();

        for (String month : Months.months) {
            int sumMonthYear = 0;
            for (String s : dbYear.keySet()) {
                for (YearlyConstructor yearlyConstructor : dbYear.get(s)) {
                    if (!yearlyConstructor.isExpenseYear && (yearlyConstructor.monthYear.equals(month))) {
                        sumMonthYear += yearlyConstructor.amountYear;
                    } else if (yearlyConstructor.isExpenseYear && (yearlyConstructor.monthYear.equals(month))) {
                        sumMonthYear -= yearlyConstructor.amountYear;
                    }
                }
            }
            sumProfitMonths.put(month, sumMonthYear);

            if (dbMonths.get(month) != null) {
                int sumMonth = 0;
                for (MonthlyConstructor monthlyConstructor : dbMonths.get(month)) {
                    if (!monthlyConstructor.isExpense) {
                        sumMonth += monthlyConstructor.priceOne * monthlyConstructor.quantity;
                    } else {
                        sumMonth -= monthlyConstructor.priceOne * monthlyConstructor.quantity;
                    }
                }
                sumProfitYear.put(month, sumMonth);
            } else {
                sumProfitYear.put(month, 0);
            }
        }

        for (String month : Months.months) {
            if (!sumProfitYear.get(month).equals(sumProfitMonths.get(month))) {
                difMonth.add(month);
            }
        }

        if (difMonth.isEmpty()) {
            System.out.println("Сверка отчетов завершена успешно! Несоответствий не обнаружено.");
        } else {
            for (String s : difMonth) {
                System.out.println("Обнаружено несоответствие в месяце под № " + s);
            }
        }
    }
}



