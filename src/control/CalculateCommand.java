package control;

import implPersistance.RestExchangeRateLoader;
import java.util.List;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import view.MoneyDialog;
import view.MoneyDisplay;
import view.SwingMoneyDialog;

public class CalculateCommand implements Command {

    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final RestExchangeRateLoader exchRateLoader;

    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, RestExchangeRateLoader exchRateLoader) {
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchRateLoader = exchRateLoader;
    }

    @Override
    public String name() {
        return "calculate";
    }

    @Override
    public void execute() {
        Money originMoney = moneyDialog.setMoney();
        Money destMoney = convertMoney(originMoney, ((SwingMoneyDialog) moneyDialog).getDestCurrency());
        moneyDisplay.display(destMoney);
    }

    private Money convertMoney(Money originMoney, Currency destCurrency) {
        List<ExchangeRate> exchRates = exchRateLoader.load(((SwingMoneyDialog) moneyDialog).getCurrencies());
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
