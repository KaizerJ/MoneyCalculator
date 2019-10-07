package moneycalculator;

import java.util.List;

public interface ExchangeRateLoader {
    
    public List<ExchangeRate> load(List<Currency> currencies);
}
