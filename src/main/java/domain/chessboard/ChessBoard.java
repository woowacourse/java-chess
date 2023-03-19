package domain.chessboard;

import domain.coordinate.Position;

import java.util.ArrayList;
import java.util.List;

public final class ChessBoard {

    private static final int NUMBER_OF_NONE_LINES = 4;

    private final List<Row> rows;

    public ChessBoard(final List<Row> rows) {
        this.rows = rows;
    }

    public static ChessBoard generate() {
        final List<Row> chessBoard = new ArrayList<>();
        chessBoard.add(Row.from(RowFactory.OTHERS_BLACK));
        chessBoard.add(Row.from(RowFactory.PAWN_BLACK));
        for (int i = 0; i < NUMBER_OF_NONE_LINES; i++) {
            chessBoard.add(Row.from(RowFactory.EMPTY));
        }
        chessBoard.add(Row.from(RowFactory.PAWN_WHITE));
        chessBoard.add(Row.from(RowFactory.OTHERS_WHITE));

        return new ChessBoard(new ArrayList<>(chessBoard));
    }

    public List<Row> getChessBoard() {
        return new ArrayList<>(rows);
    }

    public Square findSquare(Position position) {
        return rows.get(position.getY())
                .getSquare(position.getX());
    }

}
