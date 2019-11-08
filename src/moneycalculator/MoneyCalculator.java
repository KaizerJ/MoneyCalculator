package moneycalculator;

import control.CalculateCommand;
import gui.MainFrame;
import implPersistance.CsvCurrencyLoader;
import implPersistance.RestExchangeRateLoader;
import model.Currency;
import persistance.CurrencyLoader;
import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new CsvCurrencyLoader();
        RestExchangeRateLoader exchRateLoader = new RestExchangeRateLoader();
        MainFrame mainFrame = new MainFrame(currencyLoader.load());
        mainFrame.addCommand(new CalculateCommand(mainFrame.getMoneyDialog(),
                                                  mainFrame.getMoneyDisplay(),
                                                  exchRateLoader));
    }
}
