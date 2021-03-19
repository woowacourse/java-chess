package chess.domain.piece;

import chess.domain.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.domain.ChessGame.BLACK;
import static chess.domain.ChessGame.WHITE;

public enum Pieces {
    ROOK(Arrays.asList(0, 7)) {
        @Override
        public Piece create(int row, int column) {
            if (row == 0) {
                return new Rook("R", BLACK, Point.valueOf(row, column));
            }
            if (row == 7) {
                return new Rook("r", WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    KNIGHT(Arrays.asList(1, 6)) {
        @Override
        public Piece create(int row, int column) {
            if (row == 0) {
                return new Knight("N", BLACK, Point.valueOf(row, column));
            }
            if (row == 7) {
                return new Knight("n", WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    BISHOP(Arrays.asList(2, 5)) {
        @Override
        public Piece create(int row, int column) {
            if (row == 0) {
                return new Bishop("B", BLACK, Point.valueOf(row, column));
            }
            if (row == 7) {
                return new Bishop("b", WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    QUEEN(Collections.singletonList(3)) {
        @Override
        public Piece create(int row, int column) {
            if (row == 0) {
                return new Queen("Q", BLACK, Point.valueOf(row, column));
            }
            if (row == 7) {
                return new Queen("q", WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    KING(Collections.singletonList(4)) {
        @Override
        public Piece create(int row, int column) {
            if (row == 0) {
                return new King("K", BLACK, Point.valueOf(row, column));
            }
            if (row == 7) {
                return new King("k", WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    };

    private final List<Integer> column;

    Pieces(List<Integer> column) {
        this.column = column;
    }

    private static Piece createDefaultPieces(int row, int column) {
        if (row == 1) {
            return new Pawn("P", BLACK, Point.valueOf(row, column));
        }
        if (row == 6) {
            return new Pawn("p", WHITE, Point.valueOf(row, column));
        }
        return new Empty(".", null, Point.valueOf(row, column));
    }

    public static Piece findPiece(int row, int column) {
        return matchColumn(column).create(row, column);
    }

    private static Pieces matchColumn(int column) {
        return Arrays.stream(Pieces.values())
                .filter(piece -> piece.column.contains(column))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public abstract Piece create(int row, int column);
}
