package article.GUI;

import article.ActionEvent.ActionEvent;
import article.ActionEvent.MouseEvent;

import javax.swing.*;
import java.awt.*;

/*
 * This class using to create Graphic User Interface and to connected other stuff
 * with it. Also it sets connection with other class using in this work.
 */

public class GUI {
    public JFrame jFrame = new JFrame("ConcurrencyMode");
    private ActionEvent action;

    /**
     * Creating Graphic User Interface
     *
     * @param rectangle describe location and size of Graphic User Interface
     */
    public GUI(Rectangle rectangle){
        jFrame.setVisible(true);
        jFrame.setBounds(rectangle);
        jFrame.setResizable(false);
    }

    public GUI(Rectangle rectangle, Color color){
        jFrame.getContentPane().setBackground(color);
        jFrame.setVisible(true);
        jFrame.setBounds(rectangle);
        jFrame.setResizable(false);
    }

    public GUI(Rectangle rectangle, Color color, boolean IsUseMenuBar) {
        jFrame.getContentPane().setBackground(color);
        jFrame.setVisible(true);
        jFrame.setJMenuBar(IsUseMenuBar ? menuBar.createMenuBar() : null);
        jFrame.setBounds(rectangle);
        jFrame.setResizable(false);
    }

    /**
     * Refreshing components in window
     */
    public void repaint() {
        jFrame.repaint();
    }


    public void setDefaultCloseOperation (int o){
        jFrame.setDefaultCloseOperation(o);
    }

    public void setLayout(LayoutManager o){
        jFrame.setLayout(o);
    }

    public void setAction(ActionEvent action) {
        this.action = action;
        this.action.setGUI(this);
    }

    /**
     * Add new components to window
     */
    public void add(Object o) {
        jFrame.add((Component) o);
    }

    /**
     * Remove the component from the window
     */
    public void remove(Object o) {
        jFrame.remove((Component) o);
        decrementIterator(o.getClass().toString());
    }
    /**
     * Button counter
     */
    public int indexButton = 0;

    public Button[] buttons = new Button[100];

    /**
     * Method create buttons
     *
     * @param rectangle describe location and size of buttons
     * @param flag      set Button enable(access)
     */
    public JButton createButton(Rectangle rectangle, boolean flag, Icon icon){
        buttons[indexButton] = new Button();
        buttons[indexButton].connection(action);
        buttons[indexButton].createButton(rectangle, flag, "", indexButton);
        buttons[indexButton].setIcon(icon);
        return buttons[indexButton++].getjButton();
    }

    public JButton createButton(Rectangle rectangle, boolean flag, String name){
        buttons[indexButton] = new Button();
        buttons[indexButton].connection(action);
        return buttons[indexButton].createButton(rectangle, flag, name, indexButton++);
    }


    // todo {"№", "X[i]", "Y[i]", "R(i - 1)", "H", "Z"};

    public Table[] tables = new Table[10];
    public JScrollPane[] scrollPane = new JScrollPane[10];
    public int indexTable = -1;

    /**
     * Method create table
     *
     * @param rectangle describe location and size of table
     * @param flag      set Table enable(access)
     * @param columns   set columns name
     */
    public JScrollPane createTable(Rectangle rectangle, String[] columns, boolean flag) {
        tables[++indexTable] = new Table();
        scrollPane[indexTable] = new JScrollPane(tables[indexTable].createTable(rectangle, columns, flag));
        scrollPane[indexTable].setBounds(rectangle);
        return scrollPane[indexTable];
    }


    public TextField[] textFields = new TextField[100];

    /**
     * TextField counter
     */
    public int sizeTextField = 0;

    /**
     * Method create array of TextFields
     *
     * @param rectangle describe location and size of TextField
     * @param flag      set TextField enable(access)
     * @param name      set TextField name
     * @param isVisible set TextField visible
     */
    public JTextField createTextField(Rectangle rectangle, boolean flag, String name, boolean isVisible) {
        textFields[sizeTextField] = new TextField();
        textFields[sizeTextField].createTextField(rectangle, flag, name, sizeTextField, isVisible);

        return textFields[sizeTextField++].getTextField();
    }


