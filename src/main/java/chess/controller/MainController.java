package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class MainController {

    private final Board board = new Board(new BoardFactory());
    private final Map<GameCommand, SubController> subControllers = Map.of(
            GameCommand.START, new StartSubController(),
            GameCommand.MOVE, new MoveSubController(board)
    );

    public void run() {
        OutputView.printGameStartInfo();

        GameCommand command = GameCommand.from(InputView.readCommand());
        while (command != GameCommand.END) {
            subControllers.get(command).run();
            OutputView.printBoard(board.getBoard());
            command = GameCommand.from(InputView.readCommand());
        }
    }
}
