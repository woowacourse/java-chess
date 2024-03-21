package chess.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^[a-h][1-8]$");

    private final Scanner scanner = new Scanner(System.in);

    public GameCommand readGameCommand() {
        String rawInput = scanner.next();

        return GameCommand.map(rawInput);
    }

    public String readPosition() {
        String position = scanner.next();
        validatePositionFormat(position);

        return position;
    }

    private void validatePositionFormat(final String input) {
        Matcher matcher = POSITION_PATTERN.matcher(input);
        boolean matches = matcher.matches();

        if (!matches) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식의 위치 입력입니다.");
        }
    }
}
