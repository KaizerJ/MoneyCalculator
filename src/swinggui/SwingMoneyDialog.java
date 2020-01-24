package swinggui;

import java.util.Arrays;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Currency;
import model.Money;
import view.ExchangeDialog;

public class SwingMoneyDialog extends JPanel implements ExchangeDialog {
    private final Currency[] currencies;
    private final JTextField amountTextField;
    private final JComboBox<Currency> currencyFromCombo;
    private final JComboBox<Currency> currencyToCombo;
    
    @Override
    public Money setMoney() {
        double amount = 0.;
        try {
            amount = Double.parseDouble(amountTextField.getText());
        } catch (Exception ignored) {}
        return new Money(amount,getFromCurrency());
    }

    @Override
    public Currency setDestCurrency(){
        for (Currency currency : currencies) {
            if(currency.getCode().equals(currencyToCombo.getSelectedItem()))
                return currency;
        }
        return null;
    }
    
    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.amountTextField = new JTextField(15);
        amountTextField.setText("Inserte una cantidad");
        amountTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        String[] currencyCodes = getCurrencyCodes();
        this.currencyFromCombo = new JComboBox(currencyCodes);
        this.currencyToCombo = new JComboBox(currencyCodes);
        this.add(amountTextField);
        this.add(currencyFromCombo);
        this.add(currencyToCombo);
    }

    private String[] getCurrencyCodes() {
        String[] currencyCodes = new String[currencies.length];
        int iter = 0;
        for (Currency currency : currencies) {
            currencyCodes[iter++] = currency.getCode();
        }
        Arrays.sort(currencyCodes);
        return currencyCodes;
    }

    public Currency[] getCurrencies() {
        return currencies;
    }
    
    //Es muy similar a getDestCurrency (Refactorizar?)
    private Currency getFromCurrency() {
        for (Currency currency : currencies) {
            if(currency.getCode().equals(currencyFromCombo.getSelectedItem()))
                return currency;
        }
        return null;
    }
}
