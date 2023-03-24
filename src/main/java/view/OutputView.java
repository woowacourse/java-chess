package view;

public final class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    public static final String GAME_STATUS_MESSAGE = "게임 상황입니다.";
    public static final String GAME_END_MESSAGE = "게임을 종료합니다.";

    public static final String GAME_GUIDE_MESSAGE =
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static final String GAME_WINNER_MESSAGE_FORMAT = "%s가 우승했습니다!" +
            System.lineSeparator();

    public void printBoard(final String parsedBoard) {
        System.out.println(parsedBoard);
    }

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
        System.out.println(GAME_GUIDE_MESSAGE);
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    public void printGameStatus(final String gameStatus) {
        System.out.println(GAME_STATUS_MESSAGE);
        System.out.println(gameStatus);
        System.out.println(GAME_GUIDE_MESSAGE);
    }

    public void printGameResult(final String gameResult) {
        System.out.println(GAME_END_MESSAGE);
        System.out.println(gameResult);
    }

    public void printWinner(final String winningColor) {
        System.out.printf(GAME_WINNER_MESSAGE_FORMAT, winningColor);
    }
}
