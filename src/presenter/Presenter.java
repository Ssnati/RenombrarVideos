package presenter;

import model.Model;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Presenter implements ActionListener {
    private View view;
    private Model model;

    public Presenter() {
        view = new View(this);
        model = new Model();
    }

    public static void main(String[] args) {
        new Presenter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "OPEN_FILE_CHOOSER" -> view.showJFileChooser();
            case "SAVE" -> saveChanges();
            case "ORDER_ASCEND" -> view.orderAscend();
            case "ORDER_DESCEND" -> view.orderDescend();
            case "RENAME" -> view.rename(view.getRenameTextField());
            case "ApproveSelection" -> {
                File[] files = view.getFile();
                String[] names = new String[files.length];
                for (int i = 0; i < files.length; i++) {
                    names[i] = files[i].getName();
                }
                view.fillButtons(names);
            }
        }
    }

    private void saveChanges() {
        try {
            model.saveChanges(view.getFile(), view.getNewNames());
        } catch (IOException e) {
            System.out.println("Error while saving changes" + e.getMessage());
        }
    }
}