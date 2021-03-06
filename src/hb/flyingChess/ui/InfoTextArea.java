package hb.flyingChess.ui;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JTextArea;

public class InfoTextArea extends JTextArea {
    private LinkedList<String> infoList = new LinkedList<>();

    public InfoTextArea() {
        super(4, 10);
    }

    public void addText(String text) {
        if (infoList.size() > 19) {
            infoList.removeFirst();
        }
        infoList.add(text);
        updateGui();
    }

    public LinkedList<String> getInfoList() {
        return infoList;
    }

    public void setInfo(LinkedList<String> infoList) {
        this.infoList = infoList;
        updateGui();
    }

    private void updateGui() {
        Iterator<String> it = infoList.iterator();
        setText("");
        while (it.hasNext()) {
            append(it.next()+"\n");
        }
    }
}
