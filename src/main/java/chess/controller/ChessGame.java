package chess.controller;

import chess.dto.BoardDto;
import chess.model.Board;
import chess.model.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public final class ChessGame {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public void run() {
        InputView.printGameIntro();
        Board board = null;
        do {
            board = executeGame(board);
        } while (isRunning(board));
    }

    private Board executeGame(Board board) {
        return retryOnException(() -> executeCommand(board));
    }

    private Board executeCommand(Board board) {
        List<String> commands = InputView.askGameCommands();
        Command command = Command.findCommand(commands.get(COMMAND_INDEX));
        if (command.isEnd()) {
            return null;
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
        String source = commands.get(SOURCE_INDEX);
        String target = commands.get(TARGET_INDEX);
        board.move(source, target);
        BoardDto boardDto = BoardDto.from(board);
        OutputView.printChessBoard(boardDto);
    }

    private boolean isRunning(Board board) {
        return board != null;
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
