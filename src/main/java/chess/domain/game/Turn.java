package chess.domain.game;

import chess.domain.piece.Color;

public class Turn {

    private final int turn;
    private final Color currentTurn;

    public Turn() {
        this(Color.WHITE);
    }

    public Turn(Color currentTurn) {
        this(currentTurn, 1);
    }

    public Turn(Color currentTurn, int turn) {
        this.currentTurn = currentTurn;
        this.turn = turn;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public Turn changeTurn() {
        if (currentTurn == Color.WHITE) {
            return new Turn(Color.BLACK, turn + 1);
        }
        return new Turn(Color.WHITE, turn + 1);
    }

    public int getTurn() {
        return turn;
    }
}
