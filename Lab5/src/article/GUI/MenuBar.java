package article.GUI;

import article.ActionEvent.ActionEvent;
import article.Filler;

import javax.swing.*;
import java.awt.*;

public class MenuBar extends JMenuBar{

    private JMenuBar menuBar;
    private JMenu[] menus = new JMenu[5];
    public JMenu subMenu = new JMenu("Add Power");;
    public JMenuItem[][] menuItems = new JMenuItem[5][15];
    public JRadioButtonMenuItem[][] radioButtonMenuItems = new JRadioButtonMenuItem[2][10];
    ButtonGroup[] group = new ButtonGroup[2];

    public MenuBar(){
        menuBar = Filler.fillMenuBar(menus, subMenu, menuItems, radioButtonMenuItems, group);

    }

    public JMenuBar createMenuBar(){
        menuBar.setBackground(new Color(255, 255, 255));
        menuBar.setVisible(true);
        return menuBar;
    }

    public ActionEvent action;

    public void connection(ActionEvent action){
        this.action = action;
    }

    public void commandStart(){
        menuItems[1][0].setEnabled(false);
        menuItems[1][1].setEnabled(true);
        menuItems[1][2].setEnabled(true);
    }

    public void commandStop(){
        menuItems[1][0].setEnabled(true);
        menuItems[1][1].setEnabled(false);
        menuItems[1][2].setEnabled(false);
    }
}
