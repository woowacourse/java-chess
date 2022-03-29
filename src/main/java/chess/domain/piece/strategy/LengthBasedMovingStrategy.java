package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.function.IntPredicate;

public class LengthBasedMovingStrategy implements MovingStrategy {

    private final IntPredicate condition;

    public LengthBasedMovingStrategy(IntPredicate condition) {
        this.condition = condition;
    }

    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        int distance = calculateDistance(source, target);

        return condition.test(distance)
                && (findPiece(board, target).isEmpty() || isCapture(board, source, target));
    }

    private int calculateDistance(Position source, Position target) {
        int rankLength = Math.abs(source.getRankIndex() - target.getRankIndex());
        int fileLength = Math.abs(source.getFileIndex() - target.getFileIndex());

        return square(rankLength) + square(fileLength);
    }

    private int square(int value) {
        return value * value;
    }

    private boolean isCapture(List<List<Piece>> board, Position source, Position target) {
        Piece sourcePiece = findPiece(board, source);
        Piece targetPiece = findPiece(board, target);

        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
