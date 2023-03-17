package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

import java.util.List;


public class Rook extends Piece {

    private static final String name = "r";
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
            throw new IllegalArgumentException("rook이 이동할 수 있는 방향이 아닙니다");
        }
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if(this.isSameColor(colorOfDestination)) {
           throw new IllegalArgumentException("목적지에 아군이 있으므로 rook는 이동할 수 없습니다.");
        }
    }
}
