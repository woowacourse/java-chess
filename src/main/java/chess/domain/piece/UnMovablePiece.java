package chess.domain.piece;

import chess.domain.Position;
import chess.movingStrategy.MovingStrategies;

import java.util.Collections;
import java.util.List;

public final class UnMovablePiece extends Piece {

    private UnMovablePiece(final Color color, final MovingStrategies strategies, final PieceType pieceType) {
        super(color, strategies, pieceType);
    }

    public static UnMovablePiece create() {
        MovingStrategies emptyStrategies = new MovingStrategies(Collections.emptyList());
        return new UnMovablePiece(Color.EMPTY, emptyStrategies, PieceType.EMPTY);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        throw new IllegalStateException("시작점에 기물이 존재하지 않습니다.");
    }
}
