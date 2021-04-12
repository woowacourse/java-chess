package chess.domain.game;

import chess.domain.location.Column;
import chess.domain.location.Position;
import chess.domain.location.Row;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static final Map<Position, Piece> pieceByPosition = new HashMap<>();
    private static final Column[] ROOK_COLUMNS = {Column.A, Column.H};
    private static final Column[] KNIGHT_COLUMNS = {Column.B, Column.G};
    private static final Column[] BISHOP_COLUMNS = {Column.C, Column.F};
    private static final Column[] KING_COLUMNS = {Column.D};
    private static final Column[] QUEEN_COLUMNS = {Column.E};
    private static final Row[] EMPTY_ROWS = {Row.THREE, Row.FOUR, Row.FIVE, Row.SIX};

    static {
        setUpPiece();
        setUpEmpty();
    }

    private static void setUpPiece() {
        setUpPieceByColor(Color.BLACK);
        setUpPieceByColor(Color.WHITE);
    }

    private static void setUpPieceByColor(Color color) {
        setUpColumnsInRow(ROOK_COLUMNS, color.initGeneralRow(), new Rook(color));
        setUpColumnsInRow(KNIGHT_COLUMNS, color.initGeneralRow(), new Knight(color));
        setUpColumnsInRow(BISHOP_COLUMNS, color.initGeneralRow(), new Bishop(color));
        setUpColumnsInRow(QUEEN_COLUMNS, color.initGeneralRow(), new Queen(color));
        setUpColumnsInRow(KING_COLUMNS, color.initGeneralRow(), new King(color));
        setUpColumnsInRow(Column.values(), color.initPawnRow(), new Pawn(color));
    }

    private static void setUpColumnsInRow(Column[] columns, Row row, Piece piece) {
        for (Column column : columns) {
            pieceByPosition.put(Position.of(column, row), piece);
        }
    }

    private static void setUpEmpty() {
        for (Row row : EMPTY_ROWS) {
            setUpColumnsInRow(Column.values(), row, new Empty());
        }
    }

    public static Board create() {
        return new Board(pieceByPosition);
    }
}
