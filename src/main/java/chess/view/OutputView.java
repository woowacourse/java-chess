package chess.view;

import chess.File;
import chess.Position;
import chess.Rank;

import java.util.Collection;
import java.util.Map;

public class OutputView {

    public static void printBoard(Map<Position, String> board) {
        board.forEach((position, piece) -> {
            System.out.print(piece);
            if (position.getFile() == File.H) {
                System.out.println();
            }
        });
    }
}
