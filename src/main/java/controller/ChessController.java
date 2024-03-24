package controller;

import dto.ChessBoardDto;
import exception.CustomException;
import java.util.List;
import model.ChessBoard;
import model.command.CommandLine;
import model.status.GameStatus;
import model.status.Initialization;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final ChessBoard chessBoard = ChessBoard.setupStartingPosition();
        outputView.printStartMessage();
        GameStatus gameStatus = initGame();

        while (gameStatus.isRunning()) {
            printCurrentStatus(chessBoard);
            gameStatus = play(gameStatus, chessBoard);
        }
    }

    private GameStatus initGame() {
        try {
            return Initialization.gameSetting(readCommandLine());
        } catch (final CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return initGame();
        }
    }

    private GameStatus play(final GameStatus gameStatus, final ChessBoard chessBoard) {
        try {
            return gameStatus.play(readCommandLine(), chessBoard);
        } catch (final CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return play(gameStatus, chessBoard);
        }
    }

    private void printCurrentStatus(final ChessBoard chessBoard) {
        outputView.printChessBoard(ChessBoardDto.from(chessBoard));
    }

    private CommandLine readCommandLine() {
        try {
            List<String> command = inputView.readCommandList();
            return CommandLine.from(command);
        } catch (final CustomException exception) {
            outputView.printException(exception.getErrorCode());
        }
        return readCommandLine();
    }
}
