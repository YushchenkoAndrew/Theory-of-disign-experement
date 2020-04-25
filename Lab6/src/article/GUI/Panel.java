package article.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel extends JPanel {
    private JPanel panel;

    public Panel(){
        panel = new JPanel();
    }

    /** Method create panel
     * @param rectangle describe location and size of panel
     * @param name set panel name
     */
    public JPanel createPane(String name, Rectangle rectangle){
        Border border = BorderFactory.createTitledBorder(name);
        panel.setBorder(border);
        panel.setBounds(rectangle);
        return panel;
    }

    /**
     * Sets the layout manager for this container.
     * <p>
     * This method changes layout-related information, and therefore,
     * invalidates the component hierarchy.
     *
     * @param mgr the specified layout manager
     * @see #doLayout
     * @see #getLayout
     * @see #invalidate
     */
    public void setLayoutManager(LayoutManager mgr) {
        panel.setLayout(mgr);
    }

    public void setColor (Color color) {
        panel.setBackground(color);
    }


    /** Remove the component from panel */
    public void remove(Object o){
        super.remove((Component) o);
        panel.remove((Component) o);
    }


    /** Add new components to panel */
    public boolean addInPanel(Object o){
        panel.add((Component) o);
        return true;
    }

    /**
     * Removes the specified component from this container.
     * This method also notifies the layout manager to remove the
     * component from this container's layout via the
     * {@code removeLayoutComponent} method.
     * <p>
     * This method changes layout-related information, and therefore,
     * invalidates the component hierarchy. If the container has already been
     * displayed, the hierarchy must be validated thereafter in order to
     * reflect the changes.
     *
     * @param comp the component to be removed
     * @throws NullPointerException if {@code comp} is {@code null}
     * @see #add
     * @see #invalidate
     * @see #validate
     * @see #remove(int)
     */
    @Override
    public void remove(Component comp) {
        panel.remove(comp);
    }

    public void setBounds(Rectangle rectangle) {
        panel.setBounds(rectangle);
    }

    public Rectangle getBounds() {
        return panel.getBounds();
    }

    public void repaintPanel() {
        panel.repaint();
    }
}
