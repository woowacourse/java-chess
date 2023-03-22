package chess.controller;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.GameCommand;
import chess.view.GameCommandView;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.view.PositionConverter.convertToSourcePosition;
import static chess.view.PositionConverter.convertToTargetPosition;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame(new Board());
        while (chessGame.isNotTerminated()) {
            playChessGameWithExceptionHandling(chessGame);
        }
    }

    private void playChessGameWithExceptionHandling(ChessGame chessGame) {
        try {
            String command = inputView.readCommand();
            playChessGame(chessGame, command);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            playChessGameWithExceptionHandling(chessGame);
        }
    }

    private void playChessGame(ChessGame chessGame, String command) {
        if (GameCommandView.isStartCommand(command)) {
            startChessGame(chessGame);
        }
        if (GameCommandView.isStatusCommand(command)) {
            stateChessGame(chessGame);
        }
        if (GameCommandView.isEndCommand(command)) {
            endChessGame(chessGame);
        }
        progressChessGame(chessGame, command);
    }

    private void startChessGame(ChessGame chessGame) {
        chessGame.inputGameCommand(GameCommand.START);
        outputView.printBoard(chessGame.getBoard());
    }

    private void stateChessGame(ChessGame chessGame) {
        chessGame.inputGameCommand(GameCommand.STATUS);
    }

    private void endChessGame(ChessGame chessGame) {
        chessGame.inputGameCommand(GameCommand.END);
    }

    private void progressChessGame(ChessGame chessGame, String command) {
        if (GameCommandView.isValidCommandWithoutMove(command)) {
            return;
        }
        chessGame.inputGameCommand(GameCommand.MOVE);
        chessGame.progress(convertToSourcePosition(command), convertToTargetPosition(command));
        outputView.printBoard(chessGame.getBoard());
    }
}
