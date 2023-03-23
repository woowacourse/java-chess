package chess.view;

import chess.controller.command.ExecuteCommand;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public ExecuteCommand readExecuteCommand() {
        return ExecuteCommandFactory.getInstance(scanner.nextLine());
    }
}
