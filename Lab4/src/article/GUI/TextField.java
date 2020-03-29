package article.GUI;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField {
    private JTextField textField;

    public TextField(){
        textField = new JTextField();
    }

    public void createTextField(Rectangle rectangle, boolean flag, String name, int index, boolean isVisible) {
        textField.setText(name);
        textField.setEnabled(flag);
        textField.setDisabledTextColor(new Color(0));
        if (index % 2 == 0)
            textField.setBackground(new Color(234, 236, 238));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBounds(rectangle);
        textField.setVisible(isVisible);
    }

    public JTextField getTextField() {
        return textField;
    }

    private int exponent;

    public String deleteTrash(){
        String str = textField.getText();

        exponent = getExponent(str);

        str = str.split("[.,]")[1];
        str = str.replace("\u0305 ", "").replace("\u0305", "");

        StringBuilder newStr = new StringBuilder();
        int j = 0;
        while (str.length() >  j){
            newStr.append("0").append(str.charAt(j));
            j++;
        }
        return newStr.toString().replace("0 01", "11").replace("02", "10");
    }

    public void addMinusOne(){
        int caretPosition = textField.getCaretPosition();
        setText(new StringBuilder(getText()).insert(caretPosition, " \u03051\u0305 ").toString());
        textField.setCaretPosition(caretPosition + " \u03051\u0305 ".length());
    }

    private char[] power = new char[]{'\u2070', '\u00B9', '\u00B2', '\u00B3', '\u2074', '\u2075', '\u2076', '\u2077', '\u2078', '\u2079', '\u207B'};

    public void addPower(int index){
        int caretPosition = textField.getCaretPosition();
        setText(new StringBuilder(getText()).insert(textField.getCaretPosition(), power[index]).toString());
        textField.setCaretPosition(caretPosition + 1);
    }

    public int getExponent(String str){
        int i;
        for (i = 0; i < str.length(); i++) {
            int store = contain(str.charAt(i));
            if (store != -1)
                return store;
        }
        return 0;
    }

    public int getExponent(){
        return minus ? exponent * (-1) : exponent;
    }

    boolean minus = false;

    public int contain(char c){
        for (int i = 0; i < power.length; i++)
            if (power[10] == c)
                minus = true;
            else
                if (power[i] == c)
                    return i;
        return -1;
    }

    @Override
    public boolean isVisible(){
        return textField.isVisible();
    }

    @Override
    public void setVisible(boolean visible){
        textField.setVisible(visible);
    }

    @Override
    public void setBounds(Rectangle rectangle){
        textField.setBounds(rectangle);
    }

    @Override
    public String getText(){
        return textField.getText();
    }

    @Override
    public boolean isFocusOwner(){
        return textField.isFocusOwner();
    }

    @Override
    public void setText(String str){
        textField.setText(str);
    }


    public void setText(boolean[] x){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < x.length; i++) {
            if (i == 2)
                str.append(".");
            str.append(x[i] ? "1" : "0");
        }
        setText(str.toString());
    }

    @Override
    public Dimension getSize(){
        return textField.getSize();
    }
}
