package chess.model.board;

import chess.model.Side;
import chess.model.unit.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final String EMPTY_SQUARE
            = "해당 칸은 비어 있습니다.";

    private final Map<Row, Rank> lines;

    private Board() {
        this.lines = new HashMap<>();
    }

    public static Board makeEmptyBoard() {
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

    public Piece getPiece(final Square square) {
        try {
            final Column column = square.getColumn();
            final Row row = square.getRow();
            final Piece piece = lines.get(row).getPiece(column);
            if (piece == null) {
                throw new Exception();
            }
            return piece;
        } catch (Exception e) {
            throw new IllegalArgumentException(EMPTY_SQUARE);
        }
    }

    public void setPiece(final Square square, final Piece piece) {
        final Column column = square.getColumn();
        final Row row = square.getRow();
        final Rank line = lines.getOrDefault(row, new Rank());
        line.setPiece(column, piece);
        lines.put(row, line);
    }

    public void removePiece(final Square square) {
        final Column column = square.getColumn();
        final Row row = square.getRow();
        try {
            final Rank line = lines.get(row);
            line.removePiece(column);
            lines.put(row, line);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(EMPTY_SQUARE);
        }
    }
}
