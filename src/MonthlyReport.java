import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

 class MonthlyReport {

     private final String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    //public ArrayList<MonthlyConstructor> dbReportMonth = new ArrayList<>();
    //PrintMenu printMenu = new PrintMenu();
    //public ArrayList<String> checkLoadMonth = new ArrayList<>();

    protected void loadFileMonth(HashMap<String, ArrayList<MonthlyConstructor>> dbReportMonths) {
        ArrayList<MonthlyConstructor> transToHash = new ArrayList<>();
        for (var i = 1; i < 13; i++) {
            var file = "";
            if (i < 10) {
                file = ("resources/m.20210" + i + ".csv");
            } else {
                file = ("resources/m.2021" + i + ".csv");
            }
            File checkFile = new File(file);
            if (!checkFile.exists() && !checkFile.isDirectory()) {
                continue;
            }
            List<String> contents = readFileContents(file, months, i - 1);
            for (int k = 1; k < contents.size(); k++) {
                String line = contents.get(k);
                String[] parts = line.split(",");
                String title = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int priceOne = Integer.parseInt(parts[3]);
                MonthlyConstructor monthlyConstructor = new MonthlyConstructor(title, isExpense, quantity, priceOne);
                transToHash.add(monthlyConstructor);
            }
            dbReportMonths.put(months[i-1],transToHash);
        }
    }

    public void getMaxProfitM(ArrayList<MonthlyConstructor> dbReportMonth) {
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
    }

    private List<String> readFileContents(String path, String[] months, int i) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом за *" + months[i] + "*. Возможно файл поврежден или не соответсвует формату.");
            return Collections.emptyList();
        }
    }
}
