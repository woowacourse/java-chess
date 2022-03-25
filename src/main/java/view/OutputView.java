package view;

import domain.ChessBoard;
import domain.dto.StatusDto;
import domain.piece.property.TeamColor;
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

    public static void printStatus(StatusDto statusDto) {
        System.out.println(
                String.format("%s 점수 : %.1f (%s)", statusDto.getTurn(), statusDto.getScore(), statusDto.getResult()));
    }

    public static void printWinner(TeamColor teamColor) {
        System.out.println(teamColor + "가 우승하였습니다.");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
