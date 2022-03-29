package chess.view;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.game.Status;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Team;
import java.util.List;
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

    public static void printChessBoard(Map<Position, Piece> squares) {
        List<Rank> ranks = Rank.reverseRanks();
        for (Rank rank : ranks) {
            printChessBoardOfRank(squares, rank);
        }
        System.out.println();
    }

    private static void printChessBoardOfRank(Map<Position, Piece> squares, Rank rank) {
        for (Column column : Column.values()) {
            System.out.print(squares.get(new Position(column, rank)).getName());
        }
        System.out.println();
    }

    public static void printStatus(Status status) {
        printColorsTotalScore(status.getTotalStatus());
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
