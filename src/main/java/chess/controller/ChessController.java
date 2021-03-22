package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        Commands commands = new Commands();
        OutputView.printManual();
        playGame(chessGame, commands);
    }

    public void playGame(ChessGame chessGame, Commands commands) {
        while (true) {
            commandAndExecute(chessGame, commands);
        }
    }

    private void commandAndExecute(ChessGame chessGame, Commands commands) {
        try {
            String commandMessage = commands.execute(InputView.inputCommand(), chessGame);
            OutputView.printMessage(commandMessage);
            OutputView.printBoard(chessGame.getBoard());
        } catch (Exception e) {
            OutputView.printMessage(e.getMessage());
            playGame(chessGame, commands);
        }
    }
}