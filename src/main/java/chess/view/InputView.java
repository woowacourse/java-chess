package chess.view;

import chess.controller.CommandDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    private static void validateOptionsLength(final List<String> parsedCommandInput) {
        final String firstOption = parsedCommandInput.get(1);
        final String secondOption = parsedCommandInput.get(2);

        if (firstOption.length() != 2 || secondOption.length() != 2) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }

    private static void validateCommandSize(final List<String> parsedCommandInput) {
        if (parsedCommandInput.size() != 1 && parsedCommandInput.size() != 3) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }

    public CommandDto readCommand() {
        final String rawCommandInput = scanner.nextLine();

        List<String> parsedCommandInput = Arrays.asList(rawCommandInput.split(" "));
        validate(parsedCommandInput);

        return CommandDto.from(parsedCommandInput);
    }

    private void validate(List<String> parsedCommandInput) {
        validateCommandSize(parsedCommandInput);
        if (parsedCommandInput.size() != 1) {
            validateOptionsLength(parsedCommandInput);
        }
    }
}
