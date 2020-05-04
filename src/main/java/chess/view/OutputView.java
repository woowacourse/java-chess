package chess.view;

import chess.config.BoardConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class OutputView {
    private static final String BLANK = ".";

    public static void printBoard(Map<String, String> board) {
        IntStream.rangeClosed(BoardConfig.LINE_START, BoardConfig.LINE_END)
                .boxed()
                .sorted(Collections.reverseOrder())
                .forEach(y -> printRow(y, board));
        System.out.println();
    }

    public static void printEnd() {
        System.out.println("end");
    }

    public static void printResult(String winner, String whiteScore, String blackScore) {
        System.out.println(String.format("%s팀이 이겼습니다. White 팀 점수: %s, Black 팀 점수: %s", winner, whiteScore, blackScore));

    }

    private static void printRow(int y, Map<String, String> board) {
        IntStream.rangeClosed(BoardConfig.LINE_START, BoardConfig.LINE_END)
                .forEach(x -> printPosition(x, y, board));
        System.out.println();
    }

    private static void printPosition(int x, int y, Map<String, String> board) {
        String position = String.valueOf(x) + String.valueOf(y);
        String piece = board.get(position);
        if (isBlank(piece)) {
            System.out.print(BLANK);
        } else {
            System.out.print(piece);
        }

    }

    private static boolean isBlank(String piece) {
        return Objects.isNull(piece);
    }
}
