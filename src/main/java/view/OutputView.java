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

        System.out.println("현재 턴 : " + chessBoard.getCurrentTurn());
        for (YPosition y : yPositions) {
            printBoardByXPosition(chessBoard, y);
        }
        System.out.println();
    }

    private static void printBoardByXPosition(ChessBoard chessBoard, YPosition yPosition) {
        Arrays.stream(XPosition.values())
                .forEach(xPosition -> System.out.print(chessBoard.symbol(Position.of(xPosition, yPosition))));
        System.out.println();
    }
}
