import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class MonthlyReport {

    public String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    protected void loadFileMonth(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthReports) {
        //ArrayList<MonthlyConstructor> transToHash = new ArrayList<>();
        for (var i = 1; i < 13; i++) {
            ArrayList<MonthlyConstructor> transToHash = new ArrayList<>();
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
            dbMonthReports.put(months[i - 1], transToHash);
        }
    }

    public void getMaxValue(HashMap<String, ArrayList<MonthlyConstructor>> dbMonthReports, String month) {
        int maxSumProfit = 0;
        String getItemMax = null;
        int maxSumLoss = 0;
        String getItemMaxLoss = null;

        for (MonthlyConstructor monthlyConstructor : dbMonthReports.get(month)) {
            if (!monthlyConstructor.isExpense) {
                int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                if (maxSumProfit < currentProfit) {
                    getItemMax = monthlyConstructor.title;
                    maxSumProfit = currentProfit;
                }
            }
        }
        for (MonthlyConstructor monthlyConstructor : dbMonthReports.get(month)) {
            if (monthlyConstructor.isExpense) {
                int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                if (maxSumLoss < currentProfit) {
                    getItemMaxLoss = monthlyConstructor.title;
                    maxSumLoss = currentProfit;
                }
            }
        }
        System.out.println("===========================");
        System.out.println(month);
        System.out.println("===========================");
        System.out.println("Самый прибыльный товар:");
        System.out.println(getItemMax);
        System.out.println(maxSumProfit);
        System.out.println("---------------------------");
        System.out.println("Самая большая трата:");
        System.out.println(getItemMaxLoss);
        System.out.println(maxSumLoss);
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
