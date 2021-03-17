package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private static final int LINE_COUNT = 8;
    private static final int DECIMAL = 10;
    private static final String COLUMN_REFERENCE = "abcdefgh";
    private static final char FIRST_COLUMN = 'a';

    private final List<Piece> pieces;

    private Line(final List<Piece> pieces){
        this.pieces = new ArrayList<>(pieces);
    }

    public static Line createGeneralLine(final int rowNumber, final boolean isBlack){
        List<Piece> generalLine = new ArrayList<>();
//        Rook rookLeft = new Rook(isBlack, (char)('a'), Character.forDigit(rowNumber, 10));
//        Knight knightLeft = new Knight((isBlack, (char)('b'), Character.forDigit(rowNumber, 10));
//        Bishop bishopLeft = new Bishop((isBlack, (char)('c'), Character.forDigit(rowNumber, 10));
//        Queen queen = new Queen((isBlack, (char)('d'), Character.forDigit(rowNumber, 10));
//        King king = new King((isBlack, (char)('e'), Character.forDigit(rowNumber, 10));
//        Bishop bishopRight = new Bishop((isBlack, (char)('f'), Character.forDigit(rowNumber, 10));
//        Knight knightRight = new Knight((isBlack, (char)('g'), Character.forDigit(rowNumber, 10));
//        Rook rookRight = new Rook(isBlack, (char)('h'), Character.forDigit(rowNumber, 10));
        return new Line(generalLine);
    }

    public static Line createPawnLine(final int rowNumber, final boolean isBlack){
        List<Piece> pawnLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Pawn pawn = new Pawn(isBlack, (char)(FIRST_COLUMN + i), Character.forDigit(rowNumber, DECIMAL));
            pawnLine.add(pawn);
        }
        return new Line(pawnLine);
    }

    public static Line createEmptyLine(final int rowNumber){
        List<Piece> emptyLine = new ArrayList<>();
        for (int i = 0; i < LINE_COUNT; i++) {
            Empty empty = new Empty((char)(FIRST_COLUMN + i), Character.forDigit(rowNumber, DECIMAL));
            emptyLine.add(empty);
        }
        return new Line(emptyLine);
    }

    public Piece findPiece(final char xPosition) {
        int index = COLUMN_REFERENCE.indexOf(Character.toString(xPosition));
        return pieces.get(index);
    }
}
