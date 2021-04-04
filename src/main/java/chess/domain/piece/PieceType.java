package chess.domain.piece;

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
                return new Rook(BLACK);
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Rook(WHITE);
            }
            return createDefaultPieces(row, column);
        }
    },
    KNIGHT(Constants.INITIAL_COLUMN_OF_KNIGHT) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Knight(BLACK);
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Knight(WHITE);
            }
            return createDefaultPieces(row, column);
        }
    },
    BISHOP(Constants.INITIAL_COLUMN_OF_BISHOP) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Bishop(BLACK);
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Bishop(WHITE);
            }
            return createDefaultPieces(row, column);
        }
    },
    QUEEN(Constants.INITIAL_COLUMN_OF_QUEEN) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new Queen(BLACK);
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new Queen(WHITE);
            }
            return createDefaultPieces(row, column);
        }
    },
    KING(Constants.INITIAL_COLUMN_OF_KING) {
        @Override
        public Piece create(int row, int column) {
            if (row == Constants.INITIAL_BLACK_ROW_WITHOUT_PAWN) {
                return new King(BLACK);
            }
            if (row == Constants.INITIAL_WHITE_ROW_WITHOUT_PAWN) {
                return new King(WHITE);
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
            return new Pawn(BLACK);
        }
        if (row == INITIAL_WHITE_PAWN_ROW) {
            return new Pawn(WHITE);
        }
        return new Empty(NOTHING);
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

    public static Piece createByPieceName(Character pieceName) {
        if (pieceName == 'r') {
            return new Rook(WHITE);
        }
        if (pieceName == 'R') {
            return new Rook(BLACK);
        }
        if (pieceName == 'n') {
            return new Knight(WHITE);
        }
        if (pieceName == 'N') {
            return new Knight(BLACK);
        }
        if (pieceName == 'b') {
            return new Bishop(WHITE);
        }
        if (pieceName == 'B') {
            return new Bishop(BLACK);
        }
        if (pieceName == 'q') {
            return new Queen(WHITE);
        }
        if (pieceName == 'Q') {
            return new Queen(BLACK);
        }
        if (pieceName == 'k') {
            return new King(WHITE);
        }
        if (pieceName == 'K') {
            return new King(BLACK);
        }
        if (pieceName == 'p') {
            return new Pawn(WHITE);
        }
        if (pieceName == 'P') {
            return new Pawn(BLACK);
        }
        return new Empty(NOTHING);
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
