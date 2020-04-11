package article.GUI;

import article.ActionEvent.ActionEvent;
import article.ErrorState;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private JButton jButton;

    public Button(){
        jButton = new JButton();
    }

    public JButton createButton(Rectangle rectangle, boolean flag, String name, int index){
        jButton.setText(name);
        jButton.setMargin(new Insets(1,1, 1, 1));
        jButton.addActionListener(e -> {
            try {
                action.pressButton(index);
            } catch (Exception ex) {
                ErrorState.ErrorPopUp("Error! You enter is wrong!");
//                System.err.println(ex.fillInStackTrace().toString());
//                ex.notify();
//                System.err.println();
//                System.err.println(ex.getCause().toString());
            }
        });
        jButton.setBounds(rectangle);
        jButton.setEnabled(flag);
        jButton.setVisible(true);
        return jButton;
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        jButton.setText(text);
    }

    public void setRoundedBorder() {
    }

    public void setIcon (Icon icon) {
        jButton.setIcon(icon);
    }

    private ActionEvent action;

    public void connection(ActionEvent action){
        this.action = action;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setColor(Color color) {
        jButton.setBackground(color);
    }

    public Color getColor() {
        return jButton.getBackground();
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        if (jButton != null)
            jButton.setBounds(r);
    }

    @Override
    public Rectangle getBounds() {
        if (jButton != null)
            return jButton.getBounds();
        return null;
    }
}
