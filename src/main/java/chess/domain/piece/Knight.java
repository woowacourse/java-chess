package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.direction.Direction;
import chess.domain.position.direction.KnightDirection;
import java.util.List;

public class Knight extends AbstractPiece {

    private final List<Direction> directions;

    public Knight(PieceColor pieceColor) {
        super(pieceColor, PieceType.KNIGHT);
        this.directions = List.of(new KnightDirection());
    }

    private boolean useKnightStrategy(Position from, Position to) {
        return matchDirection(from, to);
    }

    private boolean matchDirection(Position from, Position to) {
        return directions.stream()
                .anyMatch(direction -> direction.isOnDirection(from, to));
    }

    @Override
    public boolean isMovable(Position from, Position to) {
        if (from == to) {
            return false;
        }

        return useKnightStrategy(from, to);
    }

    @Override
    public boolean isAbleToJump() {
        return true;
    }
}
