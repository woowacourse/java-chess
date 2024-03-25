package model.menu;

import model.ChessGame;

public class Start implements Menu {

    @Override
    public void play(ChessGame chessGame) {
        chessGame.start();
    }
}
