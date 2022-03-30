package chess.model.piece;

import chess.Board;
import chess.Direction;
import chess.model.square.Square;
import java.util.List;
import java.util.Optional;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String name() {
        return "p";
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }

    @Override
    public boolean movable(Board board, Square source, Square target) {
        Piece targetPiece = board.get(target);
        if (isEnemy(targetPiece)) {
            return getDiagonalDirection().stream()
                    .anyMatch(direction -> source.findLocation(direction, target));
        }
        if (source.isPawnOnFirstLine(color)) {
            Optional<List<Square>> route = getRoute(source, target);
            return route.isPresent();
        }
        return getDirection().stream()
                .anyMatch(direction -> source.findLocation(direction, target));
    }

    private Optional<List<Square>> getRoute(Square source, Square target) {
        for (int i = 0; i < 2; i++) {

        }
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, 2))
                .filter(squares -> squares.contains(target))
                .findFirst();
    }

    @Override
    public boolean isObstacleOnRoute(Board board, Square source, Square target) {
        Optional<List<Square>> route = getRoute(source, target);
        if (route.isPresent()) {
            Piece targetPiece = board.get(target);
            return isNotAlly(targetPiece);
        }
        return true;
    }

    private boolean isEnemy(Piece targetPiece) {
        return !color.equals(targetPiece.color) && targetPiece.isNotEmpty();
    }

    @Override
    List<Direction> getDirection() {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH);
        }
        return List.of(Direction.NORTH);
    }

    private List<Direction> getDiagonalDirection() {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST);
        }
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public double getPoint() {
        return 1;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
