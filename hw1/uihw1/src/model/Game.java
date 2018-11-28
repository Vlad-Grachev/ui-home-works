package model;

import java.io.Serializable;
import java.util.Objects;

public class Game implements Serializable {
    private String name;
    private int releaseYear;
    private GameGenre genre;
    private String publisher;
    private int qty;
    private double price;

    public Game(String name, int releaseYear, GameGenre genre, String publisher, int qty, double price) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.publisher = publisher;
        this.qty = qty;
        this.price = price;
    }

    public Game(String[] sGame){
        if(sGame.length == 6) {
            this.name = sGame[0];
            this.releaseYear = Integer.parseInt(sGame[1]);
            this.genre = GameGenre.valueOf(sGame[2]);
            this.publisher = sGame[3];
            this.qty = Integer.parseInt(sGame[4]);
            this.price = Double.parseDouble(sGame[5]);
        }
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public GameGenre getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(GameGenre genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) &&
                Objects.equals(publisher, game.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, publisher);
    }

    public String[] toStringArray(){
        String[] sGame = new String[6];
        sGame[0] = name;
        sGame[1] = "" + releaseYear;
        sGame[2] = genre.name();
        sGame[3] = publisher;
        sGame[4] = "" + qty;
        sGame[5] = "" + price;
        return sGame;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre=" + genre +
                ", publisher='" + publisher + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
