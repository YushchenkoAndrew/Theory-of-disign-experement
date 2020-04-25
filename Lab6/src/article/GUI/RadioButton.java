package article.GUI;

import article.ActionEvent.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class RadioButton extends JRadioButton {
    private JRadioButton radioButton;

    /**
     * Creates an initially unselected radio buttons
     * with no set text.
     */
    public RadioButton() {
        radioButton = new JRadioButton();
    }

    public void createRadioButton(Rectangle rectangle, String name, boolean isVisible, int index) {
        radioButton.setBounds(rectangle);
        radioButton.setText(name);
        radioButton.setVisible(isVisible);
        radioButton.addActionListener(e -> {
            action.pressedRadioButton(index);
        });
    }

    private ActionEvent action;

    public void connection(ActionEvent action){
        this.action = action;
    }


    public void setColor(Color color) {
        radioButton.setBackground(color);
    }

    public JRadioButton getRadioButton () {
        return radioButton;
    }

    public void setSelected (boolean flag) {
        radioButton.setSelected(flag);
    }
}
