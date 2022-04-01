package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class EmptyPiece extends Piece {

    private static EmptyPiece instance;

    private static final int EMPTY_POINT = 0;

    private EmptyPiece() {
        super(Color.EMPTY, Symbol.EMPTY);
    }

    public static EmptyPiece getInstance() {
        if (instance == null) {
            instance = new EmptyPiece();
        }
        return instance;
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(final Position position) {
        throw new IllegalStateException("해당 자리에는 말이 존재하지 않습니다.");
    }

    @Override
    public double getPoint() {
        return EMPTY_POINT;
    }

    @Override
    public Direction getPawnDirection() {
        throw new IllegalStateException("폰만 방향을 얻을 수 있습니다.");
    }
}
