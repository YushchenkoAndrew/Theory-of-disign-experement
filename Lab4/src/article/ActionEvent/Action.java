package article.ActionEvent;

import article.ConcurrencyMode.ConcurrencyMode;
import article.CorrectionPrint;
import article.Filler;
import article.GUI.*;

import javax.swing.*;

/*
 *  This class using for transferred data.
 * Using with changing data
 */

public class Action extends ActionEvent {


    /**
     * Class for bin calculation
     */
    private ConcurrencyMode concurrencyMode = new ConcurrencyMode();

    /**
     * Get exemplar of already created class
     */


    public void setGUI(GUI gui) {
        super.setGUI(gui);
    }

    /**
     * Method call in(for) simulation mode
     *
     * @param index describe which command selected
     */
    public void pressMenuItems(int index) {
        switch (index) {
            case 0: {        //command START
                gui.menuBar.commandStart();
//                try {
                    concurrencyMode.makeOperations(selectedOperation);
//                } catch (Exception ex) {
//                    ErrorState.ErrorPopUp("Error! Something went wrong during calculation");
//                }

                if (selected != 0)
                    selectedRadioButtonMenuItems();

                gui.tables[0].reset();
                pressMenuItems(3);
                break;
            }
            case 1: {        //command STEP
                if (timer != null)
                    timer.stop();
                selectedRadioButtonMenuItems();

                System.out.println("Step");
                break;
            }
            case 2: {        //command STOP
                gui.menuBar.commandStop();
                if (timer != null)
                    timer.stop();
                break;
            }
            case 3: {        //command RESET (program)
                i = 0;
                j = 0;
                counter = 1;
                gui.textFields[15].setText("");
                gui.textFields[17].setText(operationString[selectedOperation][j]);
                gui.textFields[19].setText(Integer.toString(counter++));

                gui.textFields[5].setText("00000000");
                gui.textFields[7].setText("0000.0000");
                gui.textFields[9].setText("0000.0000");

                break;
            }
            case 4: {        //command RESET (OUTPUT)
                pressMenuItems(3);
                pressMenuItems(2);
                break;
            }
        }

    }

    private int selectedOperation;
    private boolean needRemoving = false;

    /**
     * Method fill gui with special index and set index for selected operation
     */
    public void setSelectedOperation(int index) {
        selectedOperation = index;

        if (needRemoving) {
            Filler.removeNotUsingComponents(gui.arrayPanel, gui.textFields, gui.tables[0]);
            System.out.println("Cleaning!");
            needRemoving = false;
        }

        switch (index) {
            case 3: {
                Filler.addNeedingComponentsFunction(gui.arrayPanel, gui.textFields, gui.tables[0]);
                needRemoving = true;
                break;
            }
            case 4: {
                Filler.addNeedingComponentsRoot(gui.arrayPanel, gui.textFields, gui.tables[0]);
                needRemoving = true;
                break;
            }
            case 5: {
                Filler.addNeedingComponentsHypotenuseFunction(gui.arrayPanel, gui.textFields, gui.tables[0]);
                needRemoving = true;
                break;
            }
        }
    }

    private int selected;

    private Timer timer;

    public void setSelected(int selected) {
        this.selected = selected;
    }

    /**
     * Method need to create generation of steps by person or automatic
     */
    public void selectedRadioButtonMenuItems() {
        switch (selected) {
            case 0: {
                step();
                break;
            }
            case 1: {
                timer = new Timer(1000, e -> step());
                timer.start();
                break;
            }
            case 2: {
                timer = new Timer(500, e -> step());
                timer.start();
                break;
            }
            case 3: {
                timer = new Timer(100, e -> step());
                timer.start();
                break;
            }
        }
    }


    private int i, j, counter = 1;

    /**
     * Array of String, show current operation
     */
    private String[][] operationString = new String[][]{{"H = 2R + 2\u207B\u00B2 x + 2\u207B\u00B2 y", "z(i) = Z(H)", "R = H - z"},
            {"H = 2R + 2\u207B\u00B2Xy + 2\u207B\u00B2Yx + 2\u207B\u00B2\u207B\u2071xy", "z(i) = Z(H)", "R = H - z"},
            {"H = 2R + 2\u207B\u00B3x - Zy", "z(i) = Z(H)", "R = H - zY"},
            {"H = 2R + 2\u207Bᵖ(Ab + Ba + c) + 2\u207Bᵖ\u207B\u2071ab + Zd", "z(i) = Z(H)", "R = H - zD"},
            {"H = 2R + 2\u207B\u00B2ᵖ\u207B\u00B9x", "y(i) = Y(H)", "R = H - Yy - y\u00B22\u207B\u2071\u207B\u00B9"},
            {"H = 2R + 2\u207B\u00B2ᵖ(Xx - Yy) + 2\u207B\u00B2ᵖ\u207B\u2071\u207B\u00B9(x\u00B2 + y\u00B2)", "z(i) = Z(H)", "R = H - Zz - z\u00B22\u207B\u2071\u207B\u00B9"}};

