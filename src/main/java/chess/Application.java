package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.command.*;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        Commands commands = new Commands(
                Arrays.asList(
                        new EndOnCommand(),
                        new StartOnCommand(),
                        new MoveOnCommand(),
                        new StatusOnCommand()
                )
        );

        ChessController chessController =  new ChessController();
        chessController.run(chessGame, commands);
    }
}
