package article.MathPackage.NeuralNetworkGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Draw extends JComponent {
    private List<Double> data = new ArrayList<>();
    private double max = -10000000;

    public void paintComponent(Graphics graphics) {
        super.paintComponents(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.drawLine(20, 510, 720,510);
        g.drawLine(20, 10, 20,510);

        long lastX = -1;
        long lastY = -1;
        int value = data.size() / 690 + 1;
        for (long i = 0; i < data.size(); i += value) {
            g.setColor(new Color(123, 36, 28));
            if (lastX != -1)
                g.draw(new Line2D.Float(20 + 700 / (data.size() / value) * (2 * i / value + 1) / 2, 505 - (int)(data.get((int) i) * 490 / max), lastX, lastY));
            lastX = 20 + 700 / (data.size() / value) * (2 * i / value + 1) / 2;
            lastY = 505 - (int)(data.get((int)i) * 490 / max);
//            g.setColor(Color.BLACK);
//            g.drawString(Integer.toString(i + 1), 70 + 400 / data.size() * (2 * i + 1) / 2, 270);
        }
    }

    public void setData(double data) {
        this.data.add(data);
//        if (this.data.size() > 700)
//            erase();
        if (data > max)
            max = data;
    }

    public void erase() {
        max = 0;
        System.out.println("erase!!");
        while (!this.data.isEmpty())
            this.data.remove(0);
        revalidate();
        repaint();
    }
}
