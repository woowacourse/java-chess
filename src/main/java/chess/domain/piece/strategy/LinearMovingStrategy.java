package chess.domain.piece.strategy;

import chess.domain.Board;
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

    public boolean canMove(Board board, Position source, Position target) {
        Direction direction = Direction.of(source, target);

        return directions.contains(direction)
                && canMovePosition(board, direction, source, target)
                && (board.findPiece(target).isEmpty() || isCapture(board, source, target));
    }

    private boolean canMovePosition(Board board, Direction direction, Position source, Position target) {
        List<Piece> pathInPieces = new ArrayList<>();
        Position current = source.add(direction);
        while (!current.equals(target)) {
            pathInPieces.add(board.findPiece(current));
            current = current.add(direction);
        }

        return pathInPieces.stream()
                .allMatch(Piece::isEmpty);
    }

    private boolean isCapture(Board board, Position source, Position target) {
        Piece sourcePiece = board.findPiece(source);
        Piece targetPiece = board.findPiece(target);
        return !targetPiece.isEmpty() && !sourcePiece.isSameColor(targetPiece);
    }
}
