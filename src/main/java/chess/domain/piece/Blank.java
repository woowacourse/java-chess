package chess.domain.piece;

import chess.domain.Position;
import chess.domain.movingStrategy.MovingStrategies;
import chess.domain.movingStrategy.MovingStrategy;

import java.util.Collections;
import java.util.List;

public class Blank extends Piece {
    private Blank(final Color color, final MovingStrategies strategies, final PieceType pieceType) {
        super(color, strategies, pieceType);
    }

    public static Blank create() {
        MovingStrategies emptyStrategies = new MovingStrategies(Collections.emptyList());
        return new Blank(Color.EMPTY, emptyStrategies, PieceType.EMPTY);
    }

    @Override
    public List<Position> createPath(final Position source, final Position target, final MovingStrategy strategy) {
        throw new IllegalArgumentException("기물이 없는 곳은 움직일 수 없습니다.");
    }
}
