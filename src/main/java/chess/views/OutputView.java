package chess.views;

import chess.domain.chesspieces.Square;
import chess.domain.position.Position;
import chess.domain.position.component.Row;

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
                .append("> 게임 이동 : move source 위치 target위치 - 예. move b2 b3");
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
}
