package chess;

import java.util.function.Consumer;
import java.util.function.Supplier;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.command.MoveCommand;
import chess.view.command.StartCommand;

class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        StartCommand startCommand = handleException(inputView::readWannaStart);
        if (startCommand.isStart()) {
            Board board = new Board();
            outputView.printBoard(board);
            handleException(this::tryMove, board);
        }
    }

    private void tryMove(Board board) {
        MoveCommand moveCommand = inputView.readMoveCommand();
        while (!moveCommand.isEnd()) {
            Coordinate source = moveCommand.source();
            Coordinate target = moveCommand.target();
            board.move(source, target);
            outputView.printBoard(board);
            moveCommand = inputView.readMoveCommand();
        }
    }

    private <T> void handleException(Consumer<T> consumer, T target) {
        try {
            consumer.accept(target);
        } catch (Exception exception) {
            outputView.printExceptionMessage(exception);
            handleException(consumer, target);
        }
    }

    private <T> T handleException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception exception) {
            outputView.printExceptionMessage(exception);
            return handleException(supplier);
        }
    }
}
