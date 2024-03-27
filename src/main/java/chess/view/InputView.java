package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern POSITION_PATTERN = Pattern.compile("[a-hA-H][1-8]");

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public PositionDto readPosition() {
        String input = readToken().strip();
        if (!POSITION_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        return new PositionDto(
                input.substring(0, 1),
                Integer.parseInt(input.substring(1))
        );
    }

    private String readToken() {
        return scanner.next();
    }

    public Command readCommand() {
        String input = readToken();
        return Command.from(input);
    }
}
