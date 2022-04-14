package chess.model.piece;

import chess.model.board.ConsoleBoard;
import chess.model.square.Direction;
import chess.model.square.Square;
import java.util.Collections;
import java.util.List;

public abstract class PawnMovingPiece extends Piece {

    private static final int PAWN_FIRST_LINE_MAX_DISTANCE = 2;

    protected PawnMovingPiece(Color color) {
        super(color);
    }

    protected PawnMovingPiece(int id, Color color, int squareId) {
        super(id, color, squareId);
    }

    @Override
    public boolean movable(Square source, Square target) {
        return false;
    }

    @Override
    public boolean movable(ConsoleBoard consoleBoard, Square source, Square target) {
        Piece targetPiece = consoleBoard.get(target);
        if (isEnemy(targetPiece)) {
            return getDiagonalDirection().stream()
                    .anyMatch(direction -> source.findLocation(direction, target));
        }
        if (source.isPawnOnFirstLine(color)) {
            List<Square> route = getRoute(source, target);
            return !route.isEmpty();
        }
        return getDirection().stream()
                .anyMatch(direction -> source.findLocation(direction, target));
    }

    @Override
    public boolean movable(Piece targetPiece, Square source, Square target) {
        if (isEnemy(targetPiece)) {
            return getDiagonalDirection().stream()
                    .anyMatch(direction -> source.findLocation(direction, target));
        }
        if (source.isPawnOnFirstLine(color)) {
            List<Square> route = getRoute(source, target);
            return !route.isEmpty();
        }
        return getDirection().stream()
                .anyMatch(direction -> source.findLocation(direction, target));
    }

    @Override
    public boolean canMoveWithoutObstacle(ConsoleBoard consoleBoard, Square source, Square target) {
        List<Square> route = getRoute(source, target);
        if (!route.isEmpty()) {
            Piece targetPiece = consoleBoard.get(target);
            return isNotAlly(targetPiece);
        }
        return true;
    }

    public List<Square> getRoute(Square source, Square target) {
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, PAWN_FIRST_LINE_MAX_DISTANCE))
                .filter(squares -> squares.contains(target))
                .findFirst()
                .orElseGet(Collections::emptyList);
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
