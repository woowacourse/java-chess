package chess.view;

import chess.domain.Board;
import chess.domain.Team;

public class OutputView {

    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 시작 : start\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final Board board) {
        System.out.println(BoardView.showChessBoard(board));
        System.out.println();
    }

    public void printTeamScore(final Team team, final double score) {
        System.out.println(team + "의 점수는 " + score + "입니다.");
    }

    public void printWinnerTeam(final Team team) {
        System.out.println("현재 이긴 팀은 " + team + "입니다.");
    }

    public void printErrorMessage(final String errorMessage) {
        System.out.println(errorMessage);
    }
}
