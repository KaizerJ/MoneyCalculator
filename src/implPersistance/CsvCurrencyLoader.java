package implPersistance;

import model.Currency;
import persistance.CurrencyLoader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvCurrencyLoader implements CurrencyLoader{
    
    private final String path = "Currencies.csv";
    
    @Override
    public List<Currency> load() {
        List<Currency> currencies = new ArrayList<>();
        try( BufferedReader csvReader = new BufferedReader(new FileReader(path))){
            String row;
            String[] info;
            while((row = csvReader.readLine()) != null){
                info = row.split(",");
                currencies.add(new Currency(info[0],info[1],info[2]));
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
        return currencies;
    }
    
}
