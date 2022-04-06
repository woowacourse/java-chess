package chess.model.piece;

import chess.model.ConsoleBoard;
import chess.model.square.Square;

public abstract class PointMovingPiece extends Piece {

    protected PointMovingPiece(Color color) {
        super(color);
    }

    protected PointMovingPiece(Color color, int squareId) {
        super(0, color, squareId);
    }

    protected PointMovingPiece(int id, Color color, int squareId) {
        super(id, color, squareId);
    }

    @Override
    public boolean movable(Square source, Square target) {
        return getDirection().stream()
                .anyMatch(direction -> source.findLocation(direction, target));
    }

    @Override
    public boolean canMoveWithoutObstacle(ConsoleBoard consoleBoard, Square source, Square target) {
        Piece targetPiece = consoleBoard.get(target);
        return isNotAlly(targetPiece);
    }
}
