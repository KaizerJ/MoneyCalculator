package moneycalculator;

import control.CalculateCommand;
import swinggui.MainFrame;
import implPersistance.CsvCurrencyLoader;
import implPersistance.RestExchangeRateLoader;
import model.Currency;
import persistance.CurrencyLoader;

public class MoneyCalculator {

    public static void main(String[] args) {
        CurrencyLoader currencyLoader = new CsvCurrencyLoader();
        RestExchangeRateLoader exchRateLoader = new RestExchangeRateLoader();
        Currency[] currencies = currencyLoader.load();
        MainFrame mainFrame = new MainFrame(currencies);
        mainFrame.addCommand(new CalculateCommand(mainFrame.getMoneyDialog(),
                                                  mainFrame.getMoneyDisplay(),
                                                  exchRateLoader.load(currencies)));
    }
}
