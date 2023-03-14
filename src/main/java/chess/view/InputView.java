package chess.view;

import chess.controller.GameCommand;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public GameCommand printGameStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

        return readGameCommand();
    }

    public GameCommand readGameCommand() {
        final String command = readInput();

        validateBlank(command);

        return GameCommand.from(command);
    }

    private void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }

    private String readInput() {
        return scanner.nextLine()
                .trim();
    }
}
