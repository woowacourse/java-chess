package chess.view;

import chess.Position;
import chess.piece.Piece;
import java.util.Map;

public class OutputView {

    public static void displayBoard(Map<Piece, Position> board) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                int i2 = i;
                int j2 = j;
                if (!board.entrySet().stream()
                        .filter(e -> e.getValue().row().getNumber() == i2 && e.getValue().column().getNumber() == j2)
                        .toList().isEmpty()) {
                    System.out.print("체");
                    continue;
                }
                System.out.print("오");
            }
            System.out.println();
        }
    }

}
