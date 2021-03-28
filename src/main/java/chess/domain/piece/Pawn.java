package chess.domain.piece;

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
        final List<Direction> directions = findPawnDirection();
        Direction direction = findDirection(position, directions, ABLE_LENGTH);
        validate(position, pieces, direction);
        return new Pawn(color, position);
    }

    private void validate(Position position, Map<Position, Piece> pieces, Direction direction) {
        if (isForward(direction) && !(Objects.isNull(pieces.get(position)))) {
            throw new IllegalArgumentException("폰은 전진하는 위치에 기물이 있으면 안됩니다.");
        }
        if (!isForward(direction) && Objects.isNull(pieces.get(position))) {
            throw new IllegalArgumentException("폰은 대각선으로 이동하기 위해서는 상대방의 기물이 있어야 합니다.");
        }
        validateObstacle(position, direction, pieces);
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

    @Override
    protected Direction findDirection(final Position position, final List<Direction> directions,
        final int ableLength) {
        return directions.stream()
            .filter(direction -> this.position.canMove(position, direction, ableLength))
            .findAny()
            .orElseGet(() -> forWordTwoMove(position, ableLength));
    }

    private Direction forWordTwoMove(final Position position, final int ableLength) {
        if (isFirst() && this.position.canMove(position, forward(), ableLength + 1)) {
            return forward();
        }
        throw new IllegalArgumentException(ERROR_CAN_NOT_MOVE);
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
    public List<Position> movablePositions(Map<Position, Piece> pieces) {
        final List<Direction> directions = findPawnDirection();

        final List<Position> positions = Positions(pieces, directions, ABLE_LENGTH);
        // 폰의 경우 여기에 2칸전진기능 추가
        return positions;
    }
}
