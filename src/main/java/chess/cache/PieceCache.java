package chess.cache;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public final class PieceCache {
    private static final Row ROOK_LEFT_ROW_INDEX = Row.ROW_A;
    private static final Row KNIGHT_LEFT_ROW_INDEX = Row.ROW_B;
    private static final Row BISHOP_LEFT_ROW_INDEX = Row.ROW_C;
    private static final Row QUEEN_ROW_INDEX = Row.ROW_D;
    private static final Row KING_ROW_INDEX = Row.ROW_E;
    private static final Row BISHOP_RIGHT_ROW_INDEX = Row.ROW_F;
    private static final Row KNIGHT_RIGHT_ROW_INDEX = Row.ROW_G;
    private static final Row ROOK_RIGHT_ROW_INDEX = Row.ROW_H;
    private static final Column BLACK_PIECE_COLUMN = Column.COLUMN_8;
    private static final Column BLACK_PAWN_COLUMN = Column.COLUMN_7;
    private static final Column WHITE_PAWN_COLUMN = Column.COLUMN_2;
    private static final Column WHITE_PIECE_COLUMN = Column.COLUMN_1;
    private static final Map<Position, Piece> board = new HashMap<>();

    private PieceCache() {
    }

    static {
        addPawn(BLACK_PAWN_COLUMN, Color.BLACK);
        addRook(BLACK_PIECE_COLUMN, Color.BLACK);
        addKnight(BLACK_PIECE_COLUMN, Color.BLACK);
        addBishop(BLACK_PIECE_COLUMN, Color.BLACK);
        addQueen(BLACK_PIECE_COLUMN, Color.BLACK);
        addKing(BLACK_PIECE_COLUMN, Color.BLACK);

        addPawn(WHITE_PAWN_COLUMN, Color.WHITE);
        addRook(WHITE_PIECE_COLUMN, Color.WHITE);
        addKnight(WHITE_PIECE_COLUMN, Color.WHITE);
        addBishop(WHITE_PIECE_COLUMN, Color.WHITE);
        addQueen(WHITE_PIECE_COLUMN, Color.WHITE);
        addKing(WHITE_PIECE_COLUMN, Color.WHITE);
    }

    private static void addKing(final Column column, final Color color) {
        board.put(Position.of(KING_ROW_INDEX, column), King.from(color));
    }

    private static void addQueen(final Column column, final Color color) {
        board.put(Position.of(QUEEN_ROW_INDEX, column), Queen.from(color));
    }

    private static void addBishop(final Column column, final Color color) {
        board.put(Position.of(BISHOP_LEFT_ROW_INDEX, column), Bishop.from(color));
        board.put(Position.of(BISHOP_RIGHT_ROW_INDEX, column), Bishop.from(color));
    }

    private static void addKnight(final Column column, final Color color) {
        board.put(Position.of(KNIGHT_LEFT_ROW_INDEX, column), Knight.from(color));
        board.put(Position.of(KNIGHT_RIGHT_ROW_INDEX, column), Knight.from(color));
    }

    private static void addRook(final Column column, final Color color) {
        board.put(Position.of(ROOK_LEFT_ROW_INDEX, column), Rook.from(color));
        board.put(Position.of(ROOK_RIGHT_ROW_INDEX, column), Rook.from(color));
    }

    private static void addPawn(final Column column, final Color color) {
        for (Row row : Row.values()) {
            board.put(Position.of(row, column), Pawn.from(color));
        }
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}
