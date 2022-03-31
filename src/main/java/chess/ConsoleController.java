package chess;

import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.board.Piece;
import chess.console.view.InputView;
import chess.console.view.OutputView;
import java.util.Map;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();
        Command command = inputCommand();

        while (command.isStart()) {
            Map<Position, Piece> board = command.getBoard();
            OutputView.printBoard(board);
            command = inputCommandAndExecute(command);
            if (command.isStatus()) {
                OutputView.printStatus(command.getStatus());
            }
        }
    }

    private Command inputCommand() {
        try {
            return Command.of(InputView.requestToInputCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private Command inputCommandAndExecute(Command command) {
        try {
            command = command.execute(InputView.requestToInputCommand());
            return command;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommandAndExecute(command);
        }
    }
}
