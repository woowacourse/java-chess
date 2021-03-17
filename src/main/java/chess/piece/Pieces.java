package chess.piece;

import chess.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Pieces {
    ROOK(Arrays.asList(0, 7)) {
        @Override
        public Piece create(int column, int row) {
            if (row == 0) {
                return new Rook("R", "BLACK", Point.valueOf(column, row));
            }
            if (row == 7) {
                return new Rook("r", "WHITE", Point.valueOf(column, row));
            }
            if (row == 1) {
                return new Pawn("P", "BLACK", Point.valueOf(column, row));
            }
            if (row == 6) {
                return new Pawn("p", "WHITE", Point.valueOf(column, row));
            }
            return null;
        }
    },
    KNIGHT(Arrays.asList(1, 6)) {
        @Override
        public Piece create(int column, int row) {
            if (row == 0) {
                return new Knight("N", "BLACK", Point.valueOf(column, row));
            }
            if (row == 7) {
                return new Knight("n", "WHITE", Point.valueOf(column, row));
            }
            if (row == 1) {
                return new Pawn("P", "BLACK", Point.valueOf(column, row));
            }
            if (row == 6) {
                return new Pawn("p", "WHITE", Point.valueOf(column, row));
            }
            return null;
        }
    },
    BISHOP(Arrays.asList(2, 5)) {
        @Override
        public Piece create(int column, int row) {
            if (row == 0) {
                return new Bishop("B", "BLACK", Point.valueOf(column, row));
            }
            if (row == 7) {
                return new Bishop("b", "WHITE", Point.valueOf(column, row));
            }
            if (row == 1) {
                return new Pawn("P", "BLACK", Point.valueOf(column, row));
            }
            if (row == 6) {
                return new Pawn("p", "WHITE", Point.valueOf(column, row));
            }
            return null;
        }
    },
    QUEEN(Collections.singletonList(3)) {
        @Override
        public Piece create(int column, int row) {
            if (row == 0) {
                return new Queen("Q", "BLACK", Point.valueOf(column, row));
            }
            if (row == 7) {
                return new Queen("q", "WHITE", Point.valueOf(column, row));
            }
            if (row == 1) {
                return new Pawn("P", "BLACK", Point.valueOf(column, row));
            }
            if (row == 6) {
                return new Pawn("p", "WHITE", Point.valueOf(column, row));
            }
            return null;
        }
    },
    KING(Collections.singletonList(4)) {
        @Override
        public Piece create(int column, int row) {
            if (row == 0) {
                return new King("K", "BLACK", Point.valueOf(column, row));
            }
            if (row == 7) {
                return new King("k", "WHITE", Point.valueOf(column, row));
            }
            if (row == 1) {
                return new Pawn("P", "BLACK", Point.valueOf(column, row));
            }
            if (row == 6) {
                return new Pawn("p", "WHITE", Point.valueOf(column, row));
            }
            return null;
        }
    };

    private final List<Integer> column;

    Pieces(List<Integer> column) {
        this.column = column;
    }

    public static Piece findPiece(int column, int row) {
        return matchColumn(column).create(column, row);
    }

    private static Pieces matchColumn(int column) {
        return Arrays.stream(Pieces.values())
                .filter(piece -> piece.column.contains(column))
                .findFirst()
                .get();
    }

    public abstract Piece create(int column, int row);
}
