package controller;

import dto.ChessBoardDto;
import exception.CustomException;
import java.util.List;
import model.ChessGame;
import model.command.CommandLine;
import model.status.GameStatus;
import model.status.StatusFactory;
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
        final ChessGame chessGame = ChessGame.setupStartingPosition();
        outputView.printStartMessage();
        GameStatus gameStatus = initGame();

        while (gameStatus.isRunning()) {
            printCurrentStatus(chessGame);
            gameStatus = play(gameStatus, chessGame);
        }
    }

    private GameStatus initGame() {
        try {
            return StatusFactory.create(readCommandLine());
        } catch (final CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return initGame();
        }
    }

    private GameStatus play(final GameStatus gameStatus, final ChessGame chessGame) {
        try {
            return gameStatus.play(readCommandLine(), chessGame);
        } catch (final CustomException exception) {
            outputView.printException(exception.getErrorCode());
            return play(gameStatus, chessGame);
        }
    }

    private void printCurrentStatus(final ChessGame chessGame) {
        outputView.printChessBoard(ChessBoardDto.from(chessGame));
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
