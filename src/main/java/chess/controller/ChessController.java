package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String END = "end";

    public void run(ChessGame chessGame, Commands commands) {
        boolean isPlaying = true;

        OutputView.printManual();
        while (isPlaying) {
            isPlaying = playGame(chessGame, commands);
        }
    }

    public boolean playGame(ChessGame chessGame, Commands commands) {
        try {
            String commandMessage = commands.execute(chessGame, InputView.inputCommand());
            OutputView.printMessage(commandMessage);
            OutputView.printBoard(chessGame.getBoard());
            return isPlaying(commandMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playGame(chessGame, commands);
            return true;
        }
    }

    public boolean isPlaying(String input) {
        return !input.equals(END);
    }
}