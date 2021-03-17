package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Piece> pieces;

    private Line(List<Piece> pieces){
        this.pieces = new ArrayList<>(pieces);
    }

    public static Line createGeneralLine(int rowNumber, boolean isBlack){
        List<Piece> generalLine = new ArrayList<>();
//        Rook rookLeft = new Rook(isBlack, (char)('a'), Character.forDigit(rowNumber, 10));
//        Knight knightLeft = new Knight((isBlack, (char)('b'), Character.forDigit(rowNumber, 10));
//        Bishop bishopLeft = new Bishop((isBlack, (char)('c'), Character.forDigit(rowNumber, 10));
//        Queen queen = new Queen((isBlack, (char)('d'), Character.forDigit(rowNumber, 10));
//        King king = new King((isBlack, (char)('e'), Character.forDigit(rowNumber, 10));
//        Bishop bishopRight = new Bishop((isBl
//        ack, (char)('f'), Character.forDigit(rowNumber, 10));
//        Knight knightRight = new Knight((isBlack, (char)('g'), Character.forDigit(rowNumber, 10));
//        Rook rookRight = new Rook(isBlack, (char)('h'), Character.forDigit(rowNumber, 10));
        return new Line(generalLine);
    }

    public static Line createPawnLine(int rowNumber, boolean isBlack){
        List<Piece> pawnLine = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Pawn pawn = new Pawn(isBlack, (char)('a' + i), Character.forDigit(rowNumber, 10));
            pawnLine.add(pawn);
        }
        return new Line(pawnLine);
    }

    public static Line createEmptyLine(int rowNumber){
        List<Piece> emptyLine = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Empty empty = new Empty((char)('a' + i), Character.forDigit(rowNumber, 10));
            emptyLine.add(empty);
        }
        return new Line(emptyLine);
    }

}
