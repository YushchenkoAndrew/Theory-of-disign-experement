package article;

import article.ActionEvent.Action;
import article.GUI.GUI;
import article.GUI.MenuBar;
import article.GUI.Panel;
import article.GUI.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

public class Filler {

    public static void fillGUI(GUI gui, Action action) throws Exception {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLayout(null);
        gui.setAction(action);
        gui.createPane("Main registers", new Rectangle(2, 80, 205, 185), null);
        gui.createPane("Input", new Rectangle(0, 0, 493, 75), null);

        gui.arrayPanel[0].setColor(new Color(254, 249, 231));
        gui.arrayPanel[1].setColor(new Color(254, 249, 231));

        int textFieldPaneHeight = 20;
        int textFieldPaneX1 = 15;
        int textFieldPaneX2 = 55;
        int textFieldPaneY = 45;
        int textFieldPaneWidth1 = 30;
        int textFieldPaneWidth2 = 140;

        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight),
                false, "X", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight),
                false, "00000000", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY + 20, textFieldPaneWidth1, textFieldPaneHeight),
                false, "Y", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY + 20, textFieldPaneWidth2, textFieldPaneHeight),
                false, "00000000", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY + 40, textFieldPaneWidth1, textFieldPaneHeight),
                false, "Z", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY + 40, textFieldPaneWidth2, textFieldPaneHeight),
                false, "0000.0000", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY + 60, textFieldPaneWidth1, textFieldPaneHeight),
                false, "R", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY + 60, textFieldPaneWidth2, textFieldPaneHeight),
                false, "0000.0000", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY + 80, textFieldPaneWidth1, textFieldPaneHeight),
                false, "H", true), 0);
        gui.addInPanel(gui.createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY + 80, textFieldPaneWidth2, textFieldPaneHeight),
                false, "0000.0000", true), 0);

        textFieldPaneY = 30;
        textFieldPaneWidth1 = 30;
        textFieldPaneWidth2 = 140;

        gui.addInPanel(gui.createTextField(new Rectangle(15, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight),
                false, "X", true), 1);
        gui.addInPanel(gui.createTextField(new Rectangle(55, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight),
                true, "", true), 1);
        gui.addInPanel(gui.createTextField(new Rectangle(240, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight),
                false, "Y", true), 1);
        gui.addInPanel(gui.createTextField(new Rectangle(280, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight),
                true, "", true), 1);


        gui.addInPanel(gui.createButton(new Rectangle(435, 28, 45, 25), true, "Load"), 1);

        gui.add(gui.createTextField(new Rectangle(310, 90, 120, textFieldPaneHeight), false, "Last instruction", true));
        gui.add(gui.createTextField(new Rectangle(255, 115, 230, textFieldPaneHeight), false, "", true));
        gui.add(gui.createTextField(new Rectangle(210, 140, 40, textFieldPaneHeight), false, "Next", true));
        gui.add(gui.createTextField(new Rectangle(255, 140, 230, textFieldPaneHeight), false, "", true));
        gui.add(gui.createTextField(new Rectangle(210, 165, 170, textFieldPaneHeight), false, "Instruction counter", true));
        gui.add(gui.createTextField(new Rectangle(395, 165, 90, textFieldPaneHeight), false, "", true));

        String[] columns = new String[]{"№", "X[i]", "Y[i]", "H", "Z", "2R(i - 1)"};
        gui.add(gui.createTable(new Rectangle(0, 270, 495, 169), columns, false));

        gui.createMenuBar();
        Filler.fillAction(gui.menuBar);
        gui.repaint();
    }

    public static void addNeedingComponentsFunction(article.GUI.Panel[] panels, article.GUI.TextField[] textFields, Table table) {
        int textFieldPaneHeight = 20;
        int textFieldPaneY = 15;
        int textFieldPaneWidth1 = 30;
        int textFieldPaneWidth2 = 110;

        textFields[10].setBounds(new Rectangle(15, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight));
        textFields[10].setText("A");
        textFields[11].setBounds(new Rectangle(55, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight));

        textFields[12].setBounds(new Rectangle(180, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight));
        textFields[12].setText("B");
        textFields[13].setBounds(new Rectangle(220, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight));


        textFields[20] = new article.GUI.TextField();
        textFields[20].createTextField(new Rectangle(15, textFieldPaneY + 30, textFieldPaneWidth1, textFieldPaneHeight),
                false, "C", 20, true);
        panels[1].addInPanel(textFields[20].getTextField());
        textFields[21] = new article.GUI.TextField();
        textFields[21].createTextField(new Rectangle(55, textFieldPaneY + 30, textFieldPaneWidth2, textFieldPaneHeight),
                true, "", 21, true);
        panels[1].addInPanel(textFields[21].getTextField());

        textFields[22] = new article.GUI.TextField();
        textFields[22].createTextField(new Rectangle(180, textFieldPaneY + 30, textFieldPaneWidth1, textFieldPaneHeight),
                false, "D", 22, true);
        panels[1].addInPanel(textFields[22].getTextField());
        textFields[23] = new article.GUI.TextField();
        textFields[23].createTextField(new Rectangle(220, textFieldPaneY + 30, textFieldPaneWidth2, textFieldPaneHeight),
                true, "", 23, true);
        panels[1].addInPanel(textFields[23].getTextField());


        textFields[24] = new article.GUI.TextField();
        textFields[24].createTextField(new Rectangle(340, 30, textFieldPaneWidth1, textFieldPaneHeight),
                false, "P", 24, true);
        panels[1].addInPanel(textFields[24].getTextField());
        textFields[25] = new article.GUI.TextField();
        textFields[25].createTextField(new Rectangle(380, 30, textFieldPaneWidth1 + 10, textFieldPaneHeight),
                true, "", 25, true);
        panels[1].addInPanel(textFields[25].getTextField());

        textFieldPaneHeight = 20;
        int textFieldPaneX1 = 15;
        int textFieldPaneX2 = 55;
        textFieldPaneY = 25;
        textFieldPaneWidth1 = 30;
        textFieldPaneWidth2 = 140;

        textFields[0].setText("A");
        textFields[2].setText("B");

        for (int i = 0; i < 10; i += 2) {
            int q = textFieldPaneY + 10 * i + (i < 4 ? 0 : 40);
            textFields[i].setBounds(new Rectangle(textFieldPaneX1, q, textFieldPaneWidth1, textFieldPaneHeight));
            textFields[i + 1].setBounds(new Rectangle(textFieldPaneX2, q, textFieldPaneWidth2, textFieldPaneHeight));
        }

        textFields[26] = new article.GUI.TextField();
        textFields[26].createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY + 40, textFieldPaneWidth1, textFieldPaneHeight),
                false, "C", 26, true);
        panels[0].addInPanel(textFields[26].getTextField());
        textFields[27] = new article.GUI.TextField();
        textFields[27].createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY + 40, textFieldPaneWidth2, textFieldPaneHeight),
                false, "00000000", 27, true);
        panels[0].addInPanel(textFields[27].getTextField());

        textFields[28] = new article.GUI.TextField();
        textFields[28].createTextField(new Rectangle(textFieldPaneX1, textFieldPaneY + 60, textFieldPaneWidth1, textFieldPaneHeight),
                false, "D", 28, true);
        panels[0].addInPanel(textFields[28].getTextField());
        textFields[29] = new article.GUI.TextField();
        textFields[29].createTextField(new Rectangle(textFieldPaneX2, textFieldPaneY + 60, textFieldPaneWidth2, textFieldPaneHeight),
                false, "00000000", 29, true);
        panels[0].addInPanel(textFields[29].getTextField());

        table.createColumns(new String[]{"№", "A[i]", "B[i]", "C[i]", "D[i]", "H", "Z", "2R(i - 1)"});
    }

    public static void addNeedingComponentsRoot(Panel[] panels, article.GUI.TextField[] textFields, Table table) {
        int textFieldPaneY = 30;
        int textFieldPaneWidth1 = 30;
        int textFieldPaneHeight = 20;

        textFields[11].setBounds(new Rectangle(55, textFieldPaneY, 180, textFieldPaneHeight));

        textFields[12].setBounds(new Rectangle(280, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight));
        textFields[12].setText("P");
        textFields[13].setBounds(new Rectangle(320, textFieldPaneY, textFieldPaneWidth1 + 20, textFieldPaneHeight));

        int textFieldPaneX1 = 15;
        int textFieldPaneX2 = 55;
        int textFieldPaneWidth2 = 140;
        textFieldPaneY = 65;

        for (int i = 0; i < 4; i += 2) {
            int q = textFieldPaneY + 10 * i;
            textFields[i].setBounds(new Rectangle(textFieldPaneX1, q, textFieldPaneWidth1, textFieldPaneHeight));
            textFields[i + 1].setBounds(new Rectangle(textFieldPaneX2, q, textFieldPaneWidth2, textFieldPaneHeight));
        }

        table.createColumns(new String[]{"№", "X[i]", "H", "Y", "2R(i - 1)"});
    }

    public static void addNeedingComponentsHypotenuseFunction(article.GUI.Panel[] panels, article.GUI.TextField[] textFields, Table table) {
        int textFieldPaneY = 30;
        int  textFieldPaneWidth1 = 30;
        int textFieldPaneWidth2 = 110;
        int textFieldPaneHeight = 20;

        textFields[11].setBounds(new Rectangle(55, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight));
        textFields[12].setBounds(new Rectangle(180, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight));
        textFields[13].setBounds(new Rectangle(220, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight));


        textFields[20] = new article.GUI.TextField();
        textFields[20].createTextField(new Rectangle(340, 30, textFieldPaneWidth1, textFieldPaneHeight),
                false, "P", 20, true);
        panels[1].addInPanel(textFields[20].getTextField());
        textFields[21] = new article.GUI.TextField();
        textFields[21].createTextField(new Rectangle(380, 30, textFieldPaneWidth1 + 10, textFieldPaneHeight),
                true, "", 21, true);
        panels[1].addInPanel(textFields[21].getTextField());

    }

    public static void removeNotUsingComponents(article.GUI.Panel[] panels, article.GUI.TextField[] textFields, Table table) {
        int textFieldPaneHeight = 20;
        int textFieldPaneX1 = 15;
        int textFieldPaneX2 = 55;
        int textFieldPaneY = 45;
        int textFieldPaneWidth1 = 30;
        int textFieldPaneWidth2 = 140;

        textFields[0].setText("X");
        textFields[2].setText("Y");

        for (int i = 0; i < 10; i += 2) {
            int q = textFieldPaneY + 10 * i;
            textFields[i].setBounds(new Rectangle(textFieldPaneX1, q, textFieldPaneWidth1, textFieldPaneHeight));
            textFields[i + 1].setBounds(new Rectangle(textFieldPaneX2, q, textFieldPaneWidth2, textFieldPaneHeight));
        }


        textFieldPaneY = 30;
        textFieldPaneWidth1 = 30;
        textFieldPaneWidth2 = 140;

        textFields[10].setBounds(new Rectangle(15, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight));
        textFields[10].setText("X");
        textFields[11].setBounds(new Rectangle(55, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight));

        textFields[12].setBounds(new Rectangle(240, textFieldPaneY, textFieldPaneWidth1, textFieldPaneHeight));
        textFields[12].setText("Y");
        textFields[13].setBounds(new Rectangle(280, textFieldPaneY, textFieldPaneWidth2, textFieldPaneHeight));

        for (int i = 20; i <= 29; i++) {
            int q = i <= 25 ? 1 : 0;
            if (textFields[i] != null)
                panels[q].remove(textFields[i].getTextField());
            else
                break;
            textFields[i] = null;

        }

        table.createColumns(new String[]{"№", "X[i]", "Y[i]", "H", "Z", "2R(i - 1)"});
    }

    public static JMenuBar fillMenuBar(JMenu[] menus, JMenu subMenu, JMenuItem[][] menuItems, JRadioButtonMenuItem[][] radioButtonMenuItems, ButtonGroup[] group) {
        String[] menuStrings = new String[]{"File", "Simulation", "Operation", "Rate", "Tools"};
        String[][] menuItemStrings = new String[][]{{"Reset Registers"},
                {"Start", "Step", "Stop"},
                {"Sum", "Multiplication", "Division", "Function", "Root", "PythagorasFunction"},
                {"Step by step", "Slow", "Medium", "Fast"},
                {"Add '-1'", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-"}};
        Object[][] keyCommand = new Object[][]{{}, {KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E}, {}, {}, {KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2,
                KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD7,
                KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD9, KeyEvent.VK_NUMPAD0, KeyEvent.VK_MINUS}};

        JMenuBar menuBar = new JMenuBar();
        group[0] = new ButtonGroup();
        group[1] = new ButtonGroup();
        for (int i = 0; i < menus.length; i++) {
            menus[i] = new JMenu(menuStrings[i]);
            menuBar.add(menus[i]);
        }
        for (int i = 0; i < menuItemStrings.length; i++) {
            for (int j = 0; j < menuItemStrings[i].length; j++) {
                if (i != 2 && i != 3) {
                    menuItems[i][j] = new JMenuItem(menuItemStrings[i][j]);
                    if (keyCommand[i].length != 0 && j < keyCommand[i].length) {
                        menuItems[i][j].setMnemonic((int) keyCommand[i][j]);
                        if (i == 4 && j > 0) {
                            menuItems[i][j].setAccelerator(KeyStroke.getKeyStroke((int) keyCommand[i][j], (ActionEvent.ALT_MASK + ActionEvent.CTRL_MASK)));
                            subMenu.add(menuItems[i][j]);
                        } else {
                            menuItems[i][j].setAccelerator(KeyStroke.getKeyStroke((int) keyCommand[i][j], ActionEvent.CTRL_MASK));
                            menus[i].add(menuItems[i][j]);
                        }
                    }

                } else {
                    radioButtonMenuItems[i - 2][j] = new JRadioButtonMenuItem(menuItemStrings[i][j]);
                    /*if (keyCommand[i].length != 0 && j < keyCommand[i].length) {
                        menuItems[i][j].setMnemonic((int) keyCommand[i][j]);
                        menuItems[i][j].setAccelerator(KeyStroke.getKeyStroke((int) keyCommand[i][j], ActionEvent.SHIFT_MASK));
                    }*/
                    group[i - 2].add(radioButtonMenuItems[i - 2][j]);
                    menus[i].add(radioButtonMenuItems[i - 2][j]);
                }
            }
            menus[4].add(subMenu);
        }


        menuItems[1][0].setEnabled(false);
        menuItems[1][1].setEnabled(false);
        menuItems[1][2].setEnabled(false);


        return menuBar;
    }

    public static void fillAction(MenuBar menuBar) {
        menuBar.menuItems[1][0].addActionListener(e -> menuBar.action.pressMenuItems(0));//command START
        menuBar.menuItems[1][1].addActionListener(e -> menuBar.action.pressMenuItems(1));//command STEP
        menuBar.menuItems[1][2].addActionListener(e -> menuBar.action.pressMenuItems(2));//command STOP
        menuBar.menuItems[0][0].addActionListener(e -> menuBar.action.pressMenuItems(4));//command RESET (OUTPUT)

        menuBar.radioButtonMenuItems[1][0].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Step by step");
                menuBar.action.setSelected(0);
            }
        });
        menuBar.radioButtonMenuItems[1][0].setSelected(true);

        menuBar.radioButtonMenuItems[1][1].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Slow");
                menuBar.action.setSelected(1);
            }

        });
        menuBar.radioButtonMenuItems[1][2].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Medium");
                menuBar.action.setSelected(2);
            }
        });
        menuBar.radioButtonMenuItems[1][3].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Fast");
                menuBar.action.setSelected(3);
            }
        });

        menuBar.menuItems[4][0].addActionListener(e -> menuBar.action.addMinusOne());
        menuBar.subMenu.getItem(0).addActionListener(e -> menuBar.action.addPower(1));
        menuBar.subMenu.getItem(1).addActionListener(e -> menuBar.action.addPower(2));
        menuBar.subMenu.getItem(2).addActionListener(e -> menuBar.action.addPower(3));
        menuBar.subMenu.getItem(3).addActionListener(e -> menuBar.action.addPower(4));
        menuBar.subMenu.getItem(4).addActionListener(e -> menuBar.action.addPower(5));
        menuBar.subMenu.getItem(5).addActionListener(e -> menuBar.action.addPower(6));
        menuBar.subMenu.getItem(6).addActionListener(e -> menuBar.action.addPower(7));
        menuBar.subMenu.getItem(7).addActionListener(e -> menuBar.action.addPower(8));
        menuBar.subMenu.getItem(8).addActionListener(e -> menuBar.action.addPower(9));
        menuBar.subMenu.getItem(9).addActionListener(e -> menuBar.action.addPower(0));
        menuBar.subMenu.getItem(10).addActionListener(e -> menuBar.action.addPower(10));


        menuBar.radioButtonMenuItems[0][0].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Sum");
                menuBar.action.setSelectedOperation(0);
            }
        });
        menuBar.radioButtonMenuItems[0][0].setSelected(true);

        menuBar.radioButtonMenuItems[0][1].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Multiplication");
                menuBar.action.setSelectedOperation(1);
            }

        });

        menuBar.radioButtonMenuItems[0][2].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Division");
                menuBar.action.setSelectedOperation(2);
            }

        });

        menuBar.radioButtonMenuItems[0][3].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Function1");
                menuBar.action.setSelectedOperation(3);
            }

        });
        menuBar.radioButtonMenuItems[0][4].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Root");
                menuBar.action.setSelectedOperation(4);
            }

        });

        menuBar.radioButtonMenuItems[0][5].addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("PythagorasFunction");
                menuBar.action.setSelectedOperation(5);
            }

        });
    }

    public static void fillerError(GUI gui, String message) {
        gui.add(gui.createTextField(new Rectangle(0, 0, 300, 65),
                false, message, true));
        gui.repaint();
    }
}
