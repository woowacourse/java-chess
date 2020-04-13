package chess.view;

import chess.config.BoardConfig;

import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

public class OutputView {

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

    public static void printResult(String winner, String loser, String winnerScore, String loserScore) {
        System.out.println(String.format("%s팀이 %s팀을 %s : %s 로 승리하였습니다.", winner, loser, winnerScore, loserScore));

    }

    private static void printRow(int y, Map<String, String> board) {
        IntStream.rangeClosed(BoardConfig.LINE_START, BoardConfig.LINE_END)
                .forEach(x -> printPosition(x, y, board));
        System.out.println();
    }

    private static void printPosition(int x, int y, Map<String, String> board) {
        String position = String.valueOf(x) + String.valueOf(y);
        String piece = board.get(position);
        System.out.print(piece);
    }
}
