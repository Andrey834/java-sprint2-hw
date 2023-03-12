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

    protected void loadFileMonth(HashMap<String, ArrayList<MonthlyConstructor>> dbReportMonths) {
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
            dbReportMonths.put(months[i - 1], transToHash);
        }
    }

    public void getValueMonths(ArrayList<MonthlyConstructor> report, String month) {
        int getMaxProfit = 0;
        int getMaxLoss = 0;
        String getItemMaxProfit = null;
        String getItemMaxLoss = null;

        for (MonthlyConstructor monthlyConstructor : report) {
            if (!monthlyConstructor.isExpense) {
                int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                if (getMaxProfit < currentProfit) {
                    getItemMaxProfit = monthlyConstructor.title;
                    getMaxProfit = currentProfit;
                }
            }
        }
        for (MonthlyConstructor monthlyConstructor : report) {
            if (monthlyConstructor.isExpense) {
                int currentProfit = monthlyConstructor.quantity * monthlyConstructor.priceOne;
                if (getMaxLoss < currentProfit) {
                    getItemMaxLoss = monthlyConstructor.title;
                    getMaxLoss = currentProfit;
                }
            }
        }

        System.out.printf("%1s %-7s %1s %-30s %1s %13s %1s %-34s %1s %12s %1s %n", "* ", month, " | ", getItemMaxProfit, " | ", getMaxProfit, " | ", getItemMaxLoss, " | ", getMaxLoss, " *");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
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
