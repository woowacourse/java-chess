package chess.model.piece;

import chess.Board;
import chess.Direction;
import chess.model.square.Square;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "b";
    }

    @Override
    public boolean movable(Square source, Square target) {
        Optional<List<Square>> route = getRoute(source, target);
        return route.isPresent();
    }

    private Optional<List<Square>> getRoute(Square source, Square target) {
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, 7))
                .filter(squares -> squares.contains(target))
                .findFirst();
    }

    @Override
    public boolean isObstacleOnRoute(Board board, Square source, Square target) {
        Piece targetPiece = board.get(target);
        Optional<List<Square>> route = getRoute(source, target);
        if (route.isEmpty()) {
            return false;
        }
        for (Square square : route.get()) {
            Piece tempPiece = board.get(square);
            if (tempPiece.equals(targetPiece) && isNotAlly(targetPiece)) {
                return true;
            }
            if (tempPiece.isNotEmpty()) {
                return false;
            }
        }
        return false;
    }


    List<Direction> getDirection() {
        return List.of(Direction.NORTH_WEST, Direction.SOUTH_WEST, Direction.NORTH_EAST, Direction.SOUTH_EAST);
    }

    @Override
    boolean isNotEmpty() {
        return true;
    }
}
