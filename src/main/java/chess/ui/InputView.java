package chess.ui;

import java.util.Scanner;

public class InputView {

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

    private static void validateInputCommand(String inputCommand) {
        if (!isInputCommandStart(inputCommand) && !isInputCommandEnd(inputCommand)) {
            throw new IllegalArgumentException("start 또는 end만 입력할 수 있습니다.");
        }
    }

    private static boolean isInputCommandStart(String inputCommand) {
        return inputCommand.equals("start");
    }

    private static boolean isInputCommandEnd(String inputCommand) {
        return inputCommand.equals("end");
    }

}
