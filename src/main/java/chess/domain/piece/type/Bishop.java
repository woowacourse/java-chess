package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Direction> movableDirection = List.of(
            Direction.TOP_LEFT, Direction.TOP_RIGHT, Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    public void checkMovableDirection(Direction direction) {
        if(!movableDirection.contains(direction)){
            throw new IllegalArgumentException("Bishop이 이동할 수 있는 방향이 아닙니다");
        }
    }

    @Override
    public void checkMovable(final Position start, final Position end, final Color destinationColor) {
        Direction direction = Direction.findDirectionByGap(start, end);
        checkMovableDirection(direction);
        checkMovableToDestination(destinationColor);

    }

    @Override
    public List<Position> findRoute(final Position start, final Position end) {
        List<Position> route = new ArrayList<>();
        Direction direction = Direction.findDirectionByGap(start, end);
        Position currentPosition = start;

        do {
            currentPosition = currentPosition.moveDirection(direction);
            route.add(currentPosition);
        } while (!currentPosition.equals(end));
        return route;
    }

    private void checkMovableToDestination(Color colorOfDestination) {
        if(this.isSameColor(colorOfDestination)) {
            throw new IllegalArgumentException("Bishop은 도착점에 아군이 있으면 이동할 수 없습니다");
        }
    }
}
