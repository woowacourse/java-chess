package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

import java.util.List;
import java.util.Map;

public class Knight extends Piece {
    private static final String name = "n";
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
        Direction direction = Direction.findDirectionByGap(start, end, this);
        checkMovableDirection(direction);
        checkMovableAtOnce(start, end);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkMovableAtOnce(Position start, Position end) {
        int absGapOfColumn = start.findGapOfColum(end);
        int absGapOfRank = start.findGapOfRank(end);

        if(!isMovableAtOnce(absGapOfColumn, absGapOfRank)) {
            throw new IllegalArgumentException("knight이 한 번에 이동할 수 있는 거리가 아닙니다");
        }
    }

    private boolean isMovableAtOnce(int absGapOfColumn, int absGapOfRank) {
        return movableDistance.entrySet()
         .stream()
         .anyMatch((entry) -> entry.getKey() == absGapOfColumn && entry.getValue() == absGapOfRank);
    }

    public void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException("knight가 이동할 수 있는 방향이 아닙니다");
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if(this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException("목적지에 아군이 있으므로 Knight는 이동할 수 없습니다.");
        }

    }
}
