package chess.domain.piece.strategy;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;

public class LinearMovingStrategy implements MovingStrategy {

    private final List<Direction> directions;

    public LinearMovingStrategy(List<Direction> directions) {
        this.directions = directions;
    }

    public boolean canMove(List<List<Piece>> board, Position source, Position target) {
        Direction direction = Direction.of(source, target);

        return directions.contains(direction)
                && canMovePosition(board, direction, source, target)
                && (findPiece(board, target).isEmpty() || isCapture(board, source, target));
    }

    private boolean canMovePosition(List<List<Piece>> board, Direction direction, Position source, Position target) {
        List<Piece> pathInPieces = new ArrayList<>();
        Position current = source.add(direction);
        while (!current.equals(target)) {
            pathInPieces.add(findPiece(board, current));
            current = current.add(direction);
        }

        return pathInPieces.stream()
                .allMatch(Piece::isEmpty);
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
