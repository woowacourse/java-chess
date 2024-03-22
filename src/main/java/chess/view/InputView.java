package chess.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final String BOARD_POSITION_REGEX = "[a-hA-H][1-8]";
    private static final Pattern POSITION_PATTERN = Pattern.compile(BOARD_POSITION_REGEX + " " + BOARD_POSITION_REGEX);

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public PathDto readPosition() {
        String input = scanner.nextLine().strip();
        Matcher matcher = POSITION_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        return new PathDto(
                input.substring(0, 1),
                Integer.parseInt(input.substring(1, 2)),
                input.substring(3, 4),
                Integer.parseInt(input.substring(4, 5))
        );
    }

    public Command readCommand() {
        String input = readToken();
        return Command.from(input);
    }

    private String readToken() {
        return scanner.next();
    }
}
