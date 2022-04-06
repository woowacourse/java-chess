package chess;

public class Game {

    private final int id;
    private final String idWhitePlayer;
    private final String idBlackPlayer;

    public Game(String idWhitePlayer, String idBlackPlayer) {
        this.id = (idWhitePlayer + idBlackPlayer).hashCode();
        this.idWhitePlayer = idWhitePlayer;
        this.idBlackPlayer = idBlackPlayer;
    }

    public int getId() {
        return id;
    }

    public String getIdWhitePlayer() {
        return idWhitePlayer;
    }

    public String getIdBlackPlayer() {
        return idBlackPlayer;
    }
}
