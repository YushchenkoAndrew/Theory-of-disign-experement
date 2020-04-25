package article.ActionEvent;

import article.GUI.*;
import article.MainStructure;

import javax.swing.*;

public abstract class ActionEvent {
    public GUI gui;
    public MainStructure structure;

    /**
     * Method call in(for) simulation mode
     *
     * @param index describe which command selected
     */
    public void pressMenuItems(int index) {

    }

    public void pressComboBox(int index) throws Exception {

    }

    /**
     * Method need to create generation of steps by person or automatic
     */
    public void selectedRadioButtonMenuItems() {

    }

    public void setStructure(MainStructure mainStructure) {
        structure = mainStructure;
    }

    public void setSelected(int selected) {

    }

    /**
     * Method fill gui with special index and set index for selected operation
     *
     * @param index
     */
    public void setSelectedOperation(int index) {

    }

    public  void setGUI(GUI gui) {
        this.gui = gui;
    }

    /**
     * Method call when person press buttons
     */
    public void pressButton(int index) throws Exception {

    }

    public void pressedRadioButton(int index){

    }

    public void mouseClicked() {

    }

    public void mousePressed() {

    }

    public void mouseReleased() {

    }

    public void mouseEntered() {

    }

    public void mouseExited() {

    }

    /**
     * Method add -1 in selected textField
     */
    public void addMinusOne() {

    }

    /**
     * Method add power in selected textField
     *
     * @param index
     */
    public void addPower(int index) {

    }
}
