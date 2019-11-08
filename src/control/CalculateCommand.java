package control;

import implPersistance.RestExchangeRateLoader;
import view.MoneyDialog;
import view.MoneyDisplay;

public class CalculateCommand implements Command {

    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, RestExchangeRateLoader exchRateLoader) {
        /**/
    }

    @Override
    public String name() {
        return "calculate";
    }

    @Override
    public void execute() {
        /*Stub code*/
    }
    
}
