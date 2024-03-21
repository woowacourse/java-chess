package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ChessGame {

    public void run() {
        OutputView.printStartMessage();
        Command command = Command.START;
        Board board = new Board(Map.of());

        while (command != Command.END) {
            final List<String> arguments = InputView.readCommand();
            command = requestUntilValid(() -> Command.from(arguments.get(0)));

            if (command == Command.START) {
                board = BoardFactory.createBoard();
                OutputView.printInitialBoard(board.get());
            }
            if (command == Command.MOVE) {
                tryMove(board, arguments.get(1), arguments.get(2));
            }
        }
    }

    private static void tryMove(Board board, String source, String target) {
        try {
            board.move(
                    Square.from(source),
                    Square.from(target));
            OutputView.printInitialBoard(board.get());
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    public <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
