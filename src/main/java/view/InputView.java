package view;

import java.util.Scanner;
import view.mapper.CommandInput;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        String rawCommand = scanner.nextLine();
        CommandInput.validateCommand(rawCommand);
        return rawCommand;
    }
}
