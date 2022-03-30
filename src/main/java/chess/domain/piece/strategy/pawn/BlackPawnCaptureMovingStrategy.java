package chess.domain.piece.strategy.pawn;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class BlackPawnCaptureMovingStrategy implements MovingStrategy {

    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        Direction direction = Direction.of(source, target);

        return CAPTURABLE_DIRECTIONS.contains(direction)
                && source.calculateDistance(target) == 2
                && isCapture(board, source, target);
    }

    private boolean isCapture(Board board, Position source, Position target) {
        Piece sourcePiece = board.findPiece(source);
        Piece targetPiece = board.findPiece(target);

        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
    }
}
