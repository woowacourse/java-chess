package chess.model.piece;

import chess.model.Board;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;
import java.util.Optional;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "q";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, 7))
                .anyMatch(road -> road.contains(target));
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

    @Override
    List<Direction> getDirection() {
        return List.of(
                Direction.EAST,
                Direction.WEST,
                Direction.SOUTH,
                Direction.NORTH,
                Direction.NORTH_EAST,
                Direction.NORTH_WEST,
                Direction.SOUTH_EAST,
                Direction.SOUTH_WEST
        );
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return 9;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
