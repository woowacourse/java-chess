package chess;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();
        Command command = inputCommand();

        while (command.isStart()) {
            Board board = command.getBoard();
            OutputView.printBoard(board);
            command = inputCommandAndExecute(command);
            if (command.isStatus()) {
                OutputView.printStatus(command.getStatus());
            }
        }
    }

    private Command inputCommand() {
        try {
            return Command.of(InputView.requestStartOrEnd());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private Command inputCommandAndExecute(Command command) {
        try {
            command = command.execute(InputView.requestCommand());
            return command;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommandAndExecute(command);
        }
    }
}
