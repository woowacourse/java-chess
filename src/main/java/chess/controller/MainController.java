package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.function.Consumer;

public class MainController {

    private final Board board = new Board(new BoardFactory());
    private final Map<GameCommand, SubController> subControllers = Map.of(
            GameCommand.START, new StartSubController(),
            GameCommand.MOVE, new MoveSubController(board)
    );

    public void run() {
        OutputView.printGameStartInfo();
        boolean isStart = false;
        repeat(this::play, isStart);
    }

    private void play(boolean isStart) {
        GameCommand command = GameCommand.from(InputView.readCommand());
        while (command != GameCommand.END) {
            subControllers.get(command).run(isStart);
            OutputView.printBoard(board.getBoard());
            command = GameCommand.from(InputView.readCommand());
            isStart = true;
        }
    }

    private void repeat(final Consumer<Boolean> consumer, boolean isStart) {
        try {
            consumer.accept(isStart);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            repeat(consumer, isStart);
        }
    }
}
