package chess.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern POSITION_PATTERN = Pattern.compile("^[a-h][1-8]$");

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public GameCommand getGameCommand() {
        return GameCommand.getGameCommand(scanner.next());
    }

    public String getPosition() {
        String input = scanner.next();
        validateEmpty(input);
        validatePosition(input);
        return input;
    }

    private void validateEmpty(final String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("이동 명령어의 위치 값이 필요합니다.");
        }
    }

    private void validatePosition(String input) {
        Matcher matcher = POSITION_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("이동 좌표는 file[a~h], rank[1~8] 조합으로 입력해야 합니다. 예) a1 b2");
        }
    }
}
