package chess.view;

import chess.domain.board.Board;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String GAME_START_GUIDE_MESSAGE = "체스 게임을 시작합니다.";
    private static final String GAME_START_OPTION = "게임 시작 : start";
    private static final String GAME_END_OPTION = "게임 종료 : end";
    private static final String GAME_MOVE_COMMAND_OPTION = "게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String GAME_FINISH_MESSAGE = "왕이 잡혀서 게임이 종료되었습니다.";
    private static final String TEAM_SCORE_STATUS_FORMAT = "흰색 팀의 점수는 %.1f, 검은 색 팀의 점수는 %.1f 입니다.";
    private static final int END_INDEX = 8;
    private static final int START_INDEX = 1;
    private static final int ROW_SIZE = 8;

    public static void printInputStartGuideMessage() {
        System.out.println(GAME_START_GUIDE_MESSAGE);
        System.out.println(GAME_START_OPTION);
        System.out.println(GAME_END_OPTION);
        System.out.println(GAME_MOVE_COMMAND_OPTION);
    }

    public static void printBoard(Board board) {
        for (int row = END_INDEX; row >= START_INDEX; row--) {
            for (int col = START_INDEX; col <= END_INDEX; col++) {
                System.out.print(board.findPieceBy((row - 1) * ROW_SIZE + col - 1));
            }
            System.out.print(NEW_LINE);
        }
    }

    public static void printGameFinish() {
        System.out.println(GAME_FINISH_MESSAGE);
    }

    public static void printTeamScore(double whiteScore, double blackScore) {
        System.out.println(String.format(TEAM_SCORE_STATUS_FORMAT, whiteScore, blackScore));
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }
}
