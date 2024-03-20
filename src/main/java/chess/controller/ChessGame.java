package chess.controller;

import chess.dto.BoardDto;
import chess.model.Board;
import chess.model.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Supplier;

public final class ChessGame {

    public void run() {
        Command command = prepareCommand();
        if (command == Command.START) {
            Board board = Board.createInitialBoard();
            BoardDto boardDto = BoardDto.from(board);
            OutputView.printChessBoard(boardDto);
        }
    }

    private Command prepareCommand() {
        return retryOnException(() -> {
           String commandName = InputView.askGameCommand();
           return Command.findCommand(commandName);
        });
    }

    private <T> T retryOnException(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return retryOnException(operation);
        }
    }
}
