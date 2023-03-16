package techcourse.fp.chess.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public String readInitCommand() {
        return scanner.nextLine().trim();
    }

    public String[] readCommand() {
        final String input = scanner.nextLine();
        final String[] split = input.split(" ");
        if (split[0].equals("move") && isInvalidInput(split)) {
            throw new IllegalArgumentException("잘못된 커맨드를 입력하셨습니다.");
        }

        if (split[0].equals("end") && split.length != 1) {
            throw new IllegalArgumentException("잘못된 커맨드를 입력하셨습니다.");
        }

        return split;
    }

    private boolean isInvalidInput(final String[] split) {
        return split.length != 3 ||
                split[1].length() != 2 || split[2].length() != 2;
    }


}
