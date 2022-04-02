package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Color;
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

        OutputView.printStatusMessage(chessGame.score(Color.WHITE), chessGame.score(Color.BLACK));
        OutputView.printResultMessage(chessGame.result());
    }

    private void playChess(ChessGame chessGame) {
        try {
            CommandRequest commandRequest = InputView.inputCommand();
            Command command = commandRequest.getCommand();

            command.excute(chessGame, commandRequest.getSource(), commandRequest.getTarget());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            OutputView.printErrorMessage(e.getMessage());
            playChess(chessGame);
        }
    }
}
