import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton[] buttons;
    private String[] buttonLabels = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/", "Reset"};
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public Calculator() {
        frame = new JFrame("Calculator");
        panel = new JPanel(new GridLayout(5, 4, 5, 5));
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setEditable(false);
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    public void actionPerformed(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getText();
        switch (buttonText) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                if (startNewNumber) {
                    textField.setText(buttonText);
                    startNewNumber = false;
                } else {
                    textField.setText(textField.getText() + buttonText);
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                firstNumber = Double.parseDouble(textField.getText());
                operator = buttonText;
                startNewNumber = true;
                break;
            case "=":
                double secondNumber = Double.parseDouble(textField.getText());
                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        result = firstNumber / secondNumber;
                        break;
                }
                textField.setText(Double.toString(result));
                startNewNumber = true;
                break;
            case "Reset":
                textField.setText("");
                firstNumber = 0;
                operator = "";
                startNewNumber = true;
                break;
        }
    }
}
