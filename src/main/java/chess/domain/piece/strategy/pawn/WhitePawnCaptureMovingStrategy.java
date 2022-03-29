package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class WhitePawnCaptureMovingStrategy {

    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.TOP_LEFT, Direction.TOP_RIGHT);

    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        Direction direction = Direction.of(source, target);

        return CAPTURABLE_DIRECTIONS.contains(direction)
                && calculateDistance(source, target) == 2
                && isCapture(board, source, target);
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

        return !sourcePiece.isSameColor(targetPiece);
    }

    private Piece findPiece(List<List<Piece>> board, Position position) {
        int rankIndex = position.getRankIndex();
        int fileIndex = position.getFileIndex();

        return board.get(rankIndex).get(fileIndex);
    }
}
