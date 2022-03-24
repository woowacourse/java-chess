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
        Turn turn = new Turn();
        while (!command.equals(Command.END)) {
            OutputView.printBoard(board, turn);

            command = executeCommand(board, turn);
        }
    }

    private Command executeCommand(Board board, Turn turn) {
        List<String> input = List.of(InputView.requestCommand().split(" "));

        if (input.size() == 3) {
            board.move(new Position(input.get(1)),
                    new Position(input.get(2)));
            turn.countTurn();
        }

        return Command.from(input.get(0));
    }
}
