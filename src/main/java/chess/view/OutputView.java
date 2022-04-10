package chess.view;

import chess.dto.GameStatus;
import chess.domain.board.ChessBoard;
import chess.dto.StatusDTO;
import chess.domain.piece.property.Team;
import chess.domain.position.XPosition;
import chess.domain.position.Position;
import chess.domain.position.YPosition;
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

    public static void printStatus(final StatusDTO statusDto) {
        GameStatus gameStatus = statusDto.getGameStatus();
        System.out.printf("%s 점수 : %.1f (%s)" + System.lineSeparator(), gameStatus.getTurn(), gameStatus.getScore(),
                gameStatus.getResult());
    }

    public static void printWinner(final Team team) {
        System.out.println(team + "가 우승하였습니다.");
    }

    public static void printErrorMessage(final String message) {
        System.err.println(message);
    }
}
