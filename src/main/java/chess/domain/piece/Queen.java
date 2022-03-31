package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Queen extends StraightMovablePiece {

    public static final Position BLACK_INIT_LOCATION = Position.of("d8");
    public static final Position WHITE_INIT_LOCATION = Position.of("d1");

    private static final int QUEEN_POINT = 9;

    public Queen(Color color) {
        super(color, PieceName.QUEEN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, Direction.queenDirections());
    }

    @Override
    public double getPoint() {
        return QUEEN_POINT;
    }
}
