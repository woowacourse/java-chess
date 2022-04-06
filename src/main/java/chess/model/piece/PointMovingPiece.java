package chess.model.piece;

import chess.model.ConsoleBoard;
import chess.model.square.Square;

public abstract class PointMovingPiece extends AbstractPiece {

    protected PointMovingPiece(Color color) {
        super(color);
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
