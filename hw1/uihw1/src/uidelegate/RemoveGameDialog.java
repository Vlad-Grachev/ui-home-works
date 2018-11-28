package uidelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveGameDialog {
    private JDialog dialog = null;
    private String userInput = null;

    public RemoveGameDialog(Frame owner) {
        dialog = new JDialog(owner, "Remove game");
        dialog.setSize(240, 100);
        dialog.setLocation(getOwnerCenter(owner));
        dialog.setModal(true);
        dialog.setResizable(false);

        dialog.add(new JLabel("Removable game name"), "North");
        JPanel jPanel = new JPanel();
        JTextField jTextField = new JTextField();
        JButton acceptButton = new JButton("Ok");
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!jTextField.equals("")) {
                    userInput = jTextField.getText();
                    closeDialog();
                }
            }
        });

        dialog.add(jTextField);//, "North");
        dialog.add(acceptButton, "South");
        dialog.setVisible(true);
    }

    private Point getOwnerCenter(Frame owner){
        Point ownerLocation = owner.getLocation();
        return new Point(ownerLocation.x + owner.getWidth() / 2 - dialog.getWidth() / 2,
                ownerLocation.y + owner.getHeight() / 2 - dialog.getHeight() / 2);
    }

    public String getUserInput() {
        return userInput;
    }

    public void closeDialog(){
        if(!(dialog ==null))
            dialog.setVisible(false);
    }
}
