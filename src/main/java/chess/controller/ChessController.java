package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.exception.GameIsNotStartException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String STATUS = "status";
    private static final String END = "end";

    public void run(ChessGame chessGame) {
        boolean isPlaying = true;

        OutputView.printManual();
        while (isPlaying) {
            isPlaying = playGame(chessGame);
        }
    }

    public boolean playGame(ChessGame chessGame) {
        try {
            String inputCommand = InputView.inputCommand();
            Command.findCommand(inputCommand).execute(chessGame, inputCommand);
            checkGameStart(chessGame);
            OutputView.printBoard(chessGame.getBoard());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return chessGame.isBeforeEnd();
    }

    private void checkGameStart(ChessGame chessGame) {
        if (chessGame.isBeforeStart()) {
            throw new GameIsNotStartException();
        }
    }
}