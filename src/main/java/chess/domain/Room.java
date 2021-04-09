package chess.domain;

public class Room {

    private String name;
    private Game game;

    public Room(String name, Game game) {
        this.name = name;
        this.game = game;
    }

    public boolean isSameName(String name) {
        return this.name.equals(name);
    }

    public Game game() {
        return this.game;
    }
}
