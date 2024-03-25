package view;

import java.util.Scanner;
import view.command.CommandType;
import view.command.SeparatedInput;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public static final String MOVE_POSITION_REGEX_FORMAT = "^[a-h][1-8]$";

    public CommandType inputCommand() {
        String input = scanner.nextLine();
        try {
            return CommandType.from(SeparatedInput.from(input));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return CommandType.from(SeparatedInput.from(input));
        }
    }
}
