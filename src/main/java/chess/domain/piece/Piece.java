package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

public abstract class Piece {
    private final String notation;
    private final MoveStrategy moveStrategy;

    public Piece(String notation, MoveStrategy moveStrategy) {
        this.notation = notation;
        this.moveStrategy = moveStrategy;
    }

    public String getNotation() {
        return notation;
    }
}
