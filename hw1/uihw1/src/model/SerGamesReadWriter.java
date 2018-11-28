package model;

import java.io.*;
import java.util.ArrayList;

public class SerGamesReadWriter {
    public void writeGames(ArrayList<Game> games, String filename){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeInt(games.size());
            for(Game game: games)
                oos.writeObject(game);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readGames(ArrayList<Game> games, String filename){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            int size = ois.readInt();
            for(int i = 0; i < size; i++)
             games.add((Game)ois.readObject());
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
