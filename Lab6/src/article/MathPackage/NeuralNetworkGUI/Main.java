package article.MathPackage.NeuralNetworkGUI;

import article.GUI.GUI;
import article.MathPackage.NeuralNetwork;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI(new Rectangle(200, 50, 1200, 700));
        Action action = new Action();
        Draw draw = new Draw();
        NeuralNetwork network = new NeuralNetwork("src\\main\\java\\ProjectComprehendWroteDigests\\NeuralNetworkFiles\\Weights.dat");
        Filler.fillGUI(gui, action, network, draw);
    }
}
