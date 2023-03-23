package view;

public final class OutputView {

    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다." + System.lineSeparator() +
            "> 게임 시작 : start" + System.lineSeparator() +
            "> 게임 종료 : end" + System.lineSeparator() +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static final String GAME_END_MESSAGE = "게임을 종료합니다.";

    public void printBoard(final String parsedBoard) {
        System.out.println(parsedBoard);
    }

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE);
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    public void printGameResult(final String gameResult) {
        System.out.println(gameResult);
    }
}
