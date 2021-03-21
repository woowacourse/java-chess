package chess.domain.piece;

import chess.domain.Point;
import chess.domain.piece.kind.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.domain.piece.Color.*;
import static chess.domain.piece.kind.Pawn.INITIAL_BLACK_PAWN_ROW;
import static chess.domain.piece.kind.Pawn.INITIAL_WHITE_PAWN_ROW;

public enum PieceType {
    ROOK(Constants.INITIAL_COLUMN_OF_ROOK) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Rook(BLACK, Point.valueOf(row, column));
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Rook(WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    KNIGHT(Constants.INITIAL_COLUMN_OF_KNIGHT) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Knight(BLACK, Point.valueOf(row, column));
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Knight(WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    BISHOP(Constants.INITIAL_COLUMN_OF_BISHOP) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Bishop(BLACK, Point.valueOf(row, column));
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Bishop(WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    QUEEN(Constants.INITIAL_COLUMN_OF_QUEEN) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Queen(BLACK, Point.valueOf(row, column));
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Queen(WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    },
    KING(Constants.INITIAL_COLUMN_OF_KING) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new King(BLACK, Point.valueOf(row, column));
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new King(WHITE, Point.valueOf(row, column));
            }
            return createDefaultPieces(row, column);
        }
    };

    private final List<Integer> column;

    PieceType(List<Integer> column) {
        this.column = column;
    }

    private static Piece createDefaultPieces(int row, int column) {
        if (row == INITIAL_BLACK_PAWN_ROW) {
            return new Pawn(BLACK, Point.valueOf(row, column));
        }
        if (row == INITIAL_WHITE_PAWN_ROW) {
            return new Pawn(WHITE, Point.valueOf(row, column));
        }
        return new Empty(NOTHING, Point.valueOf(row, column));
    }

    public static Piece findPiece(int row, int column) {
        return matchColumn(column).create(row, column);
    }

    private static PieceType matchColumn(int column) {
        return Arrays.stream(PieceType.values())
                .filter(piece -> piece.column.contains(column))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public abstract Piece create(int row, int column);

    private static class Constants {
        public static final List<Integer> INITIAL_COLUMN_OF_ROOK = Arrays.asList(0, 7);
        public static final List<Integer> INITIAL_COLUMN_OF_KNIGHT = Arrays.asList(1, 6);
        public static final List<Integer> INITIAL_COLUMN_OF_BISHOP = Arrays.asList(2, 5);
        public static final List<Integer> INITIAL_COLUMN_OF_QUEEN = Collections.singletonList(3);
        public static final List<Integer> INITIAL_COLUMN_OF_KING = Collections.singletonList(4);
        private static final int INITIAL_WHITE_ROW_WITHOUT_PAWN = 7;
        private static final int INITIAL_BLACK_ROW_WITHOUT_PAWN = 0;
    }
}
