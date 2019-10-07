package moneycalculator;

import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoader cl = new CsvCurrencyLoader();
        List<Currency> currencies = cl.load();
        RestExchangeRateLoader exchLoader = new RestExchangeRateLoader();
        exchLoader.load(currencies);
    }
    
}
