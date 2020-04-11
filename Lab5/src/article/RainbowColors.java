package article;

import java.awt.*;

public class RainbowColors {
    double colorDegree = 0;
    double step = Math.PI / 4;

    public RainbowColors() {
        colorDegree = -step;
    }

    public Color getColor() {
        colorDegree += step;
        return new Color(Math.cos(colorDegree) > 0 ? (int) (255 * Math.cos(colorDegree)) : 0, (-1) * Math.cos(colorDegree) > 0 ? (int) (-255 * Math.cos(colorDegree)) : 0, Math.sin(colorDegree) > 0 ? (int) (255 * Math.sin(colorDegree)) : 0);
    }

    public void setStep(double step) {
        this.step = step;
        colorDegree = -step;
    }
}
