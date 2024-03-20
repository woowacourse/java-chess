package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readCommand() {
        String rawCommand = scanner.nextLine();
        validate(rawCommand);
        return rawCommand;
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("유효하지 않은 입력입니다.");
        }
    }
}
