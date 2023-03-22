package chess.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static boolean getStartCommandResult() {
        String inputCommand;
        try {
            inputCommand = SCANNER.nextLine();
            return isNotInputCommandStart(inputCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getStartCommandResult();
        }
    }

    private static boolean isNotInputCommandStart(final String inputCommand) {
        validateInputCommand(inputCommand);
        return inputCommand.equals("start");
    }

    private static void validateInputCommand(final String inputCommand) {
        if (!inputCommand.equals("start") && isNotInputCommandEnd(inputCommand)) {
            throw new IllegalArgumentException("start 또는 end만 입력할 수 있습니다.");
        }
    }

    private static boolean isNotInputCommandEnd(final String inputCommand) {
        return !inputCommand.equals("end");
    }

    public static List<String> getCommands() {
        return Arrays.stream(SCANNER.nextLine().split(" "))
                .collect(toList());
    }

}
