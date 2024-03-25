package model.menu;

import model.ChessGame;

public class End implements Menu {

    @Override
    public void play(ChessGame chessGame) {
        chessGame.end();
    }
}
