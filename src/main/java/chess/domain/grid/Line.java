package chess.domain.grid;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Line {
    private static final int LINE_COUNT = 8;
    private static final int DECIMAL = 10;
    private static final String COLUMN_REFERENCE = "abcdefgh";
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

    public static Line general(final int rowNumber, final boolean isBlack) {
        Rook rookLeft = new Rook(isBlack, FIRST_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        Knight knightLeft = new Knight(isBlack, SECOND_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        Bishop bishopLeft = new Bishop(isBlack, THIRD_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        Queen queen = new Queen(isBlack, FOURTH_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        King king = new King(isBlack, FIFTH_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        Bishop bishopRight = new Bishop(isBlack, SIXTH_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        Knight knightRight = new Knight(isBlack, SEVENTH_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        Rook rookRight = new Rook(isBlack, EIGHT_COLUMN, Character.forDigit(rowNumber, DECIMAL));
        List<Piece> generalLine = Arrays.asList(
                rookLeft,
                knightLeft,
                bishopLeft,
                queen,
                king,
                bishopRight,
                knightRight,
                rookRight
        );
        return new Line(generalLine);
    }

    public static Line pawn(final int rowNumber, final boolean isBlack) {
        List<Piece> pawnLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Pawn pawn = new Pawn(isBlack, (char) (FIRST_COLUMN + i), Character.forDigit(rowNumber, DECIMAL));
            pawnLine.add(pawn);
        }
        return new Line(pawnLine);
    }

    public static Line empty(final int rowNumber) {
        List<Piece> emptyLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Empty empty = new Empty((char) (FIRST_COLUMN + i), Character.forDigit(rowNumber, DECIMAL));
            emptyLine.add(empty);
        }
        return new Line(emptyLine);
    }

    public Piece findPiece(final char xPosition) {
        int index = COLUMN_REFERENCE.indexOf(Character.toString(xPosition));
        return pieces.get(index);
    }

    public void assignPiece(final char xPosition, final Piece piece) {
        int index = COLUMN_REFERENCE.indexOf(Character.toString(xPosition));
        pieces.set(index, piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
