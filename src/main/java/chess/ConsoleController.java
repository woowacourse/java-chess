package chess;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();
        Board board = Board.getInstance();
        State currentTurn = Ready.start(board);

        String startOrEnd = InputView.requestStartOrEnd();
        if (startOrEnd.equals("start")) {
            OutputView.printBoard(board);
            while (true) {
                String input = InputView.requestCommand();
                if (input.equals("end")) {
                    return;
                }

                if (input.startsWith("move")) {
                    String[] commands = input.split(" ");
                    currentTurn = currentTurn.movePiece(Position.of(commands[1]), Position.of(commands[2]));
                    OutputView.printBoard(board);
                }
            }
        }
    }
}
