package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public class Empty extends Piece {

    private Empty(final Color color, final PieceType pieceType, final MovingStrategies strategies) {
        super(color, pieceType, strategies);
    }

    public static Empty create() {
        final MovingStrategies emptyStrategies = new MovingStrategies(Collections.emptyList());
        return new Empty(Color.EMPTY, PieceType.EMPTY, emptyStrategies);
    }

    @Override
    public List<Position> createPath(final Position source, final Position target, final MovingStrategy strategy) {
        throw new IllegalArgumentException("기물이 없는 곳을 선택하셨습니다.");
    }
}
