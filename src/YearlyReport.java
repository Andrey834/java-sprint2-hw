import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class YearlyReport {
    public ArrayList<String> years = new ArrayList<>();
    MonthlyReport monthlyReport = new MonthlyReport();

    protected void loadFileMonth(HashMap<String, ArrayList<YearlyConstructor>> dbReportYear) {
        for (var i = 1; i < 4; i++) {
            ArrayList<YearlyConstructor> transToHash = new ArrayList<>();
            //ArrayList<String> yaers = new ArrayList<>();
            var file = "";
            file = ("resources/y.202" + i + ".csv");
            File checkFile = new File(file);
            if (!checkFile.exists() && !checkFile.isDirectory()) {
                continue;
            }
            List<String> contents = readFileContents(file);
            for (int k = 1; k < contents.size(); k++) {
                String line = contents.get(k);
                String[] parts = line.split(",");
                String monthYear = parts[0];
                int amountYear = Integer.parseInt(parts[1]);
                boolean isExpenseYear = Boolean.parseBoolean(parts[2]);
                YearlyConstructor yearlyConstructor = new YearlyConstructor(monthYear, amountYear, isExpenseYear);
                transToHash.add(yearlyConstructor);

            }
            String year = file.replaceAll("[^0-9]", "");
            dbReportYear.put(year, transToHash);
        }
    }

    public void getReportYear(String year, ArrayList<YearlyConstructor> dbYear) {
        System.out.println(year + " год.");
        int getProfitMonth = 0;
        int getLossMonth = 0;
        int profitMonth = 0;
        int averageProfit = 0;
        int averageLoss = 0;
        String getItemMaxProfit = null;
        String getItemMaxLoss = null;

        PrintMenu.printTableYear();
        for (YearlyConstructor report : dbYear) {
            if (!report.isExpenseYear) {
                getProfitMonth = report.amountYear;
            } else {
                getLossMonth = report.amountYear;
            }
            /*for (int i = 0; i < dbYear.size(); i++)
                if (!report.isExpenseYear) {
                    getProfitMonth = report.amountYear;
                } else {
                    getLossMonth = report.amountYear;
                }
            profitMonth = getProfitMonth - getLossMonth;*/

            System.out.printf("%1s %-6s %1s %9s %1s %9s %1s %9s %1s %n", "* ", report.monthYear, " | ", getProfitMonth, " | ", getLossMonth, " | ", (getProfitMonth-getLossMonth), " *");
            System.out.println("------------------------------------------------------");

        }

        /*for (MonthlyConstructor report : reportMonth) {
            if (!report.isExpense) {
                int currentProfit = report.quantity * report.priceOne;
                getMaxProfitMonth += currentProfit;
            } else {
                int currentProfit = report.quantity * report.priceOne;
                getMaxLossMonth += currentProfit;
            }
            profitMonth = getMaxProfitMonth - getMaxLossMonth;
            averageProfit = (averageProfit + getMaxProfitMonth) / 12;
            averageLoss = (averageLoss + getMaxLossMonth) / 12;
        }
        System.out.println("Средний доход " + averageProfit);
        System.out.println("Средний доход " + averageLoss);
        System.out.println("Прибыль за год составила : " + profitMonth);*/
    }

    private List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл поврежден или не соответсвует формату.");
            return Collections.emptyList();
        }
    }
}
