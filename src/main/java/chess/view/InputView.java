package chess.view;

import chess.controller.GameCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }

    public String readStartCommand() {
        final String input = readLine();
        validateStartCommand(input);
        return input;
    }

    private void validateStartCommand(final String input) {
        if (!GameCommand.START.name().equalsIgnoreCase(input)) {
            throw new IllegalArgumentException("start를 입력해야 게임을 시작할 수 있습니다.");
        }
    }

    public List<String> readCommand() {
        final String input = readLine();
        return Arrays.stream(input.split(" "))
                .collect(Collectors.toList());
    }
}
