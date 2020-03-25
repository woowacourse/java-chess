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
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
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
