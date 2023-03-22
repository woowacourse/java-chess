package chess.controller;

import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.view.GameCommand.END;
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
        start();
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        outputView.printBoard(board);

        while (progress(chessGame)) {
            outputView.printBoard(board);
        }
    }

    private void start() {
        try {
            inputView.readStart();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            start();
        }
    }

    private boolean progress(ChessGame chessGame) {
        String command = inputCommand();
        if (isStop(command)) {
            return false;
        }
        try {
            return startGame(chessGame, command);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return progress(chessGame);
        }
    }

    private String inputCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return inputCommand();
        }
    }

    private boolean isStop(String command) {
        return END.getCommand().equals(command);
    }

    private boolean startGame(ChessGame chessGame, String command) {
        chessGame.progress(convertToSourcePosition(command), convertToTargetPosition(command));
        return true;
    }
}
