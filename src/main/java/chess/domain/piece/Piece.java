package chess.domain.piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.Path;
import chess.domain.board.Position;

public class Piece {
    private final Type type;
    private final Side side;

    private Piece(final Type type, final Side side) {
        this.type = type;
        this.side = side;
    }

    public static Piece of(final Type type, final Side side) {
        return PieceCache.pieces.get(PieceCache.key(type, side));
    }

    public boolean is(final Type type) {
        return this.type == type;
    }

    public boolean is(final Side side) {
        return this.side == side;
    }

    public boolean is(final Type type, final Side side) {
        return is(type) && is(side);
    }

    public boolean isMovable(final Path path) {
        return type.canMoveBetween(path);
    }

    public boolean canBePlacedOn(final Position position) {
        return type.initPosition(position, side);
    }

    public boolean isEnemyOf(final Piece other) {
        return side != other.side;
    }

    public boolean isOnInitialPosition(final Position position) {
        return type.initPosition(position, side);
    }

    @Override
    public String toString() {
        if (side == Side.WHITE) {
            return type.getName();
        }
        return type.getName().toUpperCase();
    }

    public static List<Piece> getPieces() {
        return new ArrayList<>(PieceCache.pieces.values());
    }

    static class PieceCache {
        public static final Map<String, Piece> pieces;

        static {
            pieces = new HashMap<>();
            for (Type type : Type.values()) {
                for (Side side : Side.values()) {
                    pieces.put(key(type, side), new Piece(type, side));
                }
            }
        }

        private static String key(final Type type, final Side side) {
            return side.name() + type.getName();
        }
    }
}
