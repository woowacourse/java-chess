package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.Direction;
import chess.domain.position.direction.HorizontalDirection;
import chess.domain.position.direction.VerticalDirection;
import java.util.List;

public class Rook extends AbstractPiece {

    private final List<Direction> directions;

    public Rook(PieceColor pieceColor) {
        super(pieceColor, PieceType.ROOK);
        this.directions = List.of(new HorizontalDirection(), new VerticalDirection());
    }

    private boolean useRookStrategy(Position from, Position to) {
        return matchDirection(from, to);
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useRookStrategy(from, to);
    }

    private boolean matchDirection(Position from, Position to) {
        return directions.stream()
                .anyMatch(direction -> direction.isOnDirection(from, to));
    }
}
