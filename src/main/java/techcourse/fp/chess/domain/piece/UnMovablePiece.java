package techcourse.fp.chess.domain.piece;

import java.util.Collections;
import java.util.List;
import techcourse.fp.chess.domain.Color;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.movingStrategy.MovingStrategies;

public final class UnMovablePiece extends Piece {

    private UnMovablePiece(final Color color, final MovingStrategies strategies) {
        super(color, strategies);
    }

    public static UnMovablePiece create() {
        MovingStrategies emptyStrategies = new MovingStrategies(Collections.emptyList());
        return new UnMovablePiece(Color.EMPTY, emptyStrategies);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        throw new IllegalStateException("시작점에 기물이 존재하지 않습니다.");
    }
}
