package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;

public class Pawn extends Piece {

    public static final Row BLACK_INIT_ROW = Row.SEVEN;
    public static final Row WHITE_INIT_ROW = Row.TWO;

    private static final int PAWN_POINT = 1;

    protected Pawn(final Color color) {
        super(color, Symbol.PAWN);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return checkDirection(from, to, Direction.pawnDirection(color)) && checkPosition(from, to);
    }

    private boolean checkPosition(final Position from, final Position to) {
        final Direction direction = Direction.getDirection(from, to);
        final Position position = from.toDirection(direction);
        final List<Direction> directions = Direction.pawnStraightDirection();

        if (isFirstMove(from) && directions.contains(direction)) {
            Position additionalPosition = position.toDirection(direction);
            return position == to || additionalPosition == to;
        }
        return position.equals(to);
    }

    private boolean isFirstMove(final Position position) {
        return isBlackMovingFirst(position) || isWhiteMovingFirst(position);
    }

    private boolean isBlackMovingFirst(final Position position) {
        final List<Position> blackPawnPosition = Position.getAllPositionsOfRow(BLACK_INIT_ROW);
        return color == Color.BLACK && blackPawnPosition.contains(position);
    }

    private boolean isWhiteMovingFirst(final Position position) {
        final List<Position> whitePawnPositions = Position.getAllPositionsOfRow(WHITE_INIT_ROW);
        return color == Color.WHITE && whitePawnPositions.contains(position);
    }

    @Override
    public final double getPoint() {
        return PAWN_POINT;
    }
}
