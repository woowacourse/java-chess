package chess.views;

import chess.domain.chesspieces.Square;
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
        StringBuilder initialGuide = new StringBuilder();
        initialGuide.append("> 체스 게임을 시작합니다.")
                .append(NEW_LINE)
                .append("> 게임 시작 : start")
                .append(NEW_LINE)
                .append("> 게임 종료 : end")
                .append(NEW_LINE)
                .append("> 게임 이동 : move source 위치 target위치 - 예. move b2 b3")
                .append(NEW_LINE)
                .append("> 게임 상황 : status");
        System.out.println(initialGuide.toString());
    }

    public static void printChessBoard(Map<Position, Square> chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = chessBoard.size();
        for (int i = 0; i < size; i += OFFSET) {
            String row = chessBoard.values()
                    .stream()
                    .skip(i)
                    .limit(OFFSET)
                    .map(Square::getDisplay)
                    .collect(Collectors.joining());
            stringBuilder.insert(0, row);
            stringBuilder.insert(0, NEW_LINE);
        }
        stringBuilder.append(NEW_LINE);
        System.out.println(stringBuilder.toString());
    }

    // 각 플레어의 점수
    // 승패 결과
    public static void printStatus(Result result) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Status status : result.getStatuses()) {
            stringBuilder.append(status.getPlayer());
            stringBuilder.append(" : ");
            stringBuilder.append(status.getScore());
            stringBuilder.append(NEW_LINE);
        }
        stringBuilder.append("승자 : ");
        stringBuilder.append(result.getWinner());

        System.out.println(stringBuilder.toString());
    }
}
