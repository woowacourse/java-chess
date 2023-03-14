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

    private final Color color;

    public PieceGenerator(Color color) {
        this.color = color;
    }

    public List<Piece> findFirstRowPieces() {
        List<Piece> firstRow = new ArrayList<>(SIZE_OF_COLUMN);
        initRook(firstRow);
        initKnight(firstRow);
        initBishop(firstRow);
        initQueen(firstRow);
        initKing(firstRow);

        return firstRow;
    }

    private void initRook(List<Piece> firstRow) {
        initialColumnsOfRook.forEach(column ->
                firstRow.add(column.getIndex(), new Rook(color)));
    }

    private void initKnight(List<Piece> firstRow) {
        initialColumnsOfKnight.forEach(column ->
                firstRow.add(column.getIndex(), new Knight(color)));
    }

    private void initBishop(List<Piece> firstRow) {
        initialColumnsOfBishop.forEach(column ->
                firstRow.add(column.getIndex(), new Bishop(color)));
    }

    private void initQueen(List<Piece> firstRow) {
        initialColumnsOfQueen.forEach(column ->
                firstRow.add(column.getIndex(), new Queen(color)));
    }

    private void initKing(List<Piece> firstRow) {
        initialColumnsOfKing.forEach(column ->
                firstRow.add(column.getIndex(), new King(color)));
    }

    public List<Piece> findSecondRowPieces() {
        List<Piece> secondRow = new ArrayList<>(SIZE_OF_COLUMN);
        initPawn(secondRow);

        return secondRow;
    }

    private void initPawn(List<Piece> secondRow) {
        for (Column column : initialColumnsOfPawn) {
            secondRow.add(column.getIndex(), new Pawn(color));
        }
    }

}
