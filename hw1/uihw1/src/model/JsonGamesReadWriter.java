package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;

public class JsonGamesReadWriter {
    public GameModel readGames(){
        GameModel games = null;
        try {
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new FileReader("games.json"));
            games = gson.fromJson(jsonReader, GameModel.class);
            jsonReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return games;
        }
    }

    public void writeGames(GameModel games){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonWriter jsonWriter = new JsonWriter(new FileWriter("games.json"));
            gson.toJson(games, GameModel.class, jsonWriter);
            jsonWriter.flush();
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
