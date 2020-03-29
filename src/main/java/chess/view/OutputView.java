package chess.view;

import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    public static void printStartInformation() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(String board) {
        System.out.println(board);
    }

    public static void printRunningStatus(Map<String, Double> scores, String maxScoreTeam) {
        System.out.printf("%s 팀이 이기고 있습니다.\n", maxScoreTeam);

        for (Entry<String, Double> entry : scores.entrySet()) {
            System.out.printf("%s : %f점\n", entry.getKey(), entry.getValue());
        }
    }

    public static void printRunningStatus(Map<String, Double> scores) {
        System.out.println("동점 상태입니다.");

        for (Entry<String, Double> entry : scores.entrySet()) {
            System.out.printf("%s : %f점\n", entry.getKey(), entry.getValue());
        }
    }

    public static void printResult(Map<String, Double> scores, String winner) {
        System.out.printf("%s 팀이 이겼습니다.\n", winner);

        for (Entry<String, Double> entry : scores.entrySet()) {
            System.out.printf("%s : %f점\n", entry.getKey(), entry.getValue());
        }
    }

    public static void printResult(Map<String, Double> scores) {
        System.out.println("무승부입니다.");

        for (Entry<String, Double> entry : scores.entrySet()) {
            System.out.printf("%s : %f점\n", entry.getKey(), entry.getValue());
        }
    }
}
