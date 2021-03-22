package chess.view;

import chess.domain.board.Team;
import chess.dto.BoardDto;
import java.util.List;

public class OutputView {

    private static final String INFO_MESSAGE_FORMAT = "> %s\n";
    private static final String SCORE_FORMAT = "%s : %.1f점";
    private static final String CURRENT_TURN_FORMAT = "현재 턴 : %s\n";
    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String START_COMMAND = "게임 시작 : start";
    private static final String END_COMMAND = "게임 종료 : end";
    private static final String MOVE_COMMAND = "게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String STATUS_COMMAND = "현재 점수 : status";


    public static void printStartInfo() {
        System.out.printf(INFO_MESSAGE_FORMAT, START_MESSAGE);
        System.out.printf(INFO_MESSAGE_FORMAT, START_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT, END_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT, MOVE_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT, STATUS_COMMAND);
        System.out.println();
    }

    public static void printChessBoard(BoardDto boardDto) {
        List<List<String>> board = boardDto.board();
        board.forEach(row -> System.out.println(String.join("", row)));
        System.out.println();
    }

    public static void printTeamScore(double score, Team team) {
        System.out.printf(INFO_MESSAGE_FORMAT,
            String.format(SCORE_FORMAT, team.teamName(), score));
    }

    public static void printCurrentTurn(Team currentTurn) {
        System.out.printf(INFO_MESSAGE_FORMAT,
            String.format(CURRENT_TURN_FORMAT, currentTurn.teamName()));
    }
}
