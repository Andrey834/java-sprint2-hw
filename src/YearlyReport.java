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
        for (var i = 1; i < 7; i++) {
            ArrayList<YearlyConstructor> transToHash = new ArrayList<>();
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
        int getProfitMonth = 0;
        int getLossMonth = 0;
        int profitMonth = 0;
        int averageProfit = 0;
        int averageLoss = 0;
        String getItemMaxProfit = null;
        String getItemMaxLoss = null;
        HashMap<String, Integer> getSumMonths = new HashMap<>();
        for (YearlyConstructor report : dbYear) {
            if (!report.isExpenseYear) {
                getProfitMonth = report.amountYear;
                getSumMonths.put(report.monthYear, (getSumMonths.getOrDefault(report.monthYear, 0) + getProfitMonth));
                averageProfit += getProfitMonth;
            } else {
                getLossMonth = report.amountYear;
                getSumMonths.put(report.monthYear, (getSumMonths.getOrDefault(report.monthYear, 0) - getLossMonth));
                averageLoss += getLossMonth;
            }
        }
        PrintMenu.printTableYear(averageProfit, averageLoss, year);


        for (String month : monthlyReport.months) {
            if (getSumMonths.get(month) != null) {
                System.out.printf("%1s %4s %2s %9s %1s %n", "* ", month, " | ", getSumMonths.get(month), " *");
                System.out.println("------------------------");
            } else {
                continue;
            }
        }
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
