package chess.views;

import chess.domain.chesspieces.Piece;
import chess.domain.position.Position;
import chess.domain.position.component.Row;
import chess.domain.status.Result;
import chess.domain.status.Status;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private final static String NEW_LINE = System.lineSeparator();
    private final static int OFFSET = Row.values().length;

    public static void printInitialGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 상황 : status");
    }

    public static void printChessBoard(Map<Position, Piece> chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = chessBoard.size();
        for (int i = 0; i < size; i += OFFSET) {
            String row = chessBoard.values()
                    .stream()
                    .skip(i)
                    .limit(OFFSET)
                    .map(Piece::getDisplay)
                    .collect(Collectors.joining());
            stringBuilder.insert(0, row);
            stringBuilder.insert(0, NEW_LINE);
        }
        stringBuilder.append(NEW_LINE);

        System.out.println(stringBuilder.toString());
    }

    public static void printStatus(Result result) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Status status : result.getStatuses()) {
            stringBuilder.append(status.getPlayer());
            stringBuilder.append(" : ");
            stringBuilder.append(status.getScore());
            stringBuilder.append(NEW_LINE);
        }

        if (result.isDraw()) {
            stringBuilder.append("무승부");
        }

        if (!result.isDraw()) {
            stringBuilder.append("승자 : ");
            stringBuilder.append(result.getWinners());
        }

        System.out.println(stringBuilder.toString());
    }


    public static void printGameOver() {
        System.out.println("King이 잡혀서 게임을 종료합니다.");
    }
}
