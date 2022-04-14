package domain;

import domain.piece.Bishop;
import domain.piece.Blank;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Arrays;

public enum PieceGenerator {
    KING("K") {
        @Override
        public Piece generate(Player player) {
            if (player == Player.NULL) {
                return new Blank();
            }
            return new King(player);
        }
    },
    QUEEN("Q") {
        @Override
        public Piece generate(Player player) {
            if (player == Player.NULL) {
                return new Blank();
            }
            return new Queen(player);
        }
    },
    BISHOP("B") {
        @Override
        public Piece generate(Player player) {
            if (player == Player.NULL) {
                return new Blank();
            }
            return new Bishop(player);
        }
    },
    KNIGHT("N") {
        @Override
        public Piece generate(Player player) {
            if (player == Player.NULL) {
                return new Blank();
            }
            return new Knight(player);
        }
    },
    ROOK("R") {
        @Override
        public Piece generate(Player player) {
            if (player == Player.NULL) {
                return new Blank();
            }
            return new Rook(player);
        }
    },
    PAWN("P") {
        @Override
        public Piece generate(Player player) {
            if (player == Player.NULL) {
                return new Blank();
            }
            return new Pawn(player);
        }
    },
    NULL(".") {
        @Override
        public Piece generate(Player player) {
            return new Blank();
        }
    };
    private final String symbol;

    PieceGenerator(final String symbol) {
        this.symbol = symbol;
    }

    public static PieceGenerator of(String symbol) {
        return Arrays.stream(PieceGenerator.values())
            .filter(value -> value.symbol.equals(symbol))
            .findFirst()
            .orElse(null);
    }

    public abstract Piece generate(Player player);
}