    /**
     * Method make step in operation and add new data in window
     */
    private void step() {
        switch (j) {
            case 0: {
                String h = String.valueOf(CorrectionPrint.rightPrint(concurrencyMode.getH(), i));
                switch (selectedOperation) {
                    case 3: {
                        gui.tables[0].addRow(new String[]{Integer.toString(i + 1), concurrencyMode.getX().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getX()[i])
                                : "-", concurrencyMode.getY().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getY()[i]) : "-",
                                concurrencyMode.getC().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getC()[i]) : "-",
                                concurrencyMode.getD().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getD()[i]) : "-", h,
                                String.valueOf(CorrectionPrint.getCorrectedZ(concurrencyMode.getZ(), i, true)), String.valueOf(CorrectionPrint.rightPrint(concurrencyMode.getR(), i))});
                        break;
                    }
                    case 4: {
                        gui.tables[0].addRow(new String[]{Integer.toString(i + 1), concurrencyMode.getX().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getX()[i])
                                : "-", h, String.valueOf(CorrectionPrint.getCorrectedZ(concurrencyMode.getZ(), i, true)), String.valueOf(CorrectionPrint.rightPrint(concurrencyMode.getR(), i))});
                        break;
                    }
                    default: {
                        gui.tables[0].addRow(new String[]{Integer.toString(i + 1), concurrencyMode.getX().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getX()[i])
                                : "-", concurrencyMode.getY().length > i ? gui.tables[0].correctionTableString(concurrencyMode.getY()[i]) : "-", h,
                                String.valueOf(CorrectionPrint.getCorrectedZ(concurrencyMode.getZ(), i, true)), String.valueOf(CorrectionPrint.rightPrint(concurrencyMode.getR(), i))});
                        break;
                    }
                }
                gui.textFields[9].setText(h);
                break;
            }
            case 1: {
                gui.textFields[selectedOperation != 4 ? 5 : 3].setText(CorrectionPrint.getCorrectedZ(concurrencyMode.getInComputerCoding(concurrencyMode.getZ()), i));
                break;
            }
            case 2: {
                gui.textFields[7].setText(String.valueOf(CorrectionPrint.rightPrint(concurrencyMode.getR(), i)));
                i++;
                if (i == concurrencyMode.getZ().length) {
                    pressMenuItems(2);
                    if (selected != 0)
                        timer.stop();
                }
                break;
            }
        }

        gui.textFields[19].setText(Integer.toString(counter++));
        gui.textFields[15].setText(operationString[selectedOperation][j]);

        j++;
        j = j == operationString[selectedOperation].length ? 0 : j;

        gui.textFields[17].setText(operationString[selectedOperation][j]);
    }

    /**
     * Method call when person press buttons
     */
    public void pressButton(int index) throws Exception {
        String x = gui.textFields[11].deleteTrash();
        String y;
        switch (selectedOperation) {
            case 3: {
                y = gui.textFields[13].deleteTrash();
                String c = gui.textFields[21].deleteTrash();
                String d = gui.textFields[23].deleteTrash();
                String p = gui.textFields[25].getText();

                concurrencyMode.setData(x, y, c, d, Integer.valueOf(p), new int[]{gui.textFields[11].getExponent(), gui.textFields[13].getExponent(), gui.textFields[21].getExponent(),
                        gui.textFields[23].getExponent()});
                gui.textFields[27].setText(concurrencyMode.getInComputerCoding(concurrencyMode.getC()));
                gui.textFields[29].setText(concurrencyMode.getInComputerCoding(concurrencyMode.getD()));
                gui.textFields[3].setText(concurrencyMode.getInComputerCoding(concurrencyMode.getY()));
                break;
            }
            case 4: {
                concurrencyMode.setData(x, Integer.valueOf(gui.textFields[13].getText()));
                break;
            }
            case 5: {
                y = gui.textFields[13].deleteTrash();
                concurrencyMode.setData(x, y, Integer.valueOf(gui.textFields[21].getText()), new int[]{gui.textFields[11].getExponent(), gui.textFields[13].getExponent()});
                gui.textFields[3].setText(concurrencyMode.getInComputerCoding(concurrencyMode.getY(), 2));
                break;
            }
            default: {
                y = gui.textFields[13].deleteTrash();
                concurrencyMode.setData(x, y, 0, new int[]{gui.textFields[11].getExponent(), gui.textFields[13].getExponent()});
                gui.textFields[3].setText(concurrencyMode.getInComputerCoding(concurrencyMode.getY()));
                break;
            }
        }
        gui.textFields[1].setText(concurrencyMode.getInComputerCoding(concurrencyMode.getX(), selectedOperation == 4 || selectedOperation == 5 ? 2 : -1));
        gui.menuBar.commandStop();
    }

    /**
     * Method add -1 in selected textField
     */
    public void addMinusOne() {
        if (gui.textFields[11].isFocusOwner())
            gui.textFields[11].addMinusOne();

        if (gui.textFields[13].isFocusOwner())
            gui.textFields[13].addMinusOne();
        if (selectedOperation == 3) {
            if (gui.textFields[21].isFocusOwner())
                gui.textFields[21].addMinusOne();

            if (gui.textFields[23].isFocusOwner())
                gui.textFields[23].addMinusOne();
        }
    }

    /**
     * Method add power in selected textField
     */
    public void addPower(int index) {
        if (gui.textFields[11].isFocusOwner())
            gui.textFields[11].addPower(index);

        if (gui.textFields[13].isFocusOwner())
            gui.textFields[13].addPower(index);

        if (selectedOperation == 3) {
            if (gui.textFields[21].isFocusOwner())
                gui.textFields[21].addPower(index);

            if (gui.textFields[23].isFocusOwner())
                gui.textFields[23].addPower(index);
        }
    }

}