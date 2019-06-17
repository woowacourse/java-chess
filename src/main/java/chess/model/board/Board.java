package chess.model.board;

import chess.model.Column;
import chess.model.Row;
import chess.model.unit.Piece;
import chess.model.unit.Side;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final String EMPTY_SQUARE
            = "해당 칸은 비어 있습니다.";

    private final Map<Row, Rank> lines;

    private Board() {
        this.lines = new HashMap<>();
    }

    private static Board makeEmptyBoard() {
        return new Board();
    }

    public static Board makeInitialBoard() {
        final Board board = makeEmptyBoard();
        board.setLine(Row._1, Rank.getFirstLine(Side.WHITE));
        board.setLine(Row._2, Rank.getSecondLine(Side.WHITE));
        board.setLine(Row._7, Rank.getSecondLine(Side.BLACK));
        board.setLine(Row._8, Rank.getFirstLine(Side.BLACK));
        return board;
    }

    private void setLine(final Row row, final Rank rank) {
        lines.put(row, rank);
    }

    public Piece getPiece(Column column, Row row) {
        final Piece piece = lines.get(row).getPiece(column);
        if (piece == null) {
            throw new IllegalArgumentException(EMPTY_SQUARE);
        }
        return piece;
    }

    public void setPiece(Column column, Row row, Piece piece) {
        final Rank line = lines.get(row);
        line.setPiece(column, piece);
        lines.put(row, line);
    }
}
