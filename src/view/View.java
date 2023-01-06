package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class View extends JFrame {
    private MainPanel mainPanel;
    private final ActionListener actionListener;
    public View(ActionListener actionListener) {
        super("View");
        this.actionListener = actionListener;
        initComponent();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(452,506);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponent() {
        mainPanel = new MainPanel(actionListener);
        add(mainPanel);
    }


    public void showJFileChooser() {
        mainPanel.showJFileChooser();
    }

    public File[] getFile() {
        return mainPanel.getFileChooser().getSelectedFiles();
    }

    public void fillButtons(String[] names) {
        mainPanel.fillButtons(names);
        mainPanel.updateUI();
    }

    public void orderAscend() {
        mainPanel.orderAscend();
        mainPanel.updateUI();
        mainPanel.getButtonPanel().updateUI();
    }

    public void orderDescend() {
        mainPanel.orderDescend();
        mainPanel.updateUI();
        mainPanel.getButtonPanel().updateUI();
    }

    public String getRenameTextField() {
        return mainPanel.getRenameTextField().getText();
    }

    public void rename(String renameTextField) {
        mainPanel.rename(renameTextField);
        mainPanel.updateUI();
        mainPanel.getButtonPanel().updateUI();
    }

    public String[] getNewNames() {
        return mainPanel.getNewNames();
    }
}
