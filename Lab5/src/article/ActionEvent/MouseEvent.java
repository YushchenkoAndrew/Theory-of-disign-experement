package article.ActionEvent;

import article.ErrorState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class MouseEvent {
    public MouseEvent(){
    }

    public void setMouseEvent (){
        jFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    action.mouseClicked();
                } catch (Exception ex) {
                    ErrorState.ErrorPopUp("Something went wrong with mouse click!");
//                System.err.println(ex.getMessage());
//                System.err.println(ex.getCause().toString());
                }

            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                try {
                    action.mousePressed();
                } catch (Exception ex) {
                    ErrorState.ErrorPopUp("Something went wrong with mouse Pressed!");
//                System.err.println(ex.fillInStackTrace().toString());
//                ex.notify();
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                try {
                    action.mouseReleased();
                } catch (Exception ex) {
                    ErrorState.ErrorPopUp("Something went wrong with mouse Released!");
//                System.err.println(ex.getMessage());
//                System.err.println(ex.getCause().toString());
                }
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                try {
                    action.mouseEntered();
                } catch (Exception ex) {
                    ErrorState.ErrorPopUp("Something went wrong with mouse Entered!");
//                System.err.println(ex.getMessage());
//                System.err.println(ex.getCause().toString());
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {

            }
        });
    }

    private ActionEvent action;

    private JFrame jFrame;

    public void connection(ActionEvent action){
        this.action = action;
    }

    public void connection(JFrame jFrame){
        this.jFrame = jFrame;
    }

}
