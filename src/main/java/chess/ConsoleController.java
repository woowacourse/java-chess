package chess;

import chess.domain.board.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();

        String input = InputView.requestStartOrEnd();
        Command command = Command.of(input);

        while (command.isStart()) {
            Map<Position, Piece> board = command.getBoard();
            OutputView.printBoard(board);

            String commandInput = InputView.requestCommand();
            command = command.execute(commandInput);
        }
    }
}
