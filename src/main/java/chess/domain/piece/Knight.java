package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Knight extends Piece {
    private static final String name = "n";
    public static final String DIRECTION_ERROR_MESSAGE = "Knight가 이동할 수 있는 방향이 아닙니다";
    public static final String DISTANCE_ERROR_MESSAGE = "Knight가 한 번에 이동할 수 있는 거리가 아닙니다";
    public static final String MOVE_ERROR_MESSAGE = "Knight는 도착점에 아군이 있으면 이동할 수 없습니다.";
    private static final Map<Integer, Integer> movableDistance = Map.of(1, 2, 2, 1);
    private static final List<Direction> movableDirection = List.of(
            Direction.KNIGHT_TOP_LEFT,
            Direction.KNIGHT_TOP_RIGHT,
            Direction.KNIGHT_LEFT_TOP,
            Direction.KNIGHT_LEFT_BOTTOM,
            Direction.KNIGHT_RIGHT_TOP,
            Direction.KNIGHT_RIGHT_BOTTOM,
            Direction.KNIGHT_BOTTOM_LEFT,
            Direction.KNIGHT_BOTTOM_RIGHT);


    public Knight(Color color) {
        super(name, color);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = Direction.findDirectionByGap(start, end);

        checkMovableDirection(direction);
        checkMovableDistance(start, end);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    @Override
    public void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }
    }

    public void checkMovableDistance(Position start, Position end) {
        int distanceOfColumns = Math.abs(start.findGapOfColum(end));
        int distanceOfRanks = Math.abs(start.findGapOfRank(end));

        if(!isMovableDistance(distanceOfColumns, distanceOfRanks)) {
            throw new IllegalArgumentException(DISTANCE_ERROR_MESSAGE);
        }
    }

    private boolean isMovableDistance(int absGapOfColumn, int absGapOfRank) {
        return movableDistance.entrySet()
         .stream()
         .anyMatch(entry -> entry.getKey() == absGapOfColumn && entry.getValue() == absGapOfRank);
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if(this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_ERROR_MESSAGE);
        }

    }
}
