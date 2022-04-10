package chess;

import java.util.concurrent.atomic.AtomicInteger;

import chess.model.PieceColor;
import chess.model.Turn;

public class Game {

    private static final AtomicInteger ID_AUTO_INCREMENT = new AtomicInteger(0);

    private final String idWhitePlayer;
    private final String idBlackPlayer;
    private final Turn turn;
    private final int id;

    public Game(String idWhitePlayer, String idBlackPlayer, Turn turn, int id) {
        this.idWhitePlayer = idWhitePlayer;
        this.idBlackPlayer = idBlackPlayer;
        this.turn = turn;
        if (id == 0) {
            this.id = ID_AUTO_INCREMENT.incrementAndGet();
            return;
        }
        this.id = id;
    }

    public Game(String idWhitePlayer, String idBlackPlayer, int id) {
        this(idWhitePlayer, idBlackPlayer, new Turn(PieceColor.WHITE), id);
    }

    public int getId() {
        return id;
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
