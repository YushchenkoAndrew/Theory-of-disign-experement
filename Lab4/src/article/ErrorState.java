package article;

import article.GUI.GUI;

import java.awt.*;

public class ErrorState {

    public ErrorState(){
    }

    public static void ErrorPopUp(String message) {
        GUI gui = new GUI(new Rectangle(490, 300, 300, 100));
        Filler.fillerError(gui, message);
    }
}
