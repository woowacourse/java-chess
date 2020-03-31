package chess.controller;

import chess.domain.ChessRunner;

public class StatusController extends GameController {
    public StatusController() {
        super();
    }

    @Override
    public void execute(ChessRunner chessRunner, String input) {
//        double score = chessRunner.calculateScore();
//        outputView.printStatus(score, chessRunner.getCurrentTeam());
        printBoard(chessRunner.getBoard());
    }
}
