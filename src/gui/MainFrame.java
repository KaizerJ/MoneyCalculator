package gui;

import control.Command;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Currency;
import view.MoneyDisplay;
import view.SwingMoneyDialog;
import view.SwingMoneyDisplay;
import view.ExchangeDialog;

public class MainFrame extends JFrame {
    private final ExchangeDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final Map<String,Command> commands;

    public MainFrame(Currency[] currencies) {
        this.commands = new HashMap<>();
        
        moneyDialog = new SwingMoneyDialog(currencies);
        moneyDisplay = new SwingMoneyDisplay();
        this.setLayout(new BorderLayout());
        this.setTitle("Money Calculator");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add((Component) moneyDialog, BorderLayout.NORTH);
        this.add((Component) moneyDisplay, BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    private Component toolbar() {
        JPanel toolbar = new JPanel();
        toolbar.add(calculateButton());
        return toolbar;
    }

    private JButton calculateButton() {
        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(calculate());
        return calculateButton;
    }

    private ActionListener calculate() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get("calculate").execute();
            }
        };
    }

    public ExchangeDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public void addCommand(Command command) {
        commands.put(command.name(), command);
    }
    
    
    
}
