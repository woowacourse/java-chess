package chess.domain.piece;

import chess.domain.position.Direction;

import java.util.List;

public enum PieceType {
    PAWN(Direction.all()),
    ROOK(Direction.cross()),
    KNIGHT(List.of()),
    BISHOP(Direction.diagonal()),
    KING(Direction.all()),
    QUEEN(Direction.all());

    private final List<Direction> directions;

    PieceType(final List<Direction> directions) {
        this.directions = directions;
    }

    public boolean contains(final Direction direction) {
        return directions.contains(direction);
    }
}
