package persistance;

import java.util.List;
import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateLoader {
    
    public List<ExchangeRate> load(Currency[] currencies);
}
