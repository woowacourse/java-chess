package chess;

import chess.model.PieceColor;
import chess.model.Turn;

public class Game {

    private final String idWhitePlayer;
    private final String idBlackPlayer;
    private final Turn turn;

    public Game(String idWhitePlayer, String idBlackPlayer, Turn turn) {
        this.idWhitePlayer = idWhitePlayer;
        this.idBlackPlayer = idBlackPlayer;
        this.turn = turn;
    }

    public Game(String idWhitePlayer, String idBlackPlayer) {
        this(idWhitePlayer, idBlackPlayer, new Turn(PieceColor.WHITE));
    }

    public int getId() {
        return (idWhitePlayer + idBlackPlayer).hashCode();
    }

    public PieceColor getTurn() {
        return turn.getCurrentColor();
    }

    public String getIdWhitePlayer() {
        return idWhitePlayer;
    }

    public String getIdBlackPlayer() {
        return idBlackPlayer;
    }

    public void nextTurn() {
        turn.nextTurn();
    }
}
