import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YearlyReport {

    protected void loadFileMonth(ArrayList<YearlyConstructor> dbReportMonth) {
        for (var i = 1; i < 4; i++) {
            var file = "";
            file = ("resources/y.202" + i + ".csv");
            File checkFile = new File(file);
            if (!checkFile.exists() && !checkFile.isDirectory()) {
                continue;
            }
            List<String> contents = readFileContents(file, i - 1);
            for (int k = 1; k < contents.size(); k++) {
                String line = contents.get(k);
                String[] parts = line.split(",");
                String monthYear = parts[0];
                int amountYear = Integer.parseInt(parts[1]);
                boolean isExpenseYear = Boolean.parseBoolean(parts[2]);
                YearlyConstructor yearlyConstructor = new YearlyConstructor(monthYear, amountYear, isExpenseYear);
                dbReportMonth.add(yearlyConstructor);
            }
        }
    }

    private List<String> readFileContents(String path, int i) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно файл поврежден или не соответсвует формату.");
            return Collections.emptyList();
        }
    }
}