    public Label[] label = new Label[50];

    int indexLabel = 0;

    /**
     * Method create array of Label
     *
     * @param rectangle describe location and size of TextField
     * @param name      set TextField name
     * @param isVisible set TextField visible
     */
    public JLabel createLabel(Rectangle rectangle, String name, boolean isVisible, Color color) {
        label[indexLabel] = new Label();
        label[indexLabel].createLabel(rectangle, name, isVisible, color);
        return label[indexLabel++].getLabel();
    }

    public RadioButton[] radioButtons = new RadioButton[100];

    public int indexRadioButton = 0;

    public JRadioButton createRadioButton(Rectangle rectangle, String name, boolean isVisible, Color color, int groupIndex) {
        radioButtons[indexRadioButton] = new RadioButton();
        radioButtons[indexRadioButton].connection(action);
        radioButtons[indexRadioButton].createRadioButton(rectangle, name, isVisible, indexRadioButton);

        if (color != null)
            radioButtons[indexRadioButton].setColor(color);
        addInButtonGroup(radioButtons[indexRadioButton].getRadioButton(), groupIndex);
        return radioButtons[indexRadioButton++].getRadioButton();
    }


    public ButtonGroup[] buttonGroups = new ButtonGroup[50];

    private int groupSize = 0;

    public boolean createButtonGroup() {
        buttonGroups[groupSize++] = new ButtonGroup();
        return true;
    }

    public boolean addInButtonGroup(Object o, int index) {
        buttonGroups[index].add((AbstractButton) o);
        return true;
    }

    ComboBox comboBox = new ComboBox();

    public JComboBox createComboBox(Rectangle rectangle, boolean flag, String[] items){
        comboBox.connection(action);
        comboBox.createComboBox(rectangle, flag, items);
        return comboBox.getComboBox();
    }


    public Panel[] arrayPanel = new Panel[10];
    /**
     * Panel counter
     */
    public int sizePanel = 0;

    /**
     * Method create panel
     *
     * @param rectangle describe location and size of panel
     * @param name      set panel name
     */
    public boolean createPane(String name, Rectangle rectangle) {
        arrayPanel[sizePanel] = new Panel();
        add(arrayPanel[sizePanel++].createPane(name, rectangle));
        return true;
    }

    /**
     * Method create panel
     *
     * @param rectangle describe location and size of panel
     * @param name      set panel name
     * @param layoutManager the specified layout manager
     */
    public boolean createPane(String name, Rectangle rectangle, LayoutManager layoutManager) {
        arrayPanel[sizePanel] = new Panel();
        arrayPanel[sizePanel].setLayoutManager(layoutManager);
        add(arrayPanel[sizePanel++].createPane(name, rectangle));
        return true;
    }

    //todo to continue update this method

    private void decrementIterator(String string) {
        int border = -1;
        for (int i = 0; i < string.length(); i++)
            border = string.charAt(i) == '.' ? i : border;
        StringBuilder className = new StringBuilder();
        for (int i = border + 1; i < string.length(); i++)
            className.append(string.charAt(i));

        switch (className.toString()) {
            case "JButton": {
                indexButton--;
                break;
            }
            case "JTextField" : {
                sizeTextField--;
            }
        }
    }


    /**
     * Add new components to panel
     */
    public boolean addInPanel(Object o, int index) {
        arrayPanel[index].addInPanel(o);
        return true;
    }

    public boolean removeFromPanel(Object o, int index) {
        arrayPanel[index].remove(o);
        decrementIterator(o.getClass().toString());
        return true;
    }

    public MenuBar menuBar = new MenuBar();

    public void mouseEvent() {
        MouseEvent mouse = new MouseEvent();
        mouse.connection(jFrame);
        mouse.connection(action);

        mouse.setMouseEvent();
    }

    public void updateCursor(Cursor cursor) {
        jFrame.setCursor(cursor);
    }


    /**
     * Method create menuBar
     */
    public void createMenuBar() {
        menuBar.connection(action);
    }

    public ActionEvent getAction() {
        return action;
    }


}
