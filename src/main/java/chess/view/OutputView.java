package chess.view;

import chess.domain.board.Team;
import chess.dto.BoardDto;

import java.util.List;

public class OutputView {

    private static final String INFO_MESSAGE_FORMAT = "> %s\n";
    private static final String START_MESSAGE = "체스 게임을 시작합니다.";
    private static final String START_COMMAND = "게임 시작 : start";
    private static final String END_COMMAND = "게임 종료 : end";
    private static final String MOVE_COMMAND = "게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String GAME_OVER_MSG = "게임을 종료합니다.";


    public static void printStartInfo() {
        System.out.printf(INFO_MESSAGE_FORMAT, START_MESSAGE);
        System.out.printf(INFO_MESSAGE_FORMAT, START_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT, END_COMMAND);
        System.out.printf(INFO_MESSAGE_FORMAT, MOVE_COMMAND);
    }

    public static void printChessBoard(BoardDto boardDto) {
        List<List<String>> board = boardDto.board();
        board.forEach(row -> System.out.println(String.join("", row)));
        System.out.println();
    }

    public static void printTeamScore(double score, Team team) {
        System.out.printf(INFO_MESSAGE_FORMAT, team.teamName() + " : " + score);
        System.out.println();
    }

    public static void printMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public static void printGameOverInfo(String winner) {
        System.out.println(GAME_OVER_MSG);
        System.out.println(winner);
    }
}
