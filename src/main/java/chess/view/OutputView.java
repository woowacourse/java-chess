package chess.view;

import static chess.controller.CommandActionMapper.END;
import static chess.controller.CommandActionMapper.EXIT;
import static chess.controller.CommandActionMapper.MOVE;
import static chess.controller.CommandActionMapper.START;
import static chess.controller.CommandActionMapper.STATUS;

import chess.dto.CampScore;
import chess.dto.GameResultResponse;
import java.util.List;

public class OutputView {

    private static final String GAME_START = "> 체스 게임을 시작합니다.";
    private static final String GAME_COMMAND_MOVE_DESCRIPTION =
            MOVE.getCommand() + " source위치 target위치 - 예. move b2 b3";
    private static final String GAME_COMMAND_REQUEST = String.format(
            "> 게임 시작: %s\n> 게임 종료: %s\n> 게임 이동: %s\n> 어플리케이션 종료: %s",
            START.getCommand(), END.getCommand(), GAME_COMMAND_MOVE_DESCRIPTION, EXIT.getCommand()
    );
    private static final String GAME_OVER_MESSAGE =
            "> 게임 오버" + System.lineSeparator()
                    + "> 결과 조회: " + STATUS.getCommand() + System.lineSeparator()
                    + "> 게임 종료: " + END.getCommand() + System.lineSeparator()
                    + "> 어플리케이션 종료: " + EXIT.getCommand();
    private static final String GAME_RESULT_CAMP_SCORE_FORMAT = "%s : %.1f점" + System.lineSeparator();
    private static final String GAME_RESULT_WIN_CAMP_FORMAT = "%s 팀 승리" + System.lineSeparator();
    private static final String ERROR_MESSAGE_FORMAT = "[입력 오류] %s" + System.lineSeparator();

    private OutputView() {
    }

    public static void printGuideMessage() {
        System.out.println(GAME_START);
        System.out.println(GAME_COMMAND_REQUEST);
    }

    public static void printBoard(List<List<String>> board) {
        System.out.println();
        for (List<String> rank : board) {
            printRank(rank);
            System.out.println();
        }
        System.out.println();
    }

    private static void printRank(final List<String> rank) {
        for (String value : rank) {
            System.out.print(value);
        }
    }

    public static void printGameOverMessage() {
        System.out.println(GAME_OVER_MESSAGE);
    }

    public static void printInputErrorMessage(final Exception exception) {
        System.out.printf(ERROR_MESSAGE_FORMAT, exception.getMessage());
    }

    public static void printGameResult(final GameResultResponse gameResult) {
        System.out.println();
        System.out.printf(GAME_RESULT_WIN_CAMP_FORMAT, gameResult.getWinCamp());
        printCampScore(gameResult.getWhiteScore());
        printCampScore(gameResult.getBlackScore());
    }

    private static void printCampScore(CampScore campScore) {
        System.out.printf(GAME_RESULT_CAMP_SCORE_FORMAT, campScore.getCampName(), campScore.getScore());
    }
}
