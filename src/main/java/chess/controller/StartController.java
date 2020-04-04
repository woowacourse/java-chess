package chess.controller;

import chess.domain.ChessRunner;

public class StartController extends GameController {
    public StartController() {
        super();
    }

    @Override
    public void execute(ChessRunner chessRunner, String input) {
        printBoard(chessRunner.getBoardEntities());
    }
}
