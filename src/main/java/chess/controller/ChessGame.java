package chess.controller;

import chess.dto.BoardDto;
import chess.model.Board;
import chess.model.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public final class ChessGame {

    private boolean isRunning = true;

    public void run() {
        InputView.printGameIntro();
        Board board = null;
        while (isRunning) {
            board = executeGame(board);
        }
    }

    private Board executeGame(Board board) {
        return retryOnException(() -> executeCommand(board));
    }

    private Board executeCommand(Board board) {
        List<String> commands = InputView.askGameCommands();
        Command command = Command.findCommand(commands.get(0));
        if (command.isEnd()) {
            isRunning = false;
            return board;
        }
        if (command.isStart()) {
            return executeStart();
        }
        if (command.isMove() && board != null) {
            executeMove(commands, board);
        }
        return board;
    }

    private Board executeStart() {
        Board board = Board.createInitialBoard();
        BoardDto boardDto = BoardDto.from(board);
        OutputView.printChessBoard(boardDto);
        return board;
    }

    private void executeMove(List<String> commands, Board board) {
        board.move(commands.get(1), commands.get(2));
        BoardDto boardDto = BoardDto.from(board);
        OutputView.printChessBoard(boardDto);
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
