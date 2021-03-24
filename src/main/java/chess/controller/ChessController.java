package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.CommandInput;
import chess.domain.command.Commands;
import chess.domain.piece.Team;
import chess.exception.GameIsNotStartException;
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
        boolean play = true;
        try {
            CommandInput commandInput = CommandInput.create();
            String command = commands.execute(chessGame, commandInput);
            play = isPlay(command);
            checkGameStart(chessGame, play);
            OutputView.printBoard(chessGame.getBoard());
            showStatus(command, chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return play;
    }

    private void checkGameStart(ChessGame chessGame, boolean isPlay) {
        if (chessGame.isBeforeStart() && isPlay) {
            throw new GameIsNotStartException();
        }
    }

    private void showStatus(String command, ChessGame chessGame) {
        if (command.equals(STATUS)) {
            OutputView.printStatus(chessGame.status(Team.BLACK), chessGame.status(Team.WHITE));
        }
    }

    private boolean isPlay(String command) {
        return !command.equals(END);
    }
}