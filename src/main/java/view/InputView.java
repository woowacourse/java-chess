package view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    public static final String REGEX_FORMAT = "^move [a-h][1-8] [a-h][1-8]$";
    private final Scanner scanner = new Scanner(System.in);

    public List<String> enterAnything() {
        String input = scanner.nextLine();
        return List.of(input.split(" "));
    }

    public ChessCommand enterStartOrEnd() {
        return ChessCommand.from(scanner.nextLine());
    }

    public List<String> enterSourceAndTargetPosition() {
        String input = scanner.nextLine();
        if (isValidSourceAndTargetInput(input)) {
            throw new IllegalArgumentException("이동할 source와 target 정보를 다시 입력해주세요.");
        }
        List<String> split = List.of(input.split(" "));
        return List.of(split.get(1), split.get(2));
    }

    public boolean isValidSourceAndTargetInput(final String input) {
        return Pattern.compile(REGEX_FORMAT).matcher(input).matches();
    }
}
