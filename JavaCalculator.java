import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class JavaCalculator extends JFrame implements ActionListener {
    // simple calculator with +, -, * & /

    static JFrame frame;
    static JTextField display;

    String expressionString = "";
    String total = "";

    void reduceValues(List<String> eq, int i, String operator){
        double total = 0.0;
        switch(operator){
          case "/":
            total = Double.parseDouble(eq.get(i-1)) / Double.parseDouble(eq.get(i+1));
            break;
          case "*":
            total = Double.parseDouble(eq.get(i-1)) * Double.parseDouble(eq.get(i+1));
            break;
          case "+":
            total = Double.parseDouble(eq.get(i-1)) + Double.parseDouble(eq.get(i+1));
            break;
          case "-":
            total = Double.parseDouble(eq.get(i-1)) - Double.parseDouble(eq.get(i+1));
            break;
        }
        eq.set(i-1, Double.toString(total));
        eq.remove(i);
        eq.remove(i);

    }


    String calculate(String equation) {
    // runs through the equation string and calculates answer by order of operations
        String[] equationArray = equation.split(" ");
        List<String> reducedArray = new ArrayList<>(Arrays.asList(equationArray));

        for (int i = 0; i < reducedArray.size(); i++) {
            if (reducedArray.get(i).equals("/")) {
                reduceValues(reducedArray, i, "/");
            } else if (reducedArray.get(i).equals("*")){
                reduceValues(reducedArray, i, "*");
            } 
        }
        for (int i = 0; i < reducedArray.size(); i++) {
            if (reducedArray.get(i).equals("+")) {
                reduceValues(reducedArray, i, "+");
            } else if (reducedArray.get(i).equals("-")){
                reduceValues(reducedArray, i, "-");
            }
        }

        String answer = reducedArray.get(0);
        String[] splitAnswer = answer.split("\\.");

        // if the answer has a trailing 0, it removes it
        if(splitAnswer[1].equals("0")){
            answer = splitAnswer[0];
            return answer;
        } else {
            return answer;
        }

    }

    public static void main(String[] args) {
        frame = new JFrame("JavaCalculator");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JavaCalculator calculator = new JavaCalculator();

        display = new JTextField(18);

        display.setEditable(false);

        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1;

        // create number buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        // equals button
        beq1 = new JButton("=");

        // create operator buttons
        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        beq = new JButton("C");

        // create . button
        be = new JButton(".");

        // create a panel
        JPanel panel = new JPanel();

        // add action listeners
        bm.addActionListener(calculator);
        bd.addActionListener(calculator);
        bs.addActionListener(calculator);
        ba.addActionListener(calculator);
        b9.addActionListener(calculator);
        b8.addActionListener(calculator);
        b7.addActionListener(calculator);
        b6.addActionListener(calculator);
        b5.addActionListener(calculator);
        b4.addActionListener(calculator);
        b3.addActionListener(calculator);
        b2.addActionListener(calculator);
        b1.addActionListener(calculator);
        b0.addActionListener(calculator);
        be.addActionListener(calculator);
        beq.addActionListener(calculator);
        beq1.addActionListener(calculator);

        // add elements to panel
        panel.add(display);
        panel.add(ba);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(bs);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(bm);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(bd);
        panel.add(be);
        panel.add(b0);
        panel.add(beq);
        panel.add(beq1);

        // set background color of panel
        panel.setBackground(Color.cyan);

        // add panel to frame
        frame.add(panel);

        frame.setSize(350, 220);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String calcEntry = e.getActionCommand();

        if (calcEntry.equals("/") || calcEntry.equals("+") || calcEntry.equals("-") || calcEntry.equals("*")) {
            expressionString = expressionString + " " + calcEntry + " ";
        } else if (calcEntry.equals("C")) {
            expressionString = "";
        } else if (calcEntry.equals("=")) {
            expressionString = calculate(expressionString);
        } else {
            expressionString = expressionString + calcEntry;
        }

        display.setText(expressionString);
        // System.out.println(expressionString);
    }
}