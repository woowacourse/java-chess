package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.Collections;
import java.util.List;

public class King extends Piece {

    private static final int MOVABLE_DISTANCE = 1;
    public static final String DIRECTION_ERROR_MESSAGE = "King이 이동할 수 있는 방향이 아닙니다";
    public static final String DISTANCE_ERROR_MESSAGE = "King이 한 번에 이동할 수 있는 거리가 아닙니다";
    public static final String MOVE_ERROR_MESSAGE = "King은 도착점에 아군이 있으면 이동할 수 없습니다";
    private static final List<Direction> movableDirection = List.of(
            Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT
    );

    public King(Color color) {
        super(PieceType.KING, color);
    }

    public void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }
    }

    @Override
    public void checkMovable(final Position start, final Position end, final Color destinationColor) {
        Direction direction = Direction.findDirectionByGap(start, end);
        checkMovableDirection(direction);
        checkMovableDistance(start, end);
        checkMovableToDestination(destinationColor);

    }

    @Override
    public List<Position> findRoute(final Position start, final Position end) {
        return Collections.emptyList();
    }

    public void checkMovableDistance(Position start, Position end) {
        int absGapOfColumn = Math.abs(start.findGapOfColumn(end));
        int absGapOfRank = Math.abs(start.findGapOfRank(end));

        if (absGapOfColumn > MOVABLE_DISTANCE || absGapOfRank > MOVABLE_DISTANCE) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if(this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_ERROR_MESSAGE);
        }
    }
}
