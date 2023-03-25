package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.List;

public class Queen extends Piece {

    public static final String DIRECTION_ERROR_MESSAGE = "Queen이 이동할 수 있는 방향이 아닙니다";
    public static final String MOVE_ERROR_MESSAGE = "Queen은 도착점에 아군이 있으면 이동할 수 없습니다";
    private static final List<Direction> movableDirection = List.of(
            Direction.TOP, Direction.BOTTOM, Direction.LEFT, Direction.RIGHT,
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);


    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public void checkMovable(final Position start, final Position end, final Color destinationColor) {
        Direction direction = Direction.findDirectionByGap(start, end);
        checkMovableDirection(direction);
        checkMovableToDestination(destinationColor);
    }

    @Override
    protected void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException(DIRECTION_ERROR_MESSAGE);
        }
    }

    protected void checkMovableToDestination(Color colorOfDestination) {
        if(color.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException(MOVE_ERROR_MESSAGE);
        }
    }
}
