package view;

import domain.ChessBoard;
import domain.position.XPosition;
import domain.position.Position;
import domain.position.YPosition;
import java.util.Arrays;
import java.util.Collections;

public class OutputView {

    public static void printBoard(final ChessBoard chessBoard) {
        YPosition[] yPositions = YPosition.values();
        Arrays.sort(yPositions, Collections.reverseOrder());

        for (YPosition y : yPositions) {
            for (XPosition x : XPosition.values()) {
                System.out.print(chessBoard.symbol(Position.of(x, y)));
            }
            System.out.println();
        }
        System.out.println();
    }
}
