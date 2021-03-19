package chess.view;

import chess.domain.piece.Color;

import java.util.Map;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator");

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printScore(Map<Color, Double> scoreTable) {
        scoreTable.forEach(OutputView::printEachScore);
    }

    private static void printEachScore(Color color, Double score) {
        System.out.printf("%s : %f점" + NEWLINE, color.name(), score);
    }
}
