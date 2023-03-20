package chess.view;

import chess.game.GameCommand;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static GameCommand readCommand() {
        String input = scanner.nextLine();
        return GameCommand.of(input);
    }
}
