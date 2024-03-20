package chess.view;

import java.util.Scanner;
import java.util.Set;

public class InputView {
    private static final Set<String> COMMAND = Set.of("start", "end");
    private static final String INVALID_COMMAND = "start 또는 end만 입력가능합니다.";
    private static final String START_MESSAGE = "게임 시작은 start, 종료는 end 명령을 입력하세요.";
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readStartCommand() {
        System.out.println(START_MESSAGE);
        String input = scanner.nextLine();
        validateCommand(input);
        return input;
    }

    private void validateCommand(final String command) {
        if (!COMMAND.contains(command)) {
            throw new IllegalArgumentException(INVALID_COMMAND);
        }
    }
}
