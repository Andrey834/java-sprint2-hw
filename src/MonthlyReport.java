import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MonthlyReport {
    public ArrayList<MonthlyConstructor> dbReportMonth = new ArrayList<>();
    //public ArrayList<YearlyConstructor> dataBaseYearSave = new ArrayList<>();

    public void loadFileMonth() {
        for (var i = 1; i < 13; i++) {
            var file = "";
            if (i < 10) {
                file = ("resources/m.20210" + i + ".csv");
            } else {
                file = ("resources/m.2021" + i + ".csv");
            }
            List<String> contents = readFileContents(file);
            if (contents.size() == 0) {
                continue;
            }
            for (int k = 1; k < contents.size(); k++) {
                String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
                String line = contents.get(k);
                String[] parts = line.split(",");
                String month = months[i - 1];
                String title = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int priceOne = Integer.parseInt(parts[3]);
                MonthlyConstructor monthlyConstructor = new MonthlyConstructor(month, title, isExpense, quantity, priceOne);
                dbReportMonth.add(monthlyConstructor);
            }
        }
    }

    public String getMaxProfitM() {
        HashMap<String, Integer> summary = new HashMap<>();
        for (MonthlyConstructor monthlyConstructor : dbReportMonth) {
            summary.put(monthlyConstructor.title, summary.getOrDefault(monthlyConstructor.title, 0) + (monthlyConstructor.quantity * monthlyConstructor.priceOne));
        }
        String maxTitle = null;
        for (String title : summary.keySet()) {
            if (maxTitle == null) {
                maxTitle = title;
                continue;
            }
            if (summary.get(maxTitle) < summary.get(title)) {
                maxTitle = title;
            }
        }
        return maxTitle;
    }

    public List<String> readFileContents(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.err.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return Collections.emptyList();
        }
    }
}
