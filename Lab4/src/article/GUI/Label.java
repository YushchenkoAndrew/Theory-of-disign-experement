package article.GUI;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    private JLabel label;
    public Label() {
        label = new JLabel();
    }

    public void createLabel(Rectangle rectangle, String name, Boolean isVisible, Color color) {
        label.setBounds(rectangle);
        label.setText(name);
        label.setOpaque(true);
        if (color != null)
            label.setBackground(color);
        label.setVisible(isVisible);
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public boolean isVisible(){
        return label.isVisible();
    }

    @Override
    public void setVisible(boolean visible){
        label.setVisible(visible);
    }

    @Override
    public void setBounds(Rectangle rectangle){
        label.setBounds(rectangle);
    }


    public void setForeground(Color color) {
        super.setForeground(color);
        if(label != null)
            label.setForeground(color);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (label != null)
            label.setText(text);
    }

    @Override
    public boolean isFocusOwner(){
        return label.isFocusOwner();
    }


    public void setBackground(Color color) {
        super.setBackground(color);
        if(label != null)
            label.setBackground(color);
    }

}
