package chess.view;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int SIZE = 8;

    public void printAnnounce() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printInitChessBoard(List<String> symbols) {
        int count = 0;

        for (String symbol : symbols) {
            if (count % SIZE == 0) {
                printNextLine();
            }
            System.out.print(symbol);
            count ++;
        }
        printNextLine();
        printNextLine();
    }

    private void printNextLine() {
        System.out.println();
    }

    public void printResult(Map<Team, Double> teamScores) {
        Double whiteScore = teamScores.get(WHITE);
        Double blackScore = teamScores.get(BLACK);

        if (whiteScore > blackScore) {
            System.out.println("승리: 흰팀");
        }

        if (blackScore > whiteScore) {
            System.out.println("승리: 검은팀");
        }

        if (blackScore == whiteScore) {
            System.out.println("무승부");
        }

        System.out.println("흰팀 점수: " + whiteScore);
        System.out.println("검은팀 점수: " + blackScore);
    }
}
