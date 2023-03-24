package chess.view;

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

    public List<String> readCommand() {
        final String input = readLine();
        return Arrays.stream(input.split(" "))
                .collect(Collectors.toList());
    }
}
