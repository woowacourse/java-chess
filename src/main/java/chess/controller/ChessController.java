package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.*;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        Commands commands = new Commands();
        OutputView.printManual();
        playGame(chessGame, commands);
    }

    public void playGame(ChessGame chessGame, Commands commands) {
        while (true) {
            try {
                String commandMessage = commands.execute(InputView.inputCommand(), chessGame);
                OutputView.printMessage(commandMessage);
                OutputView.printBoard(chessGame.getBoard());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                playGame(chessGame, commands);
            }
        }
    }
}