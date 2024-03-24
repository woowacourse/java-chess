package chess.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final String REGEX = "move ([a-zA-Z][1-8])? ([a-zA-Z][1-8])?|start|end";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static final Scanner SCANNER = new Scanner(System.in);

    public CommandDto readCommend() {
        String input = SCANNER.nextLine();
        validate(input);
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("올바르지 않은 명령어입니다.");
        }
        return CommandDto.from(input);
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("올바르지 않은 위치 값입니다.");
        }
    }
}
