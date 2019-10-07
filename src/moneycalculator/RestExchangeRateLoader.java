package moneycalculator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RestExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public List<ExchangeRate> load(List<Currency> currencies) { 
        String exchangeJson = null;
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest");
            Scanner scanner = new Scanner(url.openStream());
            exchangeJson = scanner.nextLine();
        } 
        catch (IOException ignored) {}
        
        Map<String, Currency> currencyMap = new HashMap<>();
        for (Currency currency : currencies) {
            currencyMap.put(currency.getCode(), currency);
        }
        
        Gson gson = new Gson();
        Map rates = gson.fromJson(exchangeJson, HashMap.class);
        
        LocalDate date = LocalDate.parse((CharSequence) rates.get("date"));
        Currency base = currencyMap.get(rates.get("base"));
        rates = (Map) rates.get("rates");
        
        ArrayList<ExchangeRate> exchangeRates = new ArrayList<>();
        for (Object code : rates.keySet()) {
            exchangeRates.add(new ExchangeRate(date, (double) rates.get(code), base, currencyMap.get(code)));
        }
                
        return exchangeRates;
    }
}
