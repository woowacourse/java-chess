package chess.view;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Team;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final int SIZE = 8;

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public void printAnnounce() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(List<String> symbols) {
        int count = 0;

        for (String symbol : symbols) {
            count = getCount(count, symbol);
        }
        printNextLine();
        printNextLine();
    }

    private int getCount(int count, String symbol) {
        if (count % SIZE == 0) {
            printNextLine();
        }

        System.out.print(symbol);

        return ++count;
    }

    private void printNextLine() {
        System.out.println();
    }

    public void printResult(Map<Team, Double> teamScores) {
        Double whiteScore = teamScores.get(WHITE);
        Double blackScore = teamScores.get(BLACK);

        System.out.println("흰팀 점수: " + whiteScore);
        System.out.println("검은팀 점수: " + blackScore);
    }

    public void printWinTeam(String winTeamName) {
        System.out.println("승리: " + winTeamName);
    }
}
