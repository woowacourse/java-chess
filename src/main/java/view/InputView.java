package view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final Pattern MOVE_REGEX = Pattern.compile("^move [a-h][1-8] [a-h][1-8]$");

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String input = scanner.nextLine();
        if (!(START_COMMAND.equals(input) || END_COMMAND.equals(input) || MOVE_REGEX.matcher(input).matches())) {
            throw new IllegalArgumentException("올바른 명령어를 입력해 주세요.");
        }
        return input;
    }
}
