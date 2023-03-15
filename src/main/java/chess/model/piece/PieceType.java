package chess.model.piece;

import chess.model.Type;
import java.util.List;

public enum PieceType implements Type {

    PAWN(Direction.pawn()),
    BISHOP(Direction.bishop()),
    KNIGHT(Direction.knight()),
    ROOK(Direction.rook()),
    QUEEN(Direction.queen()),
    KING(Direction.king());

    private final List<Direction> directions;

    PieceType(final List<Direction> directions) {
        this.directions = directions;
    }

    public boolean movable(final int rank, final int file) {
        return directions.stream()
                .anyMatch(direction -> direction.match(rank, file));
    }
}
