package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.domain.piece.Team;
import chess.exception.GameIsNotStartException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String STATUS = "status";
    private static final String END = "end";

    public void run(ChessGame chessGame, Commands commands) {
        boolean isPlaying = true;

        OutputView.printManual();
        while (isPlaying) {
            isPlaying = playGame(chessGame, commands);
        }
    }

    public boolean playGame(ChessGame chessGame, Commands commands) {
        String command = commands.execute(chessGame, InputView.inputCommand());
        try {
            checkGameStart(chessGame);
            OutputView.printBoard(chessGame.getBoard());
            showStatus(command, chessGame);
            return isPlaying(command);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return isPlaying(command);
        }
    }

    public void checkGameStart(ChessGame chessGame) {
        if (chessGame.isBeforeStart()) {
            throw new GameIsNotStartException();
        }
    }

    public void showStatus(String command, ChessGame chessGame) {
        if (command.equals(STATUS)) {
            OutputView.printStatus(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
        }
    }

    public boolean isPlaying(String command) {
        return !command.equals(END);
    }
}