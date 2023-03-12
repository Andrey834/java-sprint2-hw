import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class LoadReport {

    protected void loadFileMonth(HashMap<String, ArrayList<MonthlyConstructor>> dbReportMonths, String[] months) {
        for (int i = 1; i < 13; i++) {
            String fileM = "";
            if (i < 10) {
                fileM = ("resources/m.20210" + i + ".csv");
            } else {
                fileM = ("resources/m.2021" + i + ".csv");
            }
            File checkFile = new File(fileM);
            if (!checkFile.exists() && !checkFile.isDirectory()) {
                continue;
            }
            List<String> contents = readFileContents(fileM);
            ArrayList<MonthlyConstructor> transToHashM = new ArrayList<>();
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
            dbReportMonths.put(months[i-1], transToHashM);
        }
    }

    protected void loadFileYears(HashMap<String, ArrayList<YearlyConstructor>> dbReportYear) {
        for (int i = 1; i < 8; i++) {
            ArrayList<YearlyConstructor> transToHashY = new ArrayList<>();
            String fileY = "";
            fileY = ("resources/y.202" + i + ".csv");
            File checkFile = new File(fileY);
            if (!checkFile.exists() && !checkFile.isDirectory()) {
                continue;
            }
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
            String year = fileY.replaceAll("[^0-9]", "");
            dbReportYear.put(year, transToHashY);
        }
    }

    private List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
