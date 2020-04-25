package article.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class Table extends JTable {
    private JTable table;
    private DefaultTableModel model;
    private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    String[] columns;
    public int counter = 0;

    public JTable createTable(Rectangle rectangle, String[] columns, boolean flag){
        table = new JTable();

        createColumns(columns);


        table.setBounds(rectangle);
        table.setEnabled(flag);
        return table;
    }
    boolean q = false;

    public void createColumns(String[] columns){
        counter = 0;
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        this.columns = columns;
        model = new DefaultTableModel();
        for(String strColumn : columns)
            model.addColumn(strColumn);

        table.setModel(model);

        for (int i = 0; i < columns.length / 2 + columns.length / 8; i++)
            table.getColumnModel().getColumn(i).setPreferredWidth(5);

        for (int i = 0; i < columns.length; i++)
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        table.repaint();
    }

    public void reset(){
//        System.out.println(counter);
        for(int i = 0; i < counter; i++)
            model.removeRow(0);
        counter = 0;
    }

    public void addRow(Object[] row){
        model.addRow(row);
        counter++;
    }

    public String correctionTableString(boolean[] z){
        if (!z[2] && !z[3])
            return "0";
        else if (!z[2])
            return "1";
        else if (!z[3])
            return "2";
        return "\u03051\u0305";
    }



    public void chooseSelectCell(int rowIndex, int colIndex, boolean toggle, boolean extend){
        table.changeSelection(rowIndex, colIndex, toggle, extend);
    }

    public String[] getColumns() {
        return columns;
    }

    public void setBounds(Rectangle rectangle) {
        table.setBounds(rectangle);
        table.repaint();
    }

    public Rectangle getBounds() {
        return table.getBounds();
    }

    /**
     * Returns {@code true} if this {@code Component} is the
     * focus owner.
     *
     * @return {@code true} if this {@code Component} is the
     * focus owner; {@code false} otherwise
     * @since 1.4
     */
    @Override
    public boolean isFocusOwner() {
        return table.isFocusOwner();
    }

    /**
     * Returns the active cell editor, which is {@code null} if the table
     * is not currently editing.
     *
     * @return the {@code TableCellEditor} that does the editing,
     * or {@code null} if the table is not currently editing.
     * @see #cellEditor
     * @see #getCellEditor(int, int)
     */
    @Override
    public TableCellEditor getCellEditor() {
        return table.getCellEditor();
    }

    /**
     * Returns the cell value at <code>row</code> and <code>column</code>.
     * <p>
     * <b>Note</b>: The column is specified in the table view's display
     * order, and not in the <code>TableModel</code>'s column
     * order.  This is an important distinction because as the
     * user rearranges the columns in the table,
     * the column at a given index in the view will change.
     * Meanwhile the user's actions never affect the model's
     * column ordering.
     *
     * @param row    the row whose value is to be queried
     * @param column the column whose value is to be queried
     * @return the Object at the specified cell
     */
    @Override
    public Object getValueAt(int row, int column) {
        return table.getValueAt(row, column);
    }
}
