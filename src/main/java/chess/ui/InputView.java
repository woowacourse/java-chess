package chess.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static boolean getStartCommand() {
        OutputView.printStartGame();
        return getStartCommandResult();
    }

    private static boolean getStartCommandResult() {
        String inputCommand;
        try {
            inputCommand = SCANNER.nextLine();
            validateInputCommand(inputCommand);
            return isInputCommandStart(inputCommand);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getStartCommandResult();
        }
    }

    private static void validateInputCommand(final String inputCommand) {
        if (!isInputCommandStart(inputCommand) && !isInputCommandEnd(inputCommand)) {
            throw new IllegalArgumentException("start 또는 end만 입력할 수 있습니다.");
        }
    }

    private static boolean isInputCommandStart(final String inputCommand) {
        return inputCommand.equals("start");
    }

    private static boolean isInputCommandEnd(final String inputCommand) {
        return inputCommand.equals("end");
    }

    public static List<String> getCommand() {
        return Arrays.stream(SCANNER.nextLine().split(" "))
                .collect(toList());
    }
}
