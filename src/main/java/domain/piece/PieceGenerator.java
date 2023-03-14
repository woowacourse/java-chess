package domain.piece;

import domain.Color;
import domain.Column;

import java.util.ArrayList;
import java.util.List;

public class PieceGenerator {

    private final static int SIZE_OF_COLUMN = Column.findSize();
    private final static List<Column> initialColumnsOfPawn = Column.getOrderedColumns();
    private final static List<Column> initialColumnsOfRook = List.of(Column.A, Column.H);
    private final static List<Column> initialColumnsOfKnight =List.of(Column.B, Column.G);
    private final static List<Column> initialColumnsOfBishop = List.of(Column.C, Column.F);
    private final static List<Column> initialColumnsOfQueen = List.of(Column.D);
    private final static List<Column> initialColumnsOfKing = List.of(Column.E);

    public static List<Piece> findFirstRowPieces(Color color) {
        List<Piece> firstRow = new ArrayList<>(SIZE_OF_COLUMN);
        initRook(color, firstRow);
        initKnight(color, firstRow);
        initBishop(color, firstRow);
        initQueen(color, firstRow);
        initKing(color, firstRow);

        return firstRow;
    }

    private static void initRook(Color color, List<Piece> firstRow) {
        initialColumnsOfRook.forEach(column ->
                firstRow.add(column.getIndex(), new Rook(color)));
    }

    private static void initKnight(Color color, List<Piece> firstRow) {
        initialColumnsOfKnight.forEach(column ->
                firstRow.add(column.getIndex(), new Knight(color)));
    }

    private static void initBishop(Color color, List<Piece> firstRow) {
        initialColumnsOfBishop.forEach(column ->
                firstRow.add(column.getIndex(), new Bishop(color)));
    }

    private static void initQueen(Color color, List<Piece> firstRow) {
        initialColumnsOfQueen.forEach(column ->
                firstRow.add(column.getIndex(), new Queen(color)));
    }

    private static void initKing(Color color, List<Piece> firstRow) {
        initialColumnsOfKing.forEach(column ->
                firstRow.add(column.getIndex(), new King(color)));
    }

    public static List<Piece> findSecondRowPieces(Color color) {
        List<Piece> secondRow = new ArrayList<>(SIZE_OF_COLUMN);
        initPawn(color, secondRow);

        return secondRow;
    }

    private static void initPawn(Color color, List<Piece> secondRow) {
        for (Column column : initialColumnsOfPawn) {
            secondRow.add(column.getIndex(), new Pawn(color));
        }
    }
}
