package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.function.IntPredicate;

public class LengthBasedMovingStrategy implements MovingStrategy {

    private final IntPredicate condition;

    public LengthBasedMovingStrategy(IntPredicate condition) {
        this.condition = condition;
    }

    public boolean canMove(Board board, Position source, Position target) {
        int distance = source.calculateDistance(target);

        return condition.test(distance)
                && (board.findPiece(target).isEmpty() || isCapture(board, source, target));
    }

    private boolean isCapture(Board board, Position source, Position target) {
        Piece sourcePiece = board.findPiece(source);
        Piece targetPiece = board.findPiece(target);

        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
    }
}
