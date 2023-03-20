package chess.view;

import chess.controller.GameStatus;

import java.util.Scanner;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public String readCommand() {
        final String input = readLine();
        final GameStatus gameStatus = GameStatus.from(input);
        if (gameStatus.isMove()) {
            return input;
        }
        if (gameStatus.isStart() || gameStatus.isEnd()) {
            return gameStatus.name();
        }
        throw new AssertionError();
    }

    public String readStartCommand() {
        final String input = readLine();
        validateStartCommand(input);
        return input;
    }

    private void validateStartCommand(final String input) {
        if (!GameStatus.START.name().equalsIgnoreCase(input)) {
            throw new IllegalArgumentException("start를 입력해야 게임을 시작할 수 있습니다.");
        }
    }

}
