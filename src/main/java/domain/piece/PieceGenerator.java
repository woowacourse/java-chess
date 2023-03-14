package domain.piece;

import domain.Color;
import domain.Column;

import java.util.ArrayList;
import java.util.List;

public class PieceGenerator {

    private final static int SIZE_OF_COLUMN = 8;
    private final static List<Column> initialColumnsOfPawn = Column.getOrderedColumns();
    private final static List<Column> initialColumnsOfRook = List.of(Column.A, Column.H);

    public static List<Piece> findSecondRowPieces(Color color) {
        List<Piece> secondRow = new ArrayList<>(SIZE_OF_COLUMN);
        initPawns(color, secondRow);

        return secondRow;
    }

    private static void initPawns(Color color, List<Piece> secondRow) {
        for (Column column : initialColumnsOfPawn) {
            secondRow.add(column.getIndex(), new Pawn(color));
        }
    }

    private static void initRooks(Color color, List<Piece> secondRow) {
        initialColumnsOfRook.forEach(column -> secondRow.add(column.getIndex(), new Rook(color)));
    }
}
