package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static final String DIRECTION_ERROR_MESSAGE = "Pawn이 이동할 수 있는 방향이 아닙니다";
    public static final String DISTANCE_ERROR_MESSAGE = "Pawn이 한 번에 이동할 수 있는 거리가 아닙니다";
    private static final String MOVE_FORWARD_ERROR_MESSAGE = "Pawn은 도착점에 기물이 있으면 앞으로 이동할 수 없습니다";
    private static final String MOVE_DIAGONAL_ERROR_MESSAGE = "Pawn은 대각선에 상대방이 있을 때만 이동할 수 있습니다";
    private static final Map<Color, Rank> FIRST_RANK_BY_COLOR = Map.of(Color.BLACK, Rank.SEVEN, Color.WHITE, Rank.TWO);
    private static final Map<Boolean, Integer> MAXIMUM_DISTANCE_WHEN_FIRST_MOVE_OR_NOT = Map.of(true, 2, false, 1);
    private final List<Direction> movableDirection;

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
        this.movableDirection = createMovableDirectionByColor(color);
    }

    private List<Direction> createMovableDirectionByColor(Color color) {
        if (color == Color.BLACK) {
            return List.of(Direction.BOTTOM, Direction.BOTTOM_LEFT,
                    Direction.BOTTOM_RIGHT);
        }
        return List.of(Direction.TOP, Direction.TOP_LEFT,
                Direction.TOP_RIGHT);
    }

    @Override
    public void checkMovable(Position start, Position end, Color destinationColor) {
        Direction direction = Direction.findDirectionByGap(start, end);
        checkMovableDirection(direction);
        checkMovableDistance(start, end);
        checkMovableDestination(destinationColor, direction);//

    }

    @Override
    protected void checkMovableDirection(Direction direction) {
        if (!movableDirection.contains(direction)) {
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }
    }

    private void checkMovableDistance(final Position start, final Position end) {
        List<Position> route = start.findRouteTo(end);
        if(route.size() > MAXIMUM_DISTANCE_WHEN_FIRST_MOVE_OR_NOT.get(isFirstMove(start))) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }
    }

    private boolean isFirstMove(Position start) {
        return FIRST_RANK_BY_COLOR.get(color).equals(start.getRank());
    }

    private void checkMovableDestination(final Color destinationColor, final Direction direction) {
        if (isStraightDirection(direction) && !color.isSameColor(Color.NONE)) {
            throw new IllegalArgumentException(MOVE_FORWARD_ERROR_MESSAGE);
        }
        if (isDiagonalDirection(direction) && !destinationColor.isSameColor(color.getOpponent())) {
            throw new IllegalArgumentException(MOVE_DIAGONAL_ERROR_MESSAGE);
        }
    }

    private static boolean isStraightDirection(final Direction direction) {
        return Math.abs(direction.getY()) == Direction.TOP.getY();
    }

    private boolean isDiagonalDirection(final Direction direction) {
        return Math.abs(direction.getX()) == Math.abs(direction.getY());
    }

}
