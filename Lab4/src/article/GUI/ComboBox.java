package article.GUI;

import article.ActionEvent.ActionEvent;
import article.ErrorState;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends JComboBox {
    private JComboBox comboBox;

    public ComboBox() {
        comboBox = new JComboBox();
    }

    public JComboBox createComboBox (Rectangle rectangle, boolean flag, String[] items) {
        for (int i = 0; i < items.length; i++) {
            comboBox.addItem(items[i]);
        }
        comboBox.addActionListener(e -> {
            try {
                action.pressComboBox(comboBox.getSelectedIndex());
            } catch (Exception ex) {
                ErrorState.ErrorPopUp("Error! Something go wrong!");
                System.err.println(ex.fillInStackTrace().toString());
                ex.notify();
                System.err.println();
                System.err.println(ex.getCause().toString());
            }
        });


        comboBox.setBounds(rectangle);
        comboBox.setEnabled(flag);
        comboBox.setVisible(true);
        return null;
    }

    private ActionEvent action;

    public void connection(ActionEvent action){
        this.action = action;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
}
