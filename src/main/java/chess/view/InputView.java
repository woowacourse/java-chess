package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {
    private static final String DELIMITER = " ";
    private static final String INPUT_ERROR_MESSAGE = "빈 값을 입력할 수 없습니다.";

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> T getInputWithRetry(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return getInputWithRetry(inputReader);
        }
    }

    public List<String> getCommand() {
        final String command = readCommand();
        return Arrays.asList(command.split(DELIMITER));
    }

    private String readCommand() {
        final String command = scanner.nextLine();
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
        return command;
    }
}
