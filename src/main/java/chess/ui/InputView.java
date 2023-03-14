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
            return inputCommand.equals("start");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getStartCommandResult();
        }
    }

    private static void validateInputCommand(String inputCommand) {
        if (!inputCommand.equals("start") && !inputCommand.equals("end")) {
            throw new IllegalArgumentException("start 또는 end만 입력할 수 있습니다.");
        }
    }
}
