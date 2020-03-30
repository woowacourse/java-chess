package chess.view;

import java.util.Map;

public class OutputView {
    private static final int LINE_START = 1;
    private static final int LINE_END = 8;

    public static void printBoard(Map<String, String> board) {
        for (int y = LINE_END; LINE_START <= y; y--) {
            for (int x = LINE_START; x <= LINE_END; x++) {
                String position = String.valueOf(x) + String.valueOf(y);
                String piece = board.get(position);
                System.out.print(piece);
            }
            System.out.println();
        }
    }

    public static void printEnd() {
        System.out.println("end");
    }
}
