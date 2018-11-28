package uidelegate;

import model.*;

import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private MainFrame thisFrame = this;
    private JTable table;
    private GameModel gameModel;

    public MainFrame() {
        super("Game library");
        setSize(720, 540);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addButtons();
        addTable();
        setVisible(true);
    }

    private void addButtons(){
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton saveButton = new JButton("Save");
        JPanel jPanel = new JPanel();
        jPanel.add(addButton);
        jPanel.add(removeButton);
        jPanel.add(saveButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddGameDialog dialog =  new AddGameDialog(thisFrame);
                if (dialog.getUserInput() != null) {
                    gameModel.addGame(dialog.getUserInput());
                    table.updateUI();
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveGameDialog dialog =  new RemoveGameDialog(thisFrame);
                if (dialog.getUserInput() != null) {
                    gameModel.removeGame(dialog.getUserInput());
                    table.updateUI();
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.writeGames();
            }
        });
        add(jPanel, "North");
    }

    private void addTable(){
        JsonGamesReadWriter grw = new JsonGamesReadWriter();
        gameModel = grw.readGames();
        table = new JTable(gameModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.getSelectedRow();
                if(e.getClickCount() == 2 && row != -1){
                    EditGameDialog dialog = new EditGameDialog(thisFrame, gameModel.getGame(row));
                    table.updateUI();
                }
            }
        });
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);
    }
}
