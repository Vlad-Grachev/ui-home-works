package uidelegate;

import model.GameGenre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGameDialog {
    private JDialog dialog = null;
    private String[] userInput = null;

    public AddGameDialog(Frame owner){
        dialog = new JDialog(owner, "Add game");
        dialog.setSize(720, 140);
        dialog.setLocation(getOwnerCenter(owner));
        dialog.setModal(true);
        dialog.setResizable(false);

        JPanel upPanel = new JPanel();
        GridLayout grid = new GridLayout(2, 6);//, 8, 8);
        upPanel.setLayout(grid);
        upPanel.add(new Label("Name"));
        upPanel.add(new Label("Release year"));
        upPanel.add(new Label("Genre"));
        upPanel.add(new Label("Publisher"));
        upPanel.add(new Label("Count"));
        upPanel.add(new Label("Price"));

        JTextField[] textFields = new JTextField[6];
        JComboBox<GameGenre> gengreMenu = new JComboBox(GameGenre.values());
        for(int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField();
            if (i != 2)
                upPanel.add(textFields[i]);
            else
                upPanel.add(gengreMenu);
        }
        dialog.add(upPanel, "North");

        JPanel downPanel = new JPanel();
        JButton acceptButton = new JButton("Ok");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Validator.checkYear(textFields[1].getText())){
                    textFields[1].setBackground(Color.RED);
                    return;
                }
                if(!Validator.checkQty(textFields[4].getText())){
                    textFields[4].setBackground(Color.RED);
                    return;
                }
                if(!Validator.checkPrice(textFields[5].getText())){
                    textFields[5].setBackground(Color.RED);
                    return;
                }
                userInput = new String[6];
                for(int i = 0; i < userInput.length; i++){
                    if(i != 2)
                        userInput[i] = textFields[i].getText();
                    else {
                        GameGenre selectedGenre = (GameGenre) gengreMenu.getSelectedItem();
                        userInput[i] = selectedGenre.name();
                    }
                }
                closeDialog();
            }
        });
        downPanel.add(acceptButton);
        dialog.add(downPanel);
        dialog.setVisible(true);
    }

    private Point getOwnerCenter(Frame owner){
        Point ownerLocation = owner.getLocation();
        return new Point(ownerLocation.x + owner.getWidth() / 2 - dialog.getWidth() / 2,
                ownerLocation.y + owner.getHeight() / 2 - dialog.getHeight() / 2);
    }

    public String[] getUserInput() {
        return userInput;
    }

    public void closeDialog(){
        if(!(dialog ==null))
            dialog.setVisible(false);
    }
}
