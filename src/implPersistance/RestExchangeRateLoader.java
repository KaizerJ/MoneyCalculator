package implPersistance;

import model.Currency;
import model.ExchangeRate;
import persistance.ExchangeRateLoader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public List<ExchangeRate> load(Currency[] currencies) { 
        
        Map<String, Currency> currencyMap = new HashMap<>();
        for (Currency currency : currencies) {
            currencyMap.put(currency.getCode(), currency);
        }
        
        String exchangeJson;
        URL url;
        LocalDate date;
        Map rates;
        Currency base;
        Gson gson = new Gson();
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        for (Currency currency : currencies) {            
            exchangeJson = null;
            try {
                url = new URL("https://api.exchangeratesapi.io/latest?base=" + 
                                    currency.getCode());
                Scanner scanner = new Scanner(url.openStream());
                exchangeJson = scanner.nextLine();
            } 
            catch (IOException ignored) {}

            rates = gson.fromJson(exchangeJson, HashMap.class);

            date = LocalDate.parse((CharSequence) rates.get("date"));
            base = currencyMap.get(rates.get("base"));
            rates = (Map) rates.get("rates");

            for (Object code : rates.keySet()) {
                exchangeRates.add(new ExchangeRate(date, (double) rates.get(code), base, currencyMap.get(code)));
            }
            
        }
        exchangeRates.add(new ExchangeRate(LocalDate.now(),1,currencyMap.get("EUR"),currencyMap.get("EUR")));
                
        return exchangeRates;
    }
}
