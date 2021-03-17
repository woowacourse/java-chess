package chess.piece;

import chess.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Pieces {
    ROOK(Arrays.asList('a', 'h')) {
        @Override
        public Piece create(char letter, int rank) {
            if (rank == 8) {
                return new Rook("R", "BLACK", Point.of(letter, rank));
            }
            if (rank == 1) {
                return new Rook("r", "WHITE", Point.of(letter, rank));
            }
            if (rank == 7) {
                return new Pawn("P", "BLACK", Point.of(letter, rank));
            }
            if (rank == 2) {
                return new Pawn("p", "WHITE", Point.of(letter, rank));
            }
            return null;
        }
    },
    KNIGHT(Arrays.asList('b', 'g')) {
        @Override
        public Piece create(char letter, int rank) {
            if (rank == 8) {
                return new Knight("N", "BLACK", Point.of(letter, rank));
            }
            if (rank == 1) {
                return new Knight("n", "WHITE", Point.of(letter, rank));
            }
            if (rank == 7) {
                return new Pawn("P", "BLACK", Point.of(letter, rank));
            }
            if (rank == 2) {
                return new Pawn("p", "WHITE", Point.of(letter, rank));
            }
            return null;
        }
    },
    BISHOP(Arrays.asList('c', 'f')){
        @Override
        public Piece create(char letter, int rank) {
            if (rank == 8) {
                return new Bishop("B", "BLACK", Point.of(letter, rank));
            }
            if (rank == 1) {
                return new Bishop("b", "WHITE", Point.of(letter, rank));
            }
            if (rank == 7) {
                return new Pawn("P", "BLACK", Point.of(letter, rank));
            }
            if (rank == 2) {
                return new Pawn("p", "WHITE", Point.of(letter, rank));
            }
            return null;
        }
    },
    QUEEN(Collections.singletonList('d')) {
        @Override
        public Piece create(char letter, int rank) {
            if (rank == 8) {
                return new Queen("Q", "BLACK", Point.of(letter, rank));
            }
            if (rank == 1) {
                return new Queen("q", "WHITE", Point.of(letter, rank));
            }
            if (rank == 7) {
                return new Pawn("P", "BLACK", Point.of(letter, rank));
            }
            if (rank == 2) {
                return new Pawn("p", "WHITE", Point.of(letter, rank));
            }
            return null;
        }
    },
    KING(Collections.singletonList('e')){
        @Override
        public Piece create(char letter, int rank) {
            if (rank == 8) {
                return new King("K", "BLACK", Point.of(letter, rank));
            }
            if (rank == 1) {
                return new King("k", "WHITE", Point.of(letter, rank));
            }
            if (rank == 7) {
                return new Pawn("P", "BLACK", Point.of(letter, rank));
            }
            if (rank == 2) {
                return new Pawn("p", "WHITE", Point.of(letter, rank));
            }
            return null;
        }
    };

    private final List<Character> column;

    Pieces(List<Character> column) {
        this.column = column;
    }

    public static Piece findPiece(char letter, int rank) {
        return matchLetter(letter).create(letter, rank);
    }

    private static Pieces matchLetter(char letter) {
        return Arrays.stream(Pieces.values())
                .filter(piece -> piece.column.contains(letter))
                .findFirst().get();
    }

    public abstract Piece create(char letter, int rank);
}
