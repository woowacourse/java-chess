package chess.view;

import chess.machine.Command;
import chess.machine.CommandMapper;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);
    private final CommandMapper commandMapper = new CommandMapper();

    public Command readCommand() {
        String input = scanner.nextLine();
        return commandMapper.inputToCommand(input);
    }
}
