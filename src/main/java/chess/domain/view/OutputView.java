package chess.domain.view;

public class OutputView {
    private static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }
}
