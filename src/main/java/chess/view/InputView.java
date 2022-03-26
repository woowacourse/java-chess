package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String COMMAND_DELIMITER = " ";
    private static final List<String> COMMANDS = List.of("move", "start", "end");

    public static List<String> inputCommand() {
        List<String> commands = Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));
        validateCommand(commands.get(0));

        return commands;
    }

    private static void validateCommand(String command) {
        if (!COMMANDS.contains(command)) {
            throw new IllegalArgumentException("존재하지 않는 명령어입니다.");
        }
    }
}
