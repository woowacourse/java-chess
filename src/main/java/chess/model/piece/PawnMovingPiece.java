package chess.model.piece;

import chess.model.Board;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.List;
import java.util.Optional;

public abstract class PawnMovingPiece extends AbstractPiece {

    private static final int PAWN_FIRST_LINE_MAX_DISTANCE = 2;

    protected PawnMovingPiece(Color color) {
        super(color);
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
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, PAWN_FIRST_LINE_MAX_DISTANCE))
                .filter(squares -> squares.contains(target))
                .findFirst();
    }

    @Override
    public boolean canMoveWithoutObstacle(Board board, Square source, Square target) {
        Optional<List<Square>> route = getRoute(source, target);
        if (route.isPresent()) {
            Piece targetPiece = board.get(target);
            return isNotAlly(targetPiece);
        }
        return true;
    }

    private List<Direction> getDiagonalDirection() {
        if (color.isBlack()) {
            return List.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST);
        }
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    private boolean isEnemy(Piece targetPiece) {
        return !color.equals(targetPiece.color()) && targetPiece.isNotEmpty();
    }
}
