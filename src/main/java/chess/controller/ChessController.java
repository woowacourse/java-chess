package chess.controller;

import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printStartMessage();
        while (!chessGame.isFinished()) {
            playChess(chessGame);
        }
    }

    private void playChess(ChessGame chessGame) {
        try {
            CommandRequest commandRequest = InputView.inputCommand();
            Command command = commandRequest.getCommand();

            command.accept(chessGame, commandRequest.getSource(), commandRequest.getTarget());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            playChess(chessGame);
        }
    }
}
