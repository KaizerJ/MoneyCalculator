package control;

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
    private final List<ExchangeRate> exchRates;

    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, List<ExchangeRate> exchRates) {
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchRates = exchRates;
    }

    @Override
    public String name() {
        return "calculate";
    }
    
    //No me gustan nada los cast que hago para obtener la divisa destino
    
    @Override
    public void execute() {
        Money originMoney = moneyDialog.setMoney();
        Money destMoney = convertMoney(originMoney, ((SwingMoneyDialog) moneyDialog).getDestCurrency());
        moneyDisplay.display(destMoney);
    }
    
    //No me gustan el cast para obtener las divisas y cargas TODOS los exchange rates cada vez
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
