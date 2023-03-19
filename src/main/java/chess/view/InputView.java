package chess.view;

import chess.domain.commnad.Command;
import java.util.Scanner;

public class InputView {

    private final static Scanner input = new Scanner(System.in);

    public Command readGameCommand() {
        try {
            String gameCommand = input.nextLine();
            return Command.from(gameCommand);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return readGameCommand();
        }
    }
}
