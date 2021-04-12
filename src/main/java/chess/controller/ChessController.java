package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.exception.GameIsNotStartException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run(ChessGame chessGame) {
        OutputView.printManual();
        while (chessGame.isBeforeEnd()) {
            playGame(chessGame);
        }
    }

    public void playGame(ChessGame chessGame) {
        try {
            String inputCommand = InputView.inputCommand();
            Command.findCommand(inputCommand).execute(chessGame, inputCommand);
            checkGameStart(chessGame);
            OutputView.printBoard(chessGame.getBoard());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkGameStart(ChessGame chessGame) {
        if (chessGame.isBeforeStart()) {
            throw new GameIsNotStartException();
        }
    }
}