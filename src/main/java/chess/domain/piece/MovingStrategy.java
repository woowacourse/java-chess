package chess.domain.piece;

import chess.domain.board.Position;

@FunctionalInterface
public interface MovingStrategy {
    boolean test(Position start, Position end);

    static boolean king(Position start, Position end) {
        return true;
    }

    static boolean queen(Position start, Position end) {
        return true;
    }

    static boolean rook(Position start, Position end) {
        return true;
    }

    static boolean bishop(Position start, Position end) {
        return true;
    }

    static boolean knight(Position start, Position end) {
        return true;
    }

    static boolean pawn(Position start, Position end) {
        return true;
    }

}
