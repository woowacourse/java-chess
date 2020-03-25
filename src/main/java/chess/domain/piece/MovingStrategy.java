package chess.domain.piece;

import java.util.Map;
import java.util.Optional;

import chess.domain.board.Position;

@FunctionalInterface
public interface MovingStrategy {
    boolean test(Position start, Position end, Map<Position, Optional<Piece>> path);

    static boolean king(Position start, Position end, Map<Position, Optional<Piece>> path) {
        return Math.pow(Position.rowGap(start, end), 2) + Math.pow(Position.columnGap(start, end), 2) <= 2;
    }

    static boolean queen(Position start, Position end, Map<Position, Optional<Piece>> path) {
        return rook(start, end, path) || bishop(start, end, path);
    }

    static boolean rook(Position start, Position end, Map<Position, Optional<Piece>> path) {
        return Position.columnGap(start, end) == 0 || Position.rowGap(start, end) == 0;
    }

    static boolean bishop(Position start, Position end, Map<Position, Optional<Piece>> path) {
        return Math.abs(Position.columnGap(start, end)) == Math.abs(Position.rowGap(start, end));
    }

    static boolean knight(Position start, Position end, Map<Position, Optional<Piece>> path) {
        return Math.pow(Position.rowGap(start, end), 2) + Math.pow(Position.columnGap(start, end), 2) == 5;
    }

    static boolean pawn(Position start, Position end, Map<Position, Optional<Piece>> path) {
        throw new UnsupportedOperationException();
    }
}
