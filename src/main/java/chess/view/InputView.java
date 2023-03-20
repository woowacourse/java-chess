package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readStart() {
        return scanner.nextLine()
                .trim()
                .toLowerCase();
    }

    public List<String> readCommands() {
        return Arrays.stream(scanner.nextLine().trim().toLowerCase().split(" "))
                .collect(Collectors.toUnmodifiableList());
    }
}
