package chess;

public class Game {
    private static int ID_INIT;

    private final int id;
    private final String idWhitePlayer;
    private final String idBlackPlayer;

    public Game(String idWhitePlayer, String idBlackPlayer) {
        ID_INIT++;
        this.id = ID_INIT;
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
