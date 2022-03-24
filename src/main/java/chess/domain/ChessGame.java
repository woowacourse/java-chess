package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    public void run() {
        OutputView.printStartView();
        Command command = Command.from(InputView.requestCommand());
        Board board = new Board();
        board.initialize();

        while (!command.equals(Command.END)) {
            OutputView.printBoard(board);

            List<String> input = List.of(InputView.requestCommand().split(" "));
            executeMoveCommand(board, input);
            command = Command.from(input.get(0));
        }
    }

    private void executeMoveCommand(Board board, List<String> input) {
        if (input.size() == 3) {
            board.move(new Position(input.get(1)),
                    new Position(input.get(2)));
        }
    }
}
