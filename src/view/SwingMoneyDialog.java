package view;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Currency;
import model.Money;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
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

    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.amountTextField = new JTextField(15);
        amountTextField.setText("Inserte una cantidad");
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
        return currencyCodes;
    }
    
    public Currency getDestCurrency(){
        for (Currency currency : currencies) {
            if(currency.getCode().equals(currencyToCombo.getSelectedItem()))
                return currency;
        }
        return null;
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
