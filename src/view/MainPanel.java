package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainPanel extends JPanel {
    private JFileChooser fileChooser;
    private JButton openFileChooserButton;
    private JButton saveChangesButton;
    private JButton removeNumbersButton;
    private JButton orderAscendButton;
    private JButton orderDescendButton;
    private JPanel buttonPanel;
    private JTextField renameTextField;
    private List<JButton> buttons;
    private final ActionListener actionListener;

    public MainPanel(ActionListener actionListener) {
        buttons = new ArrayList<>();
        this.actionListener = actionListener;
        setLayout(new GridBagLayout());
        setBackground(Color.RED);
        initComponent();
    }

    public JTextField getRenameTextField() {
        return renameTextField;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    private void initComponent() {
        initRenameTextField();
        initOpenFileChooserButton();
        initPanel();
        initFileChooser();
        initRemoveNumbersButton();
        initSaveChangesButton();
        initOrderAscendButton();
        initOrderDescendButton();
    }

    private void initOrderDescendButton() {
        orderDescendButton = new JButton("Order Descend");
        orderDescendButton.setActionCommand("ORDER_DESCEND");
        orderDescendButton.addActionListener(actionListener);
        GridBagConstraints gbc = setConstrains(2, 3, 1, 1);
        gbc.insets = new Insets(10, 0, 5, 0);
        add(orderDescendButton, gbc);
    }

    private void initOrderAscendButton() {
        orderAscendButton = new JButton("Order Ascend");
        orderAscendButton.setActionCommand("ORDER_ASCEND");
        orderAscendButton.addActionListener(actionListener);
        GridBagConstraints gbc = setConstrains(1, 3, 1, 1);
        gbc.insets = new Insets(10, 10, 5, 0);
        add(orderAscendButton, gbc);
    }

    private void initRemoveNumbersButton() {
        removeNumbersButton = new JButton("Remove numbers");
        removeNumbersButton.setActionCommand("REMOVE_NUMBERS");
        removeNumbersButton.addActionListener(actionListener);
        GridBagConstraints gbc = setConstrains(0, 3, 1, 1);
        gbc.insets = new Insets(10, 0, 5, 0);
        add(removeNumbersButton, gbc);
    }

    private void initSaveChangesButton() {
        saveChangesButton = new JButton("Save Changes");
        saveChangesButton.setActionCommand("SAVE");
        saveChangesButton.addActionListener(actionListener);
        add(saveChangesButton, setConstrains(0, 4, 3, 1));
    }

    private void initRenameTextField() {
        renameTextField = new JTextField();
        renameTextField.setPreferredSize(new Dimension(200, 30));
        renameTextField.addActionListener(actionListener);
        renameTextField.setActionCommand("RENAME");
        GridBagConstraints gbc = setConstrains(0, 0, 2, 1);
        gbc.insets = new Insets(5, 5, 5, 5);
        add(renameTextField, gbc);
    }

    private void initPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLUE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(8);
        scrollPane.setPreferredSize(new Dimension(400, 350));
        add(scrollPane, setConstrains(0, 1, 3, 1));
    }

    private GridBagConstraints setConstrains(int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        return constraints;
    }

    private void initOpenFileChooserButton() {
        openFileChooserButton = new JButton("Abrir");
        openFileChooserButton.addActionListener(actionListener);
        openFileChooserButton.setActionCommand("OPEN_FILE_CHOOSER");
        add(openFileChooserButton, setConstrains(2, 0, 1, 1));
    }

    public void fillButtons(String[] names) {
        buttonPanel.removeAll();
        buttons.clear();
        for (String name : names) {
            JButton button = new JButton(name);
            buttons.add(button);
            button.setFocusable(false);
            buttonPanel.add(button);
        }
    }

    private void initFileChooser() {
        fileChooser = new JFileChooser("D:/Uk");
        fileChooser.addActionListener(actionListener);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".mp4") || f.getName().toLowerCase().endsWith(".mkv") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Archivos de video";
            }
        });
        fileChooser.setMultiSelectionEnabled(true);
    }

    public void showJFileChooser() {
        fileChooser.setCurrentDirectory(new File("D:/Uk"));
        fileChooser.setSelectedFiles(null);
        fileChooser.showOpenDialog(this);
    }

    public void orderAscend() {
        buttons.sort(Comparator.comparing(o -> o.getText().substring(o.getText().length() - 8)));
        fillButtons(getNewNames());
    }

    public void orderDescend() {
        buttons.sort(Comparator.comparing(o -> o.getText().substring(o.getText().length() - 8)));
        Collections.reverse(buttons);
        fillButtons(getNewNames());
    }

    public String[] getNewNames() {
        String[] names = new String[buttons.size()];
        for (int i = 0; i < buttons.size(); i++) names[i] = buttons.get(i).getText();
        return names;
    }

    public void rename(String renameTextField) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(renameTextField + " " + (i + 1) + buttons.get(i).getText().substring(buttons.get(i).getText().length() - 4));
        }
    }
}
