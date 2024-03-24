package chess.view;

import java.util.Scanner;
import chess.view.command.MoveCommand;
import chess.view.command.StartCommand;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public StartCommand readWannaStart() {
        String input = readLine();

        return StartCommand.from(input);
    }

    public MoveCommand readMoveCommand() {
        String input = readLine();

        return MoveCommand.from(input);
    }

    private String readLine() {
        return scanner.nextLine().trim();
    }
}
