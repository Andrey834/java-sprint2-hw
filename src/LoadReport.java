import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class LoadReport {

    protected static void loadFileMonth(HashMap<String, ArrayList<MonthlyConstructor>> dbReportMonths) {
        for (int i = 0; i < Months.months.length; i++) {
            ArrayList<MonthlyConstructor> transToHashM = new ArrayList<>();
            String fileM = ("resources/m.2021" + Months.months[i] + ".csv");
            File checkFile = new File(fileM);
            if (checkFile.exists()) {
                List<String> contents = readFileContents(fileM);
                for (int k = 1; k < contents.size(); k++) {
                    String line = contents.get(k);
                    String[] parts = line.split(",");
                    String title = parts[0];
                    boolean isExpense = Boolean.parseBoolean(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    int priceOne = Integer.parseInt(parts[3]);
                    MonthlyConstructor monthlyConstructor = new MonthlyConstructor(title, isExpense, quantity, priceOne);
                    transToHashM.add(monthlyConstructor);
                }
                dbReportMonths.put(Months.months[i], transToHashM);
            }
        }
    }

    protected static void loadFileYears(HashMap<String, ArrayList<YearlyConstructor>> dbReportYear, String year) {
        if (dbReportYear.isEmpty()) {
            ArrayList<YearlyConstructor> transToHashY = new ArrayList<>();
            String fileY = ("resources/y.2021.csv");
            File checkFile = new File(fileY);
            if (checkFile.exists()) {
                List<String> contents = readFileContents(fileY);
                for (int k = 1; k < contents.size(); k++) {
                    String line = contents.get(k);
                    String[] parts = line.split(",");
                    String monthYear = parts[0];
                    int amountYear = Integer.parseInt(parts[1]);
                    boolean isExpenseYear = Boolean.parseBoolean(parts[2]);
                    YearlyConstructor yearlyConstructor = new YearlyConstructor(monthYear, amountYear, isExpenseYear);
                    transToHashY.add(yearlyConstructor);
                }
            }
            year = fileY.replaceAll("[^0-9]", "");
            dbReportYear.put(year, transToHashY);
        }
    }

    private static List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}