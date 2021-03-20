package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run(ChessGame chessGame, Commands commands) {
        OutputView.printManual();
        while (true) {
            playGame(chessGame, commands);
        }
    }

    public void playGame(ChessGame chessGame, Commands commands) {
        try {
            String commandMessage = commands.execute(InputView.inputCommand());
            OutputView.printMessage(commandMessage);
            OutputView.printBoard(chessGame.getBoard());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playGame(chessGame, commands);
        }
    }
}