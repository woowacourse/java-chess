package techcourse.fp.chess.view;

import java.util.List;
import java.util.Scanner;
import techcourse.fp.chess.dto.CommandRequest;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public CommandRequest readCommand() {
        final String input = scanner.nextLine();

        final List<String> split = List.of(input.split(" "));
        return CommandRequest.create(split);
    }
}
