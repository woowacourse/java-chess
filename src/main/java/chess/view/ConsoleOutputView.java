package chess.view;

import java.util.List;
import java.util.Map;

public class ConsoleOutputView implements OutputView {
    @Override
    public void printBoard(List<String> positions, Map<String, String> board) {
        for (int i = 1; i <= positions.size(); i++) {
            String piece = board.get(positions.get(i - 1));
            System.out.print(printPiece(piece));
            if (i % 8 == 0) {
                System.out.println();
            }
        }
    }

    private String printPiece(String piece) {
        if (piece == null) {
            return ".";
        }
        return piece;
    }
}
