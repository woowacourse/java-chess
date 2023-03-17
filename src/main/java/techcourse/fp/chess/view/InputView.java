package techcourse.fp.chess.view;

import java.util.List;
import java.util.Scanner;

public final class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInitCommand() {
        return scanner.nextLine().trim();
    }

    public List<String> readCommand() {
        final String input = scanner.nextLine();
        final List<String> split = List.of(input.split(" "));
        if (split.get(0).equals("move") && isInvalidInput(split)) {
            throw new IllegalArgumentException("잘못된 커맨드를 입력하셨습니다.");
        }

        if (split.get(1).equals("end") && split.size() != 1) {
            throw new IllegalArgumentException("잘못된 커맨드를 입력하셨습니다.");
        }

        return split;
    }

    private boolean isInvalidInput(final List<String> split) {
        return split.size() != 3 ||
                split.get(1).length() != 2 ||
                split.get(2).length() != 2;
    }
}
