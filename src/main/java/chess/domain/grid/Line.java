package chess.domain.grid;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Line {
    private final List<Piece> pieces;

    private Line(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static Line from(final List<Piece> pieces) {
        return new Line(pieces);
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
        for (Column column : Column.values()) {
            Pawn pawn = new Pawn(color, column, row);
            pawnLine.add(pawn);
        }
        return new Line(pawnLine);
    }

    public static Line empty(final Row row) {
        List<Piece> emptyLine = new ArrayList<>();
        for (Column column : Column.values()) {
            Empty empty = new Empty(column, row);
            emptyLine.add(empty);
        }
        return new Line(emptyLine);
    }

    public Piece piece(final char xPosition) {
        int index = Column.column(xPosition).index();
        return pieces.get(index);
    }

    public void assignPiece(final char xPosition, final Piece piece) {
        int index = Column.column(xPosition).index();
        pieces.set(index, piece);
    }

    public List<Piece> pieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "Line{" +
                "pieces=" + pieces +
                '}';
    }
}
