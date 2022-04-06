package chess.model.piece;

import chess.model.ConsoleBoard;
import chess.model.square.Square;
import java.util.List;
import java.util.Optional;

public abstract class LinearMovingPiece extends AbstractPiece {

    private static final int LINEAR_MOVING_PIECE_MAX_DISTANCE = 7;

    protected LinearMovingPiece(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Square source, Square target) {
        Optional<List<Square>> route = getRoute(source, target);
        return route.isPresent();
    }

    @Override
    public boolean canMoveWithoutObstacle(ConsoleBoard consoleBoard, Square source, Square target) {
        Piece targetPiece = consoleBoard.get(target);
        Optional<List<Square>> route = getRoute(source, target);
        if (route.isEmpty()) {
            return false;
        }
        return checkEachSquare(consoleBoard, targetPiece, route.get());
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

    private Optional<List<Square>> getRoute(Square source, Square target) {
        return getDirection().stream()
                .map(direction -> source.findRoad(direction, LINEAR_MOVING_PIECE_MAX_DISTANCE))
                .filter(squares -> squares.contains(target))
                .findFirst();
    }
}
