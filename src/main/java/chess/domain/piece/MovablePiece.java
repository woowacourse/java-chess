package chess.domain.piece;

import chess.movingStrategy.MovingStrategies;

public abstract class MovablePiece extends Piece {

    public MovablePiece(final Color color, final MovingStrategies strategies) {
        super(color, strategies);

        if (color.isEmpty()) {
            throw new IllegalArgumentException("기물은 흑 또는 백이어야 합니다.");
        }
    }
}
