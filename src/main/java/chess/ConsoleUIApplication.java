package chess;

import chess.command.Command;
import chess.view.InputView;

public class ConsoleUIApplication {
    public static void main(String[] args) {
        Command command = Command.of(InputView.readStartCommand());
        command.execute();
    }
}
