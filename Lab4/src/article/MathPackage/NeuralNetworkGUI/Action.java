package article.MathPackage.NeuralNetworkGUI;

import article.ActionEvent.ActionEvent;
import article.CorrectionPrint;
import article.MathPackage.NeuralNetwork;

import javax.swing.*;

public class Action extends ActionEvent {
    private NeuralNetwork neuralNetwork;
    private Timer timer;
    private Draw draw;
    private boolean flag = false;    //need for make switch between pause/resume

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
    }

    public void setDraw(Draw draw) {
        this.draw = draw;
    }

    @Override
    public void pressButton(int index) throws Exception {
        switch (index) {
            case 0: {       //button event is load corrected input file and result file
                neuralNetwork.setInputFilePath(gui.textFields[1].getText());
                neuralNetwork.setCorrectResultFilePath(gui.textFields[3].getText());
                Filler.addOutputResult(gui, neuralNetwork.getResult(neuralNetwork.getNumOfTest()));
                Filler.addWantedResult(gui, neuralNetwork.getWantedResult(neuralNetwork.getNumOfTest()));
                break;
            }
            case 2: {
                if (flag) {
                    gui.buttons[2].setText("Resume");
                    timer.stop();
                    flag = false;
                }
                else {
                    pressButton(5);
                }
                break;
            }
            case 3: {
                if (timer != null)
                    timer.stop();
                pressButton(1);
                timer = null;
                draw.erase();
//                neuralNetwork.setReadFilePath("src\\article\\MathPackage\\NeuralNetworkFiles\\Weights.dat");
                neuralNetwork.setCycleOfLearning(0);
                break;
            }
            case 5: {
                timer = new Timer(1, e -> teachNeuralNetwork());
                flag = true;
                gui.buttons[2].setText("Pause");
                timer.start();
                break;
            }
            default: {       //button event is set path of file for saving getting weights
                neuralNetwork.saveData(gui.textFields[5].getText());
                break;
            }
        }
    }

    private void teachNeuralNetwork() {
        for (int i = 0; i < 1000; i++) {
            neuralNetwork.machineLearning();
            Filler.changingData(gui, neuralNetwork.getResult(neuralNetwork.getNumOfTest()), neuralNetwork.getWantedResult(neuralNetwork.getNumOfTest()));
            if (neuralNetwork.getCycleOfLearning() % neuralNetwork.getSizeOfTest() == 0) {
                gui.label[7].setText(String.valueOf(neuralNetwork.getCycleOfLearning() / neuralNetwork.getSizeOfTest()));
                gui.label[4].setText(String.valueOf(neuralNetwork.totalError()));
                if ((neuralNetwork.getCycleOfLearning() % 8 == 0)) {
                    draw.setData(neuralNetwork.totalError());
                    repaint();
                }
            }
        }
    }

    private void repaint() {
        draw.revalidate();
        draw.repaint();
        gui.repaint();
    }
}
