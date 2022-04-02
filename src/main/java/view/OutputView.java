package view;

import domain.board.ChessBoard;
import domain.dto.GameStatus;
import domain.dto.StatusDto;
import domain.piece.property.Team;
import domain.position.XPosition;
import domain.position.Position;
import domain.position.YPosition;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class OutputView {

    public static void printBoard(final ChessBoard chessBoard) {
        List<YPosition> yPositions = Arrays.asList(YPosition.values());
        yPositions.sort(Comparator.reverseOrder());

        System.out.println("현재 턴 : " + chessBoard.getCurrentTurn());
        for (YPosition y : yPositions) {
            printBoardByXPosition(chessBoard, y);
        }
        System.out.println();
    }

    private static void printBoardByXPosition(final ChessBoard chessBoard, final YPosition yPosition) {
        Arrays.stream(XPosition.values())
                .forEach(xPosition -> System.out.print(chessBoard.symbol(Position.of(xPosition, yPosition))));
        System.out.println();
    }

    public static void printStatus(final StatusDto statusDto) {
        GameStatus gameStatus = statusDto.getGameStatus();
        System.out.printf("%s 점수 : %.1f (%s)" + System.lineSeparator(), gameStatus.getTurn(), gameStatus.getScore(),
                gameStatus.getResult());
    }

    public static void printWinner(final Team team) {
        System.out.println(team + "가 우승하였습니다.");
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }
}
