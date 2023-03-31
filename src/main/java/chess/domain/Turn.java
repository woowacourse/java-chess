package chess.domain;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;

public class Turn {
    private final Camp turn;

    public Turn() {
        this.turn = Camp.WHITE;
    }

    private Turn(Camp turn) {
        this.turn = turn;
    }

    public Turn nextTurn() {
        return new Turn(turn.getOpposite());
    }

    public boolean isMoveOrder(Piece piece) {
        return piece.isSameCamp(turn);
    }

}
