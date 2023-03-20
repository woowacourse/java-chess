package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.view.*;

import java.util.function.Supplier;

public class ChessController {

    public void run() {
        OutputView.printInitialMessage();
        if (Command.START == readStartSign()) {
            playChess();
        }
    }

    private Command readStartSign() {
        Command startSign;
        do {
            startSign = readCommand().getCommand();
        } while (Command.MOVE == startSign);
        return startSign;
    }

    private CommandDto readCommand() {
        return RetryIfThrowsException(() ->
                InputRenderer.toCommandDto(InputView.readCommand()));
    }

    private void playChess() {
        Board board = Board.create();
        OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));

        CommandDto commandDto = readMove();
        while (commandDto.getCommand() == Command.MOVE) {
            Position sourcePosition = commandDto.getSourcePosition();
            Position targetPosition = commandDto.getTargetPosition();
            tryCatchStrategy(() -> {
                board.movePiece(sourcePosition, targetPosition);
                OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));
            });
            commandDto = readCommand();
        }
    }

    private CommandDto readMove() {
        CommandDto moveSign;
        do {
            moveSign = readCommand();
        } while (Command.START == moveSign.getCommand());
        return moveSign;
    }

    private static <T> T RetryIfThrowsException(final Supplier<T> strategy) {
        T result = null;
        while (result == null) {
            result = tryCatchStrategy(strategy, null);
        }
        return result;
    }

    private static void tryCatchStrategy(final Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }

    private static <T> T tryCatchStrategy(final Supplier<T> strategy, T result) {
        try {
            result = strategy.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
        return result;
    }
}
