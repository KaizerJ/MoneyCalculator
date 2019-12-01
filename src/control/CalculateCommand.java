package control;

import java.util.List;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import view.MoneyDisplay;
import view.ExchangeDialog;

public class CalculateCommand implements Command {

    private final ExchangeDialog exchangeDialog;
    private final MoneyDisplay moneyDisplay;
    private final List<ExchangeRate> exchRates;

    public CalculateCommand(ExchangeDialog moneyDialog, MoneyDisplay moneyDisplay, List<ExchangeRate> exchRates) {
        this.exchangeDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchRates = exchRates;
    }

    @Override
    public String name() {
        return "calculate";
    }
    
    @Override
    public void execute() {
        Money originMoney = exchangeDialog.setMoney();
        Money destMoney = convertMoney(originMoney, exchangeDialog.setDestCurrency());
        moneyDisplay.display(destMoney);
    }

    private Money convertMoney(Money originMoney, Currency destCurrency) {
        double rate = 0;
        for (ExchangeRate exchRate : exchRates) {
            if(exchRate.getFrom().equals(originMoney.getCurrency()) && 
                    exchRate.getTo().equals(destCurrency)){
                rate = exchRate.getRate();
                break;
            }
        }
        
        return new Money(originMoney.getAmount() * rate, destCurrency);
    }
    
}
