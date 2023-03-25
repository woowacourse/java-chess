package chess.view;

import chess.dto.inputView.ReadCommandDto;

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

    public ReadCommandDto readCommand() {
        final String input = readLine();
        final List<String> result = Arrays.stream(input.split(" "))
                .collect(Collectors.toList());
        return new ReadCommandDto(result);
    }
}
