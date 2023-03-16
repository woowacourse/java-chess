package controller;

import domain.game.Game;
import view.OutputView;

public class ChessController {
    public void run() {
        Game game = Game.create();
        new OutputView().printChessBoard(game.getChessBoard());
    }
}
