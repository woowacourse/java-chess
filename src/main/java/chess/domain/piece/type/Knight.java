package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {
    public static final String DIRECTION_ERROR_MESSAGE = "Knight가 이동할 수 있는 방향이 아닙니다";
    public static final String MOVE_ERROR_MESSAGE = "Knight는 도착점에 아군이 있으면 이동할 수 없습니다";
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
        super(PieceType.KNIGHT, color);
    }


    @Override
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



    @Override
    public void checkMovable(final Position start, final Position end, final Color colorOfDestination) {

        Direction direction = Direction.findDirectionByGap(start, end);
        checkMovableDirection(direction);
        checkMovableToDestination(colorOfDestination);
    }

    @Override
    public List<Position> findRoute(final Position start, final Position end) {
        return Collections.emptyList();
    }
}
