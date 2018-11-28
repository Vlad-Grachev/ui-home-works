package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GameModel extends AbstractTableModel {
    private ArrayList<Game> games = new ArrayList<>();

    public GameModel() {
    }

    public void addGame(Game game){
        games.add(game);
    }

    public void addGame(String[] sGame){
        games.add(new Game(sGame));
    }

    public void removeGame(String gameName){
        int i = 0;
        while(i < games.size() && !gameName.equals(games.get(i).getName()))
            i++;
        if(i < games.size())
            games.remove(i);
    }

    public Game getGame(int index){
        return games.get(index);
    }

    public void writeGames(){
        JsonGamesReadWriter grw = new JsonGamesReadWriter();
        grw.writeGames(this);
    }

    @Override
    public String toString() {
        String result = "GameModel{";
        if(games != null) {
            for (Game game : games)
                result += game.toString() + "\n";
        }
        result += "}";
        return  result;
    }

    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Game name";
            case 1: return "Release year";
            case 2: return "Genre";
            case 3: return "Publisher";
            case 4: return "Count";
            case 5: return "Price, $";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Game.class;
            case 3: return String.class;
            case 4: return Integer.class;
            case 5: return Double.class;
        }
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Game current = games.get(rowIndex);
        switch (columnIndex){
            case 0: return current.getName();
            case 1: return current.getReleaseYear();
            case 2: return current.getGenre();
            case 3: return current.getPublisher();
            case 4: return current.getQty();
            case 5: return current.getPrice();
        }
        return null;
    }
}
