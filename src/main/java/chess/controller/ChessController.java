package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run(ChessGame chessGame, Commands commands) {
        OutputView.printManual();
        Board board = null;

        while (true) {
            Command.execute(InputView.inputCommand(), this);
            OutputView.printBoard(board);
        }
    }

    public void playGame(ChessGame chessGame, Commands commands) {
        while (true) {
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
}