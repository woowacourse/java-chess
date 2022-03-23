package view;

import domain.ChessBoard;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    public static void printBoard(final ChessBoard chessBoard) {
        Row[] rows = Row.values();
        Arrays.sort(rows, Collections.reverseOrder());
        for (Row row : rows) {
            for (Column column : Column.values()) {
                System.out.print(chessBoard.symbol(new Position(row,column)));
            }
            System.out.println();
        }
    }
}
