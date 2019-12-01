package view;

import model.Currency;
import model.Money;

public interface ExchangeDialog {
    
    public Money setMoney();
    
    public Currency setDestCurrency();
}
