package chess.domain.grid;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Line {
    private static final int LINE_COUNT = 8;

    private final List<Piece> pieces;

    private Line(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static Line general(final Row row, final Color color) {
        List<Piece> generalLine = Arrays.asList(
                new Rook(color, Column.FIRST, row),
                new Knight(color, Column.SECOND, row),
                new Bishop(color, Column.THIRD, row),
                new Queen(color, Column.FOURTH, row),
                new King(color, Column.FIFTH, row),
                new Bishop(color, Column.SIXTH, row),
                new Knight(color, Column.SEVENTH, row),
                new Rook(color, Column.EIGHTH, row)
        );
        return new Line(generalLine);
    }

    public static Line pawn(final Row row, final Color color) {
        List<Piece> pawnLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Pawn pawn = new Pawn(color, Column.column(i), row);
            pawnLine.add(pawn);
        }
        return new Line(pawnLine);
    }

    public static Line empty(final Row row) {
        List<Piece> emptyLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Empty empty = new Empty(Column.column(i), row);
            emptyLine.add(empty);
        }
        return new Line(emptyLine);
    }

    public final Piece piece(final Column column) {
        return pieces.get(column.getIndex());
    }

    public final void assignPiece(final Column column, final Piece piece) {
        pieces.set(column.getIndex(), piece);
    }

    public final List<Piece> pieces() {
        return pieces;
    }
}
