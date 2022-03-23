import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    public static void printBoard(ChessBoard chessBoard) {
        Row[] rows = Row.values();
        Arrays.sort(rows, Collections.reverseOrder());
        for (Row row : rows) {
            for (Column column : Column.values()) {
                System.out.print(chessBoard.getSymbol(new Position(row, column)));
            }
            System.out.println();
        }
    }
}
