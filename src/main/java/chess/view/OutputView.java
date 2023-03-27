package chess.view;

import chess.domain.pieces.component.Team;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(List<List<String>> pieceNames) {
        for (List<String> names : pieceNames) {
            names.forEach(System.out::print);
            System.out.println();
        }
    }

    public static void printScore(Map<Team, Double> result) {
        System.out.println("화이트팀: " + result.get(Team.WHITE));
        System.out.println("블랙팀: " + result.get(Team.BLACK));
    }
}
