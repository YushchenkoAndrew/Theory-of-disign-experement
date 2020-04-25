package article.MathPackage.NeuralNetworkGUI;

import article.ActionEvent.ActionEvent;
import article.GUI.GUI;
import article.MathPackage.NeuralNetwork;

import javax.swing.*;
import java.awt.*;

public class Filler {
    public static void fillGUI(GUI gui, Action action, NeuralNetwork neuralNetwork, Draw draw) {
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLayout(null);
        gui.setAction(action);

        gui.createPane("Graph Of Errors", new Rectangle(435, 2, 750, 550), new BorderLayout());
        gui.createPane("Neural Network", new Rectangle(2, 2, 432, 550), null);
        gui.createPane("Input Output Data", new Rectangle(2, 553, 1182, 110), null);
        gui.arrayPanel[0].setColor(new Color(254, 249, 231));
        gui.arrayPanel[1].setColor(new Color(234, 250, 241));
        gui.arrayPanel[2].setColor(new Color( 235, 245, 251));

        gui.addInPanel(gui.createTextField(new Rectangle(30, 70, 50, 25), false, "Path", true), 2);
        gui.addInPanel(gui.createTextField(new Rectangle(90, 70, 350, 25), true, "src\\main\\java\\ProjectComprehendWroteDigests\\NeuralNetworkFiles\\InputData.dat", true), 2);
//        gui.addInPanel(gui.createButton(new Rectangle(450, 70, 50, 25), true, "Load"), 2);
        gui.addInPanel(gui.createLabel(new Rectangle(230, 45, 100, 25), "Input Data", true, new Color(235, 245, 251)), 2);

        gui.addInPanel(gui.createTextField(new Rectangle(680, 70, 50, 25), false, "Path", true), 2);
        gui.addInPanel(gui.createTextField(new Rectangle(740, 70, 350, 25), true, "src\\main\\java\\ProjectComprehendWroteDigests\\NeuralNetworkFiles\\OutputData.dat", true), 2);
        gui.addInPanel(gui.createButton(new Rectangle(1100, 70, 50, 25), true, "Load"), 2);
        gui.addInPanel(gui.createLabel(new Rectangle(865, 45, 150, 25), "Correct Output Data", true, new Color(235, 245, 251)), 2);

        gui.addInPanel(gui.createTextField(new Rectangle(10, 500, 50, 25), false, "Path", true), 1);
        gui.addInPanel(gui.createTextField(new Rectangle(70, 500, 290, 25), true, "src\\main\\java\\ProjectComprehendWroteDigests\\NeuralNetworkFiles\\SaveWeights.dat", true), 1);
        gui.addInPanel(gui.createButton(new Rectangle(370, 500, 50, 25), true, "Load"), 1);
        gui.addInPanel(gui.createLabel(new Rectangle(160, 475, 150, 25), "File for saving weight", true, new Color(234, 250, 241)), 1);

        gui.addInPanel(gui.createButton(new Rectangle(70, 400, 100, 25), true, "Pause"), 1);
        gui.addInPanel(gui.createButton(new Rectangle(170, 400, 100, 25), true, "Stop learning"), 1);
        gui.addInPanel(gui.createButton(new Rectangle(270, 400, 100, 25), true, "Save Weight"), 1);
        gui.addInPanel(gui.createButton(new Rectangle(350, 100, 50, 25), true, "Start"), 1);

        gui.addInPanel(gui.createLabel(new Rectangle(300, 250, 100, 20), "Total Error", true, new Color( 205, 97, 85 )), 1);
        gui.addInPanel(gui.createLabel(new Rectangle(260, 290, 150, 20), "0.0000", true, new Color( 205, 97, 85 )), 1);
        gui.label[4].setForeground(new Color(  108, 52, 131));
        gui.addInPanel(gui.createLabel(new Rectangle(250, 250, 160, 100), "", true, new Color( 205, 97, 85 )), 1);


        gui.addInPanel(gui.createLabel(new Rectangle(145, 250, 60, 20), "Cycles", true, new Color( 215, 189, 226)), 1);
        gui.addInPanel(gui.createLabel(new Rectangle(120, 290, 90, 20), "0", true, new Color( 215, 189, 226)), 1);
        gui.label[5].setForeground(new Color(  74, 35, 90));
        gui.addInPanel(gui.createLabel(new Rectangle(110, 250, 100, 100), "", true, new Color(   215, 189, 226)), 1);

        gui.addInPanel(gui.createLabel(new Rectangle(50, 20, 50, 25), "Output", true, new Color(234, 250, 241)),1);
        gui.addInPanel(gui.createLabel(new Rectangle(200, 20, 100, 25), "Wanted Result", true, new Color(234, 250, 241)),1);

        gui.addInPanel(draw,0);
        action.setDraw(draw);
        action.setNeuralNetwork(neuralNetwork);
        gui.repaint();
    }

    public static void addOutputResult(GUI gui, double[][] outputResult) {
        for (int i = 0; i < outputResult.length; i++)
            gui.addInPanel(gui.createLabel(new Rectangle(20, 45 + i * 20, 150, 25), String.valueOf(outputResult[i][0]), true, new Color(234, 250, 241)), 1);
        gui.repaint();
    }

    public static void addWantedResult(GUI gui, double[][] wantedResult) {
        for (int i = 0; i < wantedResult.length; i++)
            gui.addInPanel(gui.createLabel(new Rectangle(220, 45 + i * 20, 150, 25), String.valueOf(wantedResult[i][0]), true, new Color(234, 250, 241)), 1);

        gui.repaint();
    }

    public static void changingData(GUI gui, double[][] outputResult, double[][] wantedResult) {
        for (int i = 0; i < outputResult.length; i++) {
            gui.label[11 + i].setText(String.valueOf(outputResult[i][0]));
            gui.label[11 + outputResult.length + i].setText(String.valueOf(wantedResult[i][0]));
            if (Math.abs(outputResult[i][0] - wantedResult[i][0]) >= 0.1)
                gui.label[11 + i].setBackground(new Color( 192, 57, 43 ));
            else
                if (Math.abs(outputResult[i][0] - wantedResult[i][0]) >= 0.05)
                    gui.label[11 + i].setBackground(new Color( 186, 74, 0 ));
            else
                if (Math.abs(outputResult[i][0] - wantedResult[i][0]) >= 0.01)
                    gui.label[11 + i].setBackground(new Color(  241, 196, 15 ));
            else
                if (Math.abs(outputResult[i][0] - wantedResult[i][0]) >= 0.001)
                    gui.label[11 + i].setBackground(new Color(   247, 220, 111 ));
            else
                if (Math.abs(outputResult[i][0] - wantedResult[i][0]) >= 0.00001)
                    gui.label[11 + i].setBackground(new Color(    254, 249, 231 ));
            else gui.label[11 + i].setBackground(new Color(  234, 250, 241));


        }
    }
}
