package chess.model.piece;

import chess.model.ConsoleBoard;
import chess.model.square.Square;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class LinearMovingPiece extends Piece {

    private static final int LINEAR_MOVING_PIECE_MAX_DISTANCE = 7;

    protected LinearMovingPiece(Color color) {
        super(color);
    }

    protected LinearMovingPiece(Color color, int squareId) {
        super(0, color, squareId);
    }

    public LinearMovingPiece(int id, Color color, int squareId) {
        super(id, color, squareId);
    }

    @Override
    public boolean movable(Square source, Square target) {
        List<Square> route = getRoute(source, target);
        return !route.isEmpty();
    }

    @Override
    public boolean canMoveWithoutObstacle(ConsoleBoard consoleBoard, Square source, Square target) {
        Piece targetPiece = consoleBoard.get(target);
        List<Square> route = getRoute(source, target);
        if (route.isEmpty()) {
            return false;
        }
        return checkEachSquare(consoleBoard, targetPiece, route);
    }

    private boolean checkEachSquare(ConsoleBoard consoleBoard, Piece targetPiece, List<Square> route) {
        for (Square square : route) {
            Piece tempPiece = consoleBoard.get(square);
            if (tempPiece.equals(targetPiece) && isNotAlly(targetPiece)) {
                return true;
            }
            if (tempPiece.isNotEmpty()) {
                return false;
            }
        }
        return false;
    }

    public List<Square> getRoute(Square source, Square target) {
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, LINEAR_MOVING_PIECE_MAX_DISTANCE))
                .filter(squares -> squares.contains(target))
                .findFirst()
                .orElseGet(Collections::emptyList);
    }
}
