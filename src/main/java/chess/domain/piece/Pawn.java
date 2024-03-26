package chess.domain.piece;

import chess.domain.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {

    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);
    private static final int BLACK_PAWN_INITIAL_RANK = 7;
    private static final int WHITE_PAWN_INITIAL_RANK = 2;

    private Pawn(final Color color) {
        super(color);
        initDirections();
    }

    public static Pawn colorOf(final Color color) {
        if (color == Color.BLACK) {
            return BLACK_PAWN;
        }
        return WHITE_PAWN;
    }

    private void initDirections() {
        if (color == Color.BLACK) {
            this.directions.addAll(Set.of(Direction.DOWN, Direction.LEFT_DOWN, Direction.RIGHT_DOWN));
        }
        if (color == Color.WHITE) {
            this.directions.addAll(Set.of(Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP));
        }
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        List<Position> positions = new ArrayList<>();

        Direction direction = source.calculateDirection(target);
        validateDirection(direction);

        Position currentPosition = source;
        currentPosition = currentPosition.moveTowardDirection(direction);
        positions.add(currentPosition);

        if (currentPosition != target && isOnInitialPosition(source) && !direction.isDiagonal()) {
            currentPosition = currentPosition.moveTowardDirection(direction);
            positions.add(currentPosition);
        }

        validateReachability(target, currentPosition);

        return positions;
    }

    private boolean isOnInitialPosition(final Position position) {
        if (isBlack()) {
            return position.isSameRank(BLACK_PAWN_INITIAL_RANK);
        }
        return position.isSameRank(WHITE_PAWN_INITIAL_RANK);
    }

    @Override
    public String getOwnPieceTypeName() {
        return PieceType.PAWN.name();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
