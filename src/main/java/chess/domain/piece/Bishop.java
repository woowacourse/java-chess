package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.DiagonalDirection;
import chess.domain.position.direction.Direction;
import java.util.List;

public class Bishop extends AbstractPiece {

    private final List<Direction> directions;

    public Bishop(PieceColor pieceColor) {
        super(pieceColor, PieceType.BISHOP);
        this.directions = List.of(new DiagonalDirection());
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return matchDirection(from, to);
    }

    private boolean matchDirection(Position from, Position to) {
        return directions.stream()
                .anyMatch(direction -> direction.isOnDirection(from, to));
    }
}
