package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(final Color color, final PieceType pieceType, final MovingStrategies strategies) {
        super(color, pieceType, strategies);
        if (color.isEmpty()) {
            throw new IllegalArgumentException("기물은 흑 또는 백이어야 합니다.");
        }
    }

    @Override
    public final List<Position> createPath(final Position source, final Position target, final MovingStrategy movingStrategy) {
        List<Position> path = new ArrayList<>();
        Position currentPosition = movingStrategy.move(source);
        while (!currentPosition.equals(target)) {
            path.add(currentPosition);
            currentPosition = movingStrategy.move(currentPosition);
        }
        return path;
    }
}
