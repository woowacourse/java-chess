package chess.domain.piece;

import chess.domain.piece.strategy.MoveStrategy;

abstract public class Piece {

    private Color color;
    private MoveStrategy moveStrategy;

    protected Piece(final Color color, final MoveStrategy moveStrategy) {
        this.color = color;
        this.moveStrategy = moveStrategy;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.color != color;
    }
}
