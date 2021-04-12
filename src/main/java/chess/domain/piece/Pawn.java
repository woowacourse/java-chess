package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Pawn extends AbstractPiece {

    private static final String SYMBOL = "p";
    private static final double SCORE = 1;
    private static final int BLACK_PAWN_ROW = 6;
    private static final int WHITE_PAWN_ROW = 1;
    private static final int ABLE_LENGTH = 1;

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public String symbol() {
        return changeColorSymbol(SYMBOL);
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public Piece move(final Position position, final Map<Position, Piece> pieces) {
        validateMove(position, pieces);
        return new Pawn(color, position);
    }

    private boolean isForward(final Direction direction) {
        return direction == forward();
    }

    private List<Direction> findPawnDirection() {
        if (color.isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    private Direction forward() {
        if (color.isBlack()) {
            return Direction.SOUTH;
        }
        return Direction.NORTH;
    }

    private boolean isFirst() {
        return position.isSameRow(Point.from(WHITE_PAWN_ROW)) && color.isWhite()
            || position.isSameRow(Point.from(BLACK_PAWN_ROW)) && color.isBlack();
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Position> movablePositions(final Map<Position, Piece> pieces) {
        final List<Direction> directions = findPawnDirection();

        return positions(pieces, directions, ABLE_LENGTH);
    }

    @Override
    protected List<Position> positions(final Map<Position, Piece> pieces,
        final List<Direction> directions, final int ableLength) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : directions) {
            addMovableDirectionPositions(pieces, positions, direction);
        }

        return positions;
    }

    private void addMovableDirectionPositions(final Map<Position, Piece> pieces,
        final List<Position> positions, final Direction direction) {
        int dx = direction.getXDegree();
        int dy = direction.getYDegree();
        if (!position.isAdd(dx, dy)) {
            return;
        }
        Position movablePosition = position.addedPosition(dx, dy);
        Piece targetPiece = pieces.get(movablePosition);
        if (isForward(direction)) {
            addOneTwoForwardPosition(pieces, positions, dx, dy, movablePosition, targetPiece);
            return;
        }
        if (Objects.isNull(targetPiece) || targetPiece.isSameColor(color)) {
            return;
        }
        positions.add(movablePosition);
    }

    private void addOneTwoForwardPosition(final Map<Position, Piece> pieces, final List<Position> positions,
        final int dx, final int dy, final Position movablePosition, Piece targetPiece) {
        if (!Objects.isNull(targetPiece)) {
            return;
        }
        positions.add(movablePosition);
        if (!isFirst() || !position.isAdd(dx * 2, dy * 2)) {
            return;
        }
        Position twoForwardMovePosition = position.addedPosition(dx * 2, dy * 2);
        targetPiece = pieces.get(twoForwardMovePosition);
        if (!Objects.isNull(targetPiece)) {
            return;
        }
        positions.add(twoForwardMovePosition);
    }
}
