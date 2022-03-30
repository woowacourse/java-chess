package chess.view;

import chess.domain.game.Status;
import chess.domain.piece.attribute.Team;
import chess.dto.BoardDto;
import java.util.Map;
import java.util.Map.Entry;

public final class OutputView {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String ARROW = "> ";
    private static final String TEAM_STATUS = "%s 점수 : %.1f졈" + SEPARATOR;
    private static final String WINNER_TEAM = "게임 결과 : %s 승" + SEPARATOR;
    private static final String DRAW = "무승부";
    private static final String INIT_MESSAGE =
            ARROW + "체스 게임을 시작합니다." + SEPARATOR
                    + ARROW + "게임 시작 : start" + SEPARATOR
                    + ARROW + "게임 종료 : end" + SEPARATOR
                    + ARROW + "게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static void printInitMessage() {
        System.out.println(INIT_MESSAGE);
    }

    public static void printChessBoard(BoardDto boardDto) {
        System.out.println(boardDto.getBoardString());
    }

    public static void printStatus(Status status) {
        printColorsTotalScore(status.getStatusOfTeams());
        printGameResult(status.getWinner());
    }

    private static void printColorsTotalScore(Map<Team, Double> colorsTotalScore) {
        for (Entry<Team, Double> entry : colorsTotalScore.entrySet()) {
            System.out.printf(TEAM_STATUS, entry.getKey().name(), entry.getValue());
        }
    }

    private static void printGameResult(Team winnerTeam) {
        if (winnerTeam.equals(Team.NONE)) {
            System.out.println(DRAW);
            return;
        }
        System.out.printf(WINNER_TEAM, winnerTeam.name());
    }

    public static void printError(String string) {
        System.out.println(string);
    }
}
