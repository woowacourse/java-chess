package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.Position;

import java.util.List;


public class Rook extends Piece {

    private static final String name = "r";
    public static final String MOVE_ERROR_MESSAGE = "Rook은 도착점에 아군이 있으면 이동할 수 없습니다.";
    public static final String DIRECTION_ERROR_MESSAGE = "Rook이 이동할 수 있는 방향이 아닙니다";
    private static final List<Direction> movableDirection = List.of(Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT);

    public Rook(Color color) {
        super(name, color);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        Direction direction = findDirectionToMove(start, end);

        checkMovableDirection(direction);
        checkMovableToDestination(colorOfDestination);
        return true;
    }

    public void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if(this.isSameColor(colorOfDestination)) {
           throw new IllegalArgumentException(MOVE_ERROR_MESSAGE);
        }
    }

}
