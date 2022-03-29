package chess.domain.piece.strategy.pawn;

import chess.domain.piece.Piece;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class BlackPawnCaptureMovingStrategy implements MovingStrategy {

    private static final List<Direction> CAPTURABLE_DIRECTIONS = List.of(Direction.BOTTOM_LEFT, Direction.BOTTOM_RIGHT);

    @Override
    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        Direction direction = Direction.of(source, target);

        return CAPTURABLE_DIRECTIONS.contains(direction)
                && source.calculateDistance(target) == 2
                && isCapture(board, source, target);
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
