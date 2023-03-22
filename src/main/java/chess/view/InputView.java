package chess.view;

import chess.controller.CommandDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public CommandDto readCommand() {
        final String rawCommandInput = scanner.nextLine();
        List<String> parsedCommandInput = Arrays.asList(rawCommandInput.split(" "));
        validate(parsedCommandInput);
        return CommandDto.from(parsedCommandInput);
    }

    private void validate(List<String> parsedCommandInput) {
        if (parsedCommandInput.size() != 1 && parsedCommandInput.size() != 3) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
        if (parsedCommandInput.size() == 3 &&
                (parsedCommandInput.get(1)
                                   .length() != 2 || parsedCommandInput.get(2)
                                                                       .length() != 2)) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
        if ((Objects.equals(parsedCommandInput.get(0), "start") || Objects.equals(parsedCommandInput.get(0), "end")) &&
                parsedCommandInput.size() != 1) {
            throw new IllegalArgumentException("잘못된 명령어 형식입니다.");
        }
    }
}
