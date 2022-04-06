package chess;

public class Game {
    private final String idWhitePlayer;
    private final String idBlackPlayer;

    public Game(String idWhitePlayer, String idBlackPlayer) {
        this.idWhitePlayer = idWhitePlayer;
        this.idBlackPlayer = idBlackPlayer;
    }

    public String getIdWhitePlayer() {
        return idWhitePlayer;
    }

    public String getIdBlackPlayer() {
        return idBlackPlayer;
    }
}
