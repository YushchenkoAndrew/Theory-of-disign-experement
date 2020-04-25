package article;
import article.ActionEvent.Action;
import article.GUI.GUI;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI(new Rectangle(400, 150, 508, 498), new Color(234, 250, 241), true);
        Action action = new Action();
        try {
            Filler.fillGUI(gui, action);
        } catch (Exception e) {
            ErrorState.ErrorPopUp("Error! Program can't set up");
        }

        System.out.println("Done!");
    }
}
