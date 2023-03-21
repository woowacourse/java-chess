package chess.view;

import chess.controller.CommandDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public CommandDto readCommand() {
        List<String> parsedCommandInput = Arrays.asList(scanner.nextLine()
                .split(" "));
        validate(parsedCommandInput);
        return CommandDto.from(parsedCommandInput);
    }

    private void validate(List<String> parsedCommandInput) {
        if (parsedCommandInput.size() == 1) {
            validateFormatWithoutArguments(parsedCommandInput);
            return;
        }
        if (parsedCommandInput.size() == 3) {
            validateFormatWithArguments(parsedCommandInput);
            return;
        }
        throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
    }

    private void validateFormatWithoutArguments(final List<String> parsedCommandInput) {
        if (!Objects.equals("start", parsedCommandInput.get(0))
                && !Objects.equals("end", parsedCommandInput.get(0))) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }

    private void validateFormatWithArguments(final List<String> parsedCommandInput) {
        if ((parsedCommandInput.get(1).length() != 2
                || parsedCommandInput.get(2).length() != 2)) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
        if (Objects.equals(parsedCommandInput.get(1), parsedCommandInput.get(2))) {
            throw new IllegalArgumentException("같은 지점이 들어왔습니다.");
        }
    }
}
