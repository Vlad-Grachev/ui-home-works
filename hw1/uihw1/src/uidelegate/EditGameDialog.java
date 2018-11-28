package uidelegate;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGameDialog {
    private JDialog dialog;

    public EditGameDialog(Frame owner, Game game) {
        dialog = new JDialog(owner, "Edit game");
        dialog.setSize(720, 140);
        Point ownerLocation = owner.getLocation();
        dialog.setLocation(getOwnerCenter(owner));
        dialog.setModal(true);
        dialog.setResizable(false);

        JPanel up = new JPanel();
        GridLayout g1 = new GridLayout(2, 6, 8, 8);
        up.setLayout(g1);
        up.add(new Label("Name"));
        up.add(new Label("Release year"));
        up.add(new Label("Genre"));
        up.add(new Label("Publisher"));
        up.add(new Label("Count"));
        up.add(new Label("Price"));

        JTextField[] textFields = new JTextField[6];
        String[] sGame = game.toStringArray();
        JComboBox<GameGenre> genreMenu = new JComboBox(GameGenre.values());
        for(int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField();
            if (i != 2) {
                textFields[i].setText(sGame[i]);
                up.add(textFields[i]);
            } else {
                genreMenu.setSelectedItem(GameGenre.valueOf(sGame[i]));
                up.add(genreMenu);
            }
        }

        dialog.add(up, "North");
        JPanel down = new JPanel();
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
                game.setName(textFields[0].getText());
                game.setReleaseYear(Integer.parseInt(textFields[1].getText()));
                game.setGenre((GameGenre) genreMenu.getSelectedItem());
                game.setPublisher(textFields[3].getText());
                game.setQty(Integer.parseInt(textFields[4].getText()));
                game.setPrice(Double.parseDouble(textFields[5].getText()));
                closeDialog();
            }
        });
        down.add(acceptButton);
        dialog.add(down);
        dialog.setVisible(true);
    }

    private Point getOwnerCenter(Frame owner){
        Point ownerLocation = owner.getLocation();
        return new Point(ownerLocation.x + owner.getWidth() / 2 - dialog.getWidth() / 2,
                ownerLocation.y + owner.getHeight() / 2 - dialog.getHeight() / 2);
    }

    public void closeDialog(){
        if(!(dialog ==null))
            dialog.setVisible(false);
    }
}
