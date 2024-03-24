package chess.controller;

import chess.dto.BoardDto;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.board.InitialBoardFactory;
import chess.model.game.Command;
import chess.model.game.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public final class ChessGame {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.printGameIntro();
        GameStatus gameStatus = new GameStatus();
        Board board = null;
        while (gameStatus.isRunning()) {
            board = executeGame(board, gameStatus);
        }
    }

    private Board executeGame(Board board, GameStatus gameStatus) {
        return retryOnException(() -> executeCommand(board, gameStatus));
    }

    private Board executeCommand(Board board, GameStatus gameStatus) {
        List<String> commands = inputView.askGameCommands();
        Command command = Command.findCommand(commands.get(COMMAND_INDEX));
        if (command.isEnd()) {
            gameStatus.changeEnd();
            return null;
        }
        if (command.isStart()) {
            gameStatus.changeStart();
            return executeStart();
        }
        if (command.isMove()) {
            gameStatus.changeMove();
            executeMove(commands, board);
        }
        return board;
    }

    private Board executeStart() {
        BoardFactory boardFactory = new InitialBoardFactory();
        Board board = boardFactory.generate();
        BoardDto boardDto = BoardDto.from(board);
        outputView.printChessBoard(boardDto);
        return board;
    }

    private void executeMove(List<String> commands, Board board) {
        String source = commands.get(SOURCE_INDEX);
        String target = commands.get(TARGET_INDEX);
        board.move(source, target);
        BoardDto boardDto = BoardDto.from(board);
        outputView.printChessBoard(boardDto);
    }

    private <T> T retryOnException(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (Exception e) {
            outputView.printException(e);
            return retryOnException(operation);
        }
    }
}
