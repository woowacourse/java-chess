package chess.domain.grid;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Line {
    private static final String COLUMN_REFERENCE = "abcdefgh";
    private static final int LINE_COUNT = 8;
    private static final char FIRST_COLUMN = 'a';
    private static final char SECOND_COLUMN = 'b';
    private static final char THIRD_COLUMN = 'c';
    private static final char FOURTH_COLUMN = 'd';
    private static final char FIFTH_COLUMN = 'e';
    private static final char SIXTH_COLUMN = 'f';
    private static final char SEVENTH_COLUMN = 'g';
    private static final char EIGHT_COLUMN = 'h';

    private final List<Piece> pieces;

    private Line(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static Line general(final int rowNumber, final Color color) {
        List<Piece> generalLine = Arrays.asList(
                new Rook(color, FIRST_COLUMN, rowNumber),
                new Knight(color, SECOND_COLUMN, rowNumber),
                new Bishop(color, THIRD_COLUMN, rowNumber),
                new Queen(color, FOURTH_COLUMN, rowNumber),
                new King(color, FIFTH_COLUMN, rowNumber),
                new Bishop(color, SIXTH_COLUMN, rowNumber),
                new Knight(color, SEVENTH_COLUMN, rowNumber),
                new Rook(color, EIGHT_COLUMN, rowNumber)
        );
        return new Line(generalLine);
    }

    public static Line pawn(final int rowNumber, final Color color) {
        List<Piece> pawnLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Pawn pawn = new Pawn(color, (char) (FIRST_COLUMN + i), rowNumber);
            pawnLine.add(pawn);
        }
        return new Line(pawnLine);
    }

    public static Line empty(final int rowNumber) {
        List<Piece> emptyLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Empty empty = new Empty((char) (FIRST_COLUMN + i), rowNumber);
            emptyLine.add(empty);
        }
        return new Line(emptyLine);
    }

    public final Piece piece(final char xPosition) {
        int index = COLUMN_REFERENCE.indexOf(Character.toString(xPosition));
        return pieces.get(index);
    }

    public final void assignPiece(final char xPosition, final Piece piece) {
        int index = COLUMN_REFERENCE.indexOf(Character.toString(xPosition));
        pieces.set(index, piece);
    }

    public final List<Piece> pieces() {
        return pieces;
    }
}
