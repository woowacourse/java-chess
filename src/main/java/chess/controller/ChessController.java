package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Command;
import chess.view.Enter;
import chess.view.Enterable;
import chess.view.Input;
import chess.view.Output;

public class ChessController {

    private static final Enterable enterable = new Enter();

    public void run() {
        Output.printChessGameStart();

        ChessGame chessGame = new ChessGame();
        Command.execute(Input.inputCommand(enterable), chessGame);
        Output.printBoard(chessGame.getBoard());
    }
}
