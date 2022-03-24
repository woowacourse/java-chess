package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGame {

    public void run() {
        OutputView.printStartView();
        if (Command.firstCommand(InputView.requestCommand()) == Command.END) {
            return;
        }

        Board board = new Board();

        while (!board.isEnd()) {
            OutputView.printBoard(board);
            executeCommand(board);
        }

        OutputView.printResult(board);
    }

    private void executeCommand(Board board) {
        List<String> input = List.of(InputView.requestCommand().split(" "));

        if (Command.from(input.get(0)) == Command.END) {
            board.terminate();
            return;
        }

        if (input.size() == 3) {
            board.move(new Position(input.get(1)),
                    new Position(input.get(2)));
        }
    }
}
