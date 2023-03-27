package chess.view;

import dto.CommandDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static CommandDto readCommand() {
        List<String> parsedCommandInput = Arrays.asList(scanner.nextLine()
                .split(" "));
        validate(parsedCommandInput);
        return CommandDto.from(parsedCommandInput);
    }

    private static void validate(List<String> parsedCommandInput) {
        if (parsedCommandInput.size() == 1) {
            validateFormatWithoutArguments(parsedCommandInput);
            return;
        }
        if (parsedCommandInput.size() == 3) {
            validateCommandWithArguments(parsedCommandInput.get(0));
            validateFormatWithArguments(parsedCommandInput);
            return;
        }
        throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
    }

    private static void validateFormatWithoutArguments(final List<String> parsedCommandInput) {
        if (!Objects.equals("start", parsedCommandInput.get(0))
                && !Objects.equals("end", parsedCommandInput.get(0))
                && !Objects.equals("status", parsedCommandInput.get(0))) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }

    private static void validateCommandWithArguments(final String parsedCommand) {
        if (!Objects.equals("move", parsedCommand)) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }

    private static void validateFormatWithArguments(final List<String> parsedCommandInput) {
        if ((parsedCommandInput.get(1).length() != 2
                || parsedCommandInput.get(2).length() != 2)) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }
}
