package chess.view;

import chess.dto.ChessBoardDto;
import chess.dto.GameScoreDto;
import java.util.List;

public class OutputView {

    private static final String GAME_START = "> 체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_MOVE_DESCRIPTION = String.format(
        "%s source위치 target위치 - 예. %s b2 b3", Command.MOVE.getAnswer(), Command.MOVE.getAnswer());
    private static final String GAME_COMMAND_REQUEST = String.format(
        "> 게임 시작 : %s\n> 게임 종료 : %s\n> 게임 이동 : %s\n> 점수 및 결과 조회 : %s\n",
        Command.START.getAnswer(), Command.END.getAnswer(), GAME_COMMAND_MOVE_DESCRIPTION,
        Command.STATUS.getAnswer());
    private static final String WHITE_TEAM = "흰색 말";
    private static final String BLACK_TEAM = "검정색 말";
    private static final String SCORE_FORMAT = "%s 점수: %.1f\n%s 점수: %.1f\n";
    private static final String WINNER_FORMAT = "우승팀은 %s 입니다.";
    private static final String GAME_END_ALERT = String.format(
        "게임이 종료되었습니다. 결과를 확인하려면 %s, 종료하려면 %s 를 입력해주세요.",
        Command.STATUS.getAnswer(), Command.END.getAnswer());

    public void printStartMessage() {
        System.out.println(GAME_START);
        System.out.println(GAME_COMMAND_REQUEST);
    }

    public void printBoard(final ChessBoardDto chessBoard) {
        System.out.println();
        List<List<String>> board = chessBoard.getBoard();
        for (List<String> rank : board) {
            printRank(rank);
        }
        System.out.println();
    }

    private void printRank(final List<String> rank) {
        for (String value : rank) {
            System.out.print(value);
        }
        System.out.println();
    }

    public void alertGameEnd() {
        System.out.println(GAME_END_ALERT);
    }

    public void printCurrentScore(final GameScoreDto score) {
        String scoreResult = String.format(SCORE_FORMAT, WHITE_TEAM, score.getWhiteScore(),
            BLACK_TEAM, score.getBlackScore());
        System.out.println(scoreResult);
    }

    public void printWinner(final String winner) {
        String winnerResult = String.format(WINNER_FORMAT, getWinningTeam(winner));
        System.out.println(winnerResult);
    }

    private String getWinningTeam(String winner) {
        if (winner.equalsIgnoreCase("white")) {
            return WHITE_TEAM;
        }
        return BLACK_TEAM;
    }

    public void printErrorMessage(final IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

}
