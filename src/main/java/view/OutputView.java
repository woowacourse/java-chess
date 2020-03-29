package view;

import domain.board.Board;
import domain.board.RowOfBoard;
import domain.pieces.Pieces;
import domain.team.Team;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public static void printStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (RowOfBoard row : board.getBoard()) {
            stringBuilder.append(String.join("", row.getRowOfBoard()))
                    .append(NEW_LINE);
        }

        System.out.println(stringBuilder.toString());
    }

    public static void printStatus(Pieces pieces) {
        if (pieces.isKingKilled(Team.BLACK)) {
            System.out.println("흰 팀의 승리입니다.");
            return;
        }
        if (pieces.isKingKilled(Team.WHITE)) {
            System.out.println("검은 팀의 승리입니다.");
            return;
        }

        // TODO
        double blackScore = pieces.sumTeamScore(Team.BLACK);
        double whiteScore = pieces.sumTeamScore(Team.WHITE);

        System.out.printf("검은색 팀의 점수는 %f 입니다." + NEW_LINE, blackScore);
        System.out.printf("흰색 팀의 점수는 %f 입니다." + NEW_LINE, whiteScore);

        if (blackScore > whiteScore) {
            System.out.println("검은색 팀의 승리입니다.");
            return;
        }
        if (blackScore < whiteScore) {
            System.out.println("흰색 팀의 승리입니다.");
            return;
        }
        System.out.println("비겼습니다.");
    }
}
