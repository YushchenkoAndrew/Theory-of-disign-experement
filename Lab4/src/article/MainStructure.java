package article;

import article.ActionEvent.Action;
import article.ActionEvent.ActionEvent;
import article.GUI.GUI;

public abstract class MainStructure {

    private int size = 1;
    public GUI[] guis = new GUI[size];
    public ActionEvent[] actionEvents = new ActionEvent[size];

    public MainStructure() {
    }

    public MainStructure(GUI gui, ActionEvent actionEvent) {
        increaseSizeOfArray();
        guis[0] = gui;
        actionEvents[0] = actionEvent;
        actionEvents[0].setStructure(this);
    }

    private void increaseSizeOfArray() {
        GUI[] storeGUI = guis.clone();
        ActionEvent[] actionEventStore = actionEvents.clone();
        guis = new GUI[size];
        actionEvents = new ActionEvent[size++];

        guis = storeGUI;
        actionEvents = actionEventStore;
    }

    public void setGuisAndActionEvent(GUI gui, ActionEvent actionEvent) {
        increaseSizeOfArray();
        guis[size - 2] = gui;
        actionEvents[size - 2] = actionEvent;
        actionEvents[size - 2].setStructure(this);
    }

    /**
     *
     * @param index describe in which GUI and Action this class need to send DATA
     */
    public void transfareData(int index) {

    }
}
