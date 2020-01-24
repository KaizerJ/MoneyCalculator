package swinggui;

import java.awt.LayoutManager;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Money;
import view.MoneyDisplay;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private final JTextField moneyDisplayField;
    
    @Override
    public void display(Money money) {
        String text = money.getAmount() + " " + money.getCurrency().getCode();
        moneyDisplayField.setText(text);
    }

    public SwingMoneyDisplay() {
        moneyDisplayField = new JTextField(25);
        this.add(moneyDisplayField);
        moneyDisplayField.setText("");
        moneyDisplayField.setEditable(false);
        moneyDisplayField.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    
}
