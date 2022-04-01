package chess.model;

public class NewGameModel {

    private final int id;

    public NewGameModel(int id) {
        this.id = id;
    }

    public String toJson() {
        return "{\"gameId\":" + id + "}";
    }
}
