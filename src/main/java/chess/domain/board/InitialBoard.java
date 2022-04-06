package chess.domain.board;


import static chess.domain.piece.Symbol.BISHOP;
import static chess.domain.piece.Symbol.EMPTY;
import static chess.domain.piece.Symbol.KING;
import static chess.domain.piece.Symbol.KNIGHT;
import static chess.domain.piece.Symbol.PAWN;
import static chess.domain.piece.Symbol.QUEEN;
import static chess.domain.piece.Symbol.ROOK;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.NONE;
import static chess.domain.piece.Team.WHITE;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum InitialBoard {
    BLACK_BISHOP(Arrays.asList(Column.C, Column.F), Arrays.asList(Row.EIGHT), Piece.of(BISHOP.name(), BLACK.name())),
    WHITE_BISHOP(Arrays.asList(Column.C, Column.F), Arrays.asList(Row.ONE), Piece.of(BISHOP.name(), WHITE.name())),
    BLACK_KING(Arrays.asList(Column.E), Arrays.asList(Row.EIGHT), Piece.of(KING.name(), BLACK.name())),
    WHITE_KING(Arrays.asList(Column.E), Arrays.asList(Row.ONE), Piece.of(KING.name(), WHITE.name())),
    BLACK_KNIGHT(Arrays.asList(Column.B, Column.G), Arrays.asList(Row.EIGHT), Piece.of(KNIGHT.name(), BLACK.name())),
    WHITE_KNIGHT(Arrays.asList(Column.B, Column.G), Arrays.asList(Row.ONE), Piece.of(KNIGHT.name(), WHITE.name())),
    BLACK_PAWN(Arrays.asList(Column.values()), Arrays.asList(Row.SEVEN), Piece.of(PAWN.name(), BLACK.name())),
    WHITE_PAWN(Arrays.asList(Column.values()), Arrays.asList(Row.TWO), Piece.of(PAWN.name(), WHITE.name())),
    BLACK_QUEEN(Arrays.asList(Column.D), Arrays.asList(Row.EIGHT), Piece.of(QUEEN.name(), BLACK.name())),
    WHITE_QUEEN(Arrays.asList(Column.D), Arrays.asList(Row.ONE), Piece.of(QUEEN.name(), WHITE.name())),
    BLACK_ROOK(Arrays.asList(Column.A, Column.H), Arrays.asList(Row.EIGHT), Piece.of(ROOK.name(), BLACK.name())),
    WHITE_ROOK(Arrays.asList(Column.A, Column.H), Arrays.asList(Row.ONE), Piece.of(ROOK.name(), WHITE.name())),
    NONE_EMPTY(Arrays.asList(Column.values()),
            Arrays.asList(Row.THREE, Row.FOUR, Row.FIVE, Row.SIX), Piece.of(EMPTY.name(), NONE.name()));

    private final List<Column> columns;
    private final List<Row> rows;
    private final Piece piece;

    InitialBoard(List<Column> columns, List<Row> rows, Piece piece) {
        this.columns = columns;
        this.rows = rows;
        this.piece = piece;
    }

    public static Map<Coordinate, Piece> initialize() {
        Map<Coordinate, Piece> map = new HashMap<>();
        for (Row row : Row.values()) {
            initializeInSameRow(map, row);
        }
        return map;
    }

    private static void initializeInSameRow(Map<Coordinate, Piece> map, Row row) {
        for (Column column : Column.values()) {
            map.put(Coordinate.of(column, row), findPiece(column, row));
        }
    }

    private static Piece findPiece(Column column, Row row) {
        return Arrays.stream(InitialBoard.values())
                .filter(board -> board.columns.contains(column) && board.rows.contains(row))
                .findFirst()
                .map(board -> board.piece)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
    }
}
