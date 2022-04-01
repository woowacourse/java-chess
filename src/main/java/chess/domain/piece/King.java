package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.DiagonalDirection;
import chess.domain.position.direction.Direction;
import chess.domain.position.direction.HorizontalDirection;
import chess.domain.position.direction.VerticalDirection;
import java.util.List;

public class King extends AbstractPiece {

    private final List<Direction> directions;

    public King(PieceColor pieceColor) {
        super(pieceColor, PieceType.KING);
        this.directions = List.of(new DiagonalDirection(), new HorizontalDirection(), new VerticalDirection());
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useKingStrategy(from, to);
    }

    private boolean useKingStrategy(Position from, Position to) {
        if (from.isFarFromMoreThanOne(to)) {
            return false;
        }
        return matchDirection(from, to);
    }

    private boolean matchDirection(Position from, Position to) {
        return directions.stream()
                .anyMatch(direction -> direction.isOnDirection(from, to));
    }
}
